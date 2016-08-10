rem START EDIT HERE
set JSHOMEDIR=%XAP_HOME%
set ANT_HOME=C:\apache-ant-1.9.6
rem END EDIT HERE

set GS_HOME=%JSHOMEDIR%
set MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=512m

set PATH=%JAVA_HOME%\bin;%ANT_HOME%\bin;%JSHOMEDIR%\bin;%PATH%

set NIC_ADDR=localhost