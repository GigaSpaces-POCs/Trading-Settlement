#!/bin/bash

. setExampleEnv.sh

${JSHOMEDIR}/bin/gs.sh deploy -cluster schema=partitioned-sync2backup total_members=2,1 -max-instances-per-vm 1 components/.target/settlement-app-components.jar
