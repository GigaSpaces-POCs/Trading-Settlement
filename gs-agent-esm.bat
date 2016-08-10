@set SCRIPTDIR=%~dp0

@call setExampleEnv.bat

cd %JSHOMEDIR%\bin

del mydb.*

::start gs-agent.bat gsa.esm 1 gsa.gsc 0 gsa.lus 1 gsa.gsm 1

set NIC_ADDR=localhost

start gs-agent.bat gsa.lus 1 gsa.gsm 1 gsa.gsc 3

