#!/bin/bash 
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/"
export JSHOMEDIR="/Users/aharon/XAP-Builds/gigaspaces-xap-premium-10.2.1-ga"
export ANT_HOME="XAP-Builds/gigaspaces-xap-premium-10.2.1-ga/lib/platform/ant"
export GS_HOME="${JSHOMEDIR}"
export M2_HOME="/Users/aharon/Software/apache-maven-3.0.5"
export MAVEN_OPTS="-Xmx256m -XX:MaxPermSize=128m"
export PATH="${JAVA_HOME}/bin:${ANT_HOME}/bin:${M2_HOME}/bin:${JSHOMEDIR}/bin:${PATH}"
export NIC_ADDR=127.0.0.1
