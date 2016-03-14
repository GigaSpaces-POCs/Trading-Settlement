@set SCRIPTDIR=%~dp0

@call setExampleEnv.bat

cd %JSHOMEDIR%\bin

del mydb.*

set NIC_ADDR=SUP03

start gs-agent.bat gsa.esm 1 gsa.gsc 0 gsa.lus 1 gsa.gsm 1

set NIC_ADDR=localhost

start gs-agent.bat gsa.lus 0 gsa.gsm 0 gsa.gsc 3

