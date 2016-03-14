@set SCRIPTDIR=%~dp0

@call setExampleEnv.bat

cd %JSHOMEDIR%\bin
set JAVA_OPTIONS=-Xmx512m
@call gs-ui.bat

exit