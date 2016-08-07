#!/bin/bash

. setExampleEnv.sh

java -cp $JSHOMEDIR/lib/platform/jdbc/hsqldb-2.3.2.jar org.hsqldb.Server -database.0 file:mydb -dbname.0 xdb
