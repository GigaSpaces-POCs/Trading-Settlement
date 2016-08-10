call setExampleEnv.bat

%JSHOMEDIR%\bin\gs.bat deploy -cluster schema=partitioned-sync2backup total_members=2,1 -max-instances-per-vm 1 components\.target\settlement-app-components.jar

@call "%GS_HOME%\bin\setenv.bat"

rem set NIC_ADDR=SUP03

rem java -cp %GS_JARS%;".\startup\.target\classes"  com.gigaspaces.settlement.util.ScaleDemoMain components\.target\settlement-app-components.jar

rem pause

