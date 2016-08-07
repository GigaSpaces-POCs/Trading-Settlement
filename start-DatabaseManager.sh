#!/bin/bash

. setExampleEnv.sh

#mvn os:hsql-ui -Durl=jdbc:hsqldb:hsql://localhost/xdb
mvn os:hsql-ui -Durl=jdbc:hsqldb:hsql://10.8.1.50/xdb &
