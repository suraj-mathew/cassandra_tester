# cassandra_tester - test branch
A sample project to verify the cassandra db connectivity from java program

 - **mvn clean install**
 - **mvn exec:java -Dexec.mainClass="com.sm.cassandra_tester.DBConnector" -Dexec.args="CASSANDRA_DB_IP KEYSPACE_NAME"**

 (give the IP and keyspace name in above command and run)
 
 Example:
 
 *mvn exec:java -Dexec.mainClass="com.sm.cassandra_tester.DBConnector" -Dexec.args="192.168.10.20 ApplicationsDummyspace"*
 
 -------------------------------------- 
 
 On success, the output will print the current date time from db as below
 
    Going to test the cassandra DB at ip=localhost and keyspace=ApplicationsDummyspace
    
    ============SUCCESS========
    ============CURRENT DATE FROM DB========
    Fri Jul 24 14:56:48 IST 2020
    ====================
    Closing DB session


On failure, we will get an output as below

    Going to test the cassandra DB at ip=localhost and keyspace=ApplicationsDummyspace
    
    ERROR ==> 
    All host(s) tried for query failed (tried: localhost/127.0.0.1:9042 (com.datastax.driver.core.TransportException: [localhost/127.0.0.1:9042] Cannot connect))

