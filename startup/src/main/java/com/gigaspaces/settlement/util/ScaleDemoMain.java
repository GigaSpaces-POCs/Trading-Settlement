package com.gigaspaces.settlement.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsa.GridServiceAgent;
import org.openspaces.admin.gsa.GridServiceContainerOptions;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.elastic.ElasticStatefulProcessingUnitDeployment;
import org.openspaces.admin.pu.elastic.config.ManualCapacityScaleConfigurer;
import org.openspaces.admin.space.ElasticSpaceDeployment;
import org.openspaces.core.util.MemoryUnit;

public class ScaleDemoMain {
	static DecimalFormat formater = new DecimalFormat("0.0");
	
	// For multiple machines
	static int MAX_MEMORY_CAPACITY_GB=64;
	static int INITIAL_MEMORY_CAPACITY_GB=16;
	static int TARGET_MEMORY_CAPACITY_GB_STEP1=32;
	static int TARGET_MEMORY_CAPACITY_GB_STEP2=64;
	static int TARGET_MEMORY_CAPACITY_GB_STEP3=16;
	static int CONTAINER_MEMORY_CAPACITY_GB=4;

	// For single machine
	static int MAX_MEMORY_CAPACITY_MB=256;
	static int INITIAL_MEMORY_CAPACITY_MB=128;
	static int CONTAINER_MEMORY_CAPACITY_MB=64;
	
	static boolean localMachineDemo = true;
	static String locator = System.getProperty("locators", "127.0.0.1");
	
	public static void main(String[] args) throws Exception{
		String pu_file = args[0];
		if (pu_file == null)
		{
			System.out.println("Missing PU deploy file name - exit demo");
			System.exit(0);
		}
		System.setProperty("java.rmi.server.hostname", locator);
		
		AdminFactory adminFactory = new AdminFactory();
		
		Admin admin = adminFactory.create();
		
		System.out.println("Created Admin - OK!");
		if (admin == null) {
			System.out.println(" Can't find a Admin - exit demo");
			System.exit(0);
		}

		admin.getGridServiceAgents().waitForAtLeastOne(10, TimeUnit.SECONDS);
		admin.getElasticServiceManagers().waitForAtLeastOne(10, TimeUnit.SECONDS);
		

		GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne(10, TimeUnit.SECONDS);
		
		if (gsm == null) {
			System.out.println(" Can't find a GSM - exit demo");
			System.exit(0);
		}
		ProcessingUnit pu = null;
		
		pu = admin.getProcessingUnits().waitFor("settlement-app-components", 5,TimeUnit.SECONDS);
		
		File archiveFile = new File(pu_file);

		if (pu== null) {
			System.out.println(" PU not running - initial deploy");
			// check if the PU already running
			long startTime = System.currentTimeMillis();
			try {
				if (localMachineDemo) {
					System.out.println("--- > Local Machine Demo - Starting initial deploy - Deploying a PU with:" + INITIAL_MEMORY_CAPACITY_MB + "MB");
			//		ProcessingUnit pu = gsm.deploy(new ElasticStatefulProcessingUnitDeployment("space")	
					pu = gsm.deploy(new ElasticStatefulProcessingUnitDeployment(archiveFile)
					 .singleMachineDeployment()
					 .memoryCapacityPerContainer(CONTAINER_MEMORY_CAPACITY_MB,MemoryUnit.MEGABYTES)
			         .maxMemoryCapacity(MAX_MEMORY_CAPACITY_MB,MemoryUnit.MEGABYTES)
			         	//initial scale
			         	.scale(
			         			new ManualCapacityScaleConfigurer().
			         			memoryCapacity(INITIAL_MEMORY_CAPACITY_MB,MemoryUnit.MEGABYTES).
			         			create())       
					);
					monitorPUScaleProgress(pu , INITIAL_MEMORY_CAPACITY_MB);
				} else {
					System.out.println("Distributed Demo - Starting initial deploy - Deploying a PU with:" + INITIAL_MEMORY_CAPACITY_GB + "GB");
					pu = gsm.deploy(new ElasticSpaceDeployment("mySpace")
							 .memoryCapacityPerContainer(CONTAINER_MEMORY_CAPACITY_GB,MemoryUnit.GIGABYTES)
					         .maxMemoryCapacity(MAX_MEMORY_CAPACITY_GB,MemoryUnit.GIGABYTES)
//					         .maxNumberOfCpuCores(MAX_CORES_CAPACITY)
					         	//initial scale
					         	.scale(
					         			new ManualCapacityScaleConfigurer().
					         			memoryCapacity(INITIAL_MEMORY_CAPACITY_GB,MemoryUnit.GIGABYTES).
					         			maxConcurrentRelocationsPerMachine(2).
					         			create())       
							);
//							System.out.println( "There are " + pu.getInstances().length + " running with " + pu.getNumberOfBackups()+ " backups");
							monitorPUScaleProgress(pu , INITIAL_MEMORY_CAPACITY_GB);
				}
				long endTime = System.currentTimeMillis();
				System.out.println(" Initial Deploy done! - Time to deploy system:" + (endTime-startTime)/1000 + " seconds");
/*				
				System.out.println(" Starting GSCs for monitor, feeder, web");
				GridServiceAgent gsAgent = admin.getGridServiceAgents().waitForAtLeastOne();
				GridServiceContainerOptions gscOptions = new GridServiceContainerOptions();
				gscOptions.vmInputArgument("-Dcom.gs.zones=local");
				gscOptions.vmInputArgument("-Xmx64m");
				gsAgent.startGridService(gscOptions);
				gsAgent.startGridService(gscOptions);
				gsAgent.startGridService(gscOptions);
*/			
			}
			catch (org.openspaces.admin.pu.ProcessingUnitAlreadyDeployedException e) {
				System.out.println(e.getMessage());
			}
		}
		
//		if (localMachineDemo) {
//			scale (pu,TARGET_MEMORY_CAPACITY_MB_STEP1 , MemoryUnit.MEGABYTES); 
//			scale (pu,TARGET_MEMORY_CAPACITY_MB_STEP2 , MemoryUnit.MEGABYTES); 
//			scale (pu,TARGET_MEMORY_CAPACITY_MB_STEP3 , MemoryUnit.MEGABYTES); 
//		} else {
//			scale (pu,TARGET_MEMORY_CAPACITY_GB_STEP1 , MemoryUnit.MEGABYTES); 
//			scale (pu,TARGET_MEMORY_CAPACITY_GB_STEP2 , MemoryUnit.MEGABYTES); 
//			scale (pu,TARGET_MEMORY_CAPACITY_GB_STEP3 , MemoryUnit.MEGABYTES); 
//		}
		System.exit(0);
	}

	
	static void monitorPUScaleProgress(ProcessingUnit pu , int targetCapacity) throws Exception {
		double bias = targetCapacity/10; // 10 %
		double progressPercentage =0;
		
		while (true) {
			int totalGSCs= pu.getAdmin().getGridServiceContainers().getSize();
			if (localMachineDemo) {
				double currentMemUsageMB = getPUTotalMemoryUtilization(pu);
				double diff = Math.abs (targetCapacity - currentMemUsageMB);
				if (currentMemUsageMB>=targetCapacity) {
					// scale down scenario
					progressPercentage = (100 * (1 - (diff/currentMemUsageMB)));
				} else {
					// scale up scenario
					progressPercentage = (100 - ((diff / targetCapacity) * 100));
				}	
				System.out.println(new Date() + ">> Total Memory used:"+(formater.format(currentMemUsageMB))+ " MB - Progress:"+ (formater.format(progressPercentage)) + " % done - Total Containers:" + totalGSCs);
				Thread.sleep(2000);
				if (currentMemUsageMB> (targetCapacity - bias) 
						&& (currentMemUsageMB < (targetCapacity + bias)))
					break;
			} else {
				double currentMemUsageGB = getPUTotalMemoryUtilization(pu)/1000;
				double diff = Math.abs (targetCapacity - currentMemUsageGB);
				
				if (currentMemUsageGB>=targetCapacity) {
					// scale down scenario
					progressPercentage = (int)(( (1 - (diff/targetCapacity)) * 100));
				} else {
					// scale up scenario
					progressPercentage = (int)(100 - ((diff / targetCapacity) * 100));
				}	
				
				System.out.println(new Date() + ">> Total Memory used:"+formater.format(currentMemUsageGB)+ " GB - Progress:"+ (formater.format(progressPercentage)) + " % done - Total Containers:" + totalGSCs);			
				Thread.sleep(2000);
				if (currentMemUsageGB> (targetCapacity - bias) 
						&& (currentMemUsageGB < (targetCapacity + bias)))
					break;
			}				
			
			if (progressPercentage> 95)
				break;
		}
	}
	
//	TARGET_MEMORY_CAPACITY_MB
//  TARGET_MEMORY_CAPACITY_GB	
	static void scale (ProcessingUnit pu , int targetMemoryCapacity , MemoryUnit memUnit) throws Exception {
		long startTime =0 ;
		// checking deployed PU.
		if (localMachineDemo) {
			System.out.println(" About to start changing memory capacity from " + formater.format(getPUTotalMemoryUtilization(pu)) + " MB to " + targetMemoryCapacity + " MB");
			System.out.println(" Hit enter to scale the data grid...");
			System.in.read();
			startTime = System.currentTimeMillis();
			pu.scale(new ManualCapacityScaleConfigurer()
	        .memoryCapacity(targetMemoryCapacity,memUnit)
	        .create());
		} else {
			System.out.println(" About to start changing memory capacity from " + formater.format(getPUTotalMemoryUtilization(pu)/1000) + " GB to " + targetMemoryCapacity + " GB");
			System.out.println(" Hit enter to scale the data grid...");
			System.in.read();
			startTime = System.currentTimeMillis();
			pu.scale(new ManualCapacityScaleConfigurer()
	        .memoryCapacity(targetMemoryCapacity,memUnit)
	        .maxConcurrentRelocationsPerMachine(2)
	        .create());
		}
		monitorPUScaleProgress(pu,targetMemoryCapacity);
		long endTime = System.currentTimeMillis();
		System.out.println(" Memory capacity change done! - Time to scale system:" + (endTime-startTime)/1000 + " seconds");
		
	}
	
	static double getPUTotalMemoryUtilization(ProcessingUnit pu) {
		HashMap<String,Double> puJVMsSet = new HashMap<String,Double>();
//		System.out.println("Total Instance Count:" + pu.getNumberOfInstances() * 2);

		pu.waitFor(pu.getNumberOfInstances() * 2);
		double totalMemoryInMB=0;
		ProcessingUnitInstance instances[] = pu.getInstances();
		for (ProcessingUnitInstance processingUnitInstance : instances) {
			puJVMsSet.put(processingUnitInstance.getMachine().getHostAddress() + 
					processingUnitInstance.getVirtualMachine().getDetails().getPid(), 
					new Double (processingUnitInstance.getVirtualMachine().getDetails().
							getMemoryHeapMaxInMB()));
		}
//		System.out.println("Current total number of JVMs used:" + puJVMsSet.size());		
		Iterator<Double> iter = puJVMsSet.values().iterator();
		while (iter.hasNext()) {
			totalMemoryInMB =totalMemoryInMB + iter.next().doubleValue(); 
		}
		return totalMemoryInMB;
	}	
}
