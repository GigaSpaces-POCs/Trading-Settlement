call setExampleEnv.bat

java -cp %GS_HOME%\lib\platform\jdbc\hsqldb-2.3.2.jar org.hsqldb.Server -database.0 file:mydb -dbname.0 xdb
