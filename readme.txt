Settlement Trading Demo

Perform the following steps:
- Unzip demo
- Move into the settlement-app folder
- Edit setExampleEnv.bat and change JAVA_HOME, JSHOMEDIR
- Change name of .at files to .bat
- Add \gigaspaces-xap-premium-8.0.4-ga\tools\maven\apache-maven-3.0.2\bin to you path
- Run \gigaspaces-xap-premium-8.0.4-ga\tools\maven\installmavenrep.bat
- Run the scripts in the following order:
mvn package
gs-agent-esm
gs-ui
start-hsqldb
start-DatabaseManager
deploy-app
deploy-messaging
deploy-feeder
deploy-monitor
deploy-mirror
deploy-blotter (takes 10 min to deploy)

- Connect to the blotter (gwt-dealsBlotter processing unit) using your browser (http://127.0.0.1:8080/gwt-dealsBlotter/).
- Log in with username that matches this format:  <any characters>@<trading entity> where <trading entity> = bank1 or cust1 or bank2 or cust2, etc.  For example, username = 123@bank1.  Password can be any characters.
- Start the application by clicking on the Administration tab and then clicking Start Feeder. You can Speed up the feeder rate by decreasing the refresh Interval to 1000 and clicking the Start Feeder.
- When the number of com.gigaspaces.settlement.model.Trade object > 50 (use the GS-UI - Data Type List for the settlementSpace to see the amount of objects), the settlementSpace will scale up by creating 2 additional GSCs. See the Hosts view.
- You can scale the application down by clicking on the Administration tab and then clicking on Clear Trades.

Description:
The project consists of 6 modules: app, blotter, messaging, monitor, feeder and mirror. 

The app module starts up a space and on top of it starts a polling container that performs a take from the space of unprocessed data entries. The take operation results in an event that will end up executing the processor class. The processor sets the processed flag to true in the data object and returns it. The return value is automatically written back to the space.  

Any changes done on the space are replicated in a reliable asynchronous manner to the mirror processing unit which persists the changes to the database. 

The mirror module persists changes to the database using Hibernate. 

The feeder module connects remotely and writes unprocessed data objects into the space which results in events being created within the processor.

The messaging module generates a message for each matched deal that is found.

The blotter starts a web server that is used to monitor and configure the demo.