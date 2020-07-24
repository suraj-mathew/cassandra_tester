package com.sm.cassandra_tester;

import org.apache.log4j.BasicConfigurator;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * A sample class to verify the cassandra DB connectivity
 * 
 * @author suraj
 *
 */
public class DBConnector {

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		if(args.length == 2) {

			Session session = null;

			try {
				String serverIP = args[0];
				String keyspace = args[1];

				System.out.println("\n\nGoing to test the cassandra DB at ip="+serverIP+" and keyspace="+keyspace+"\n\n");

				Cluster cluster = Cluster.builder()
						.addContactPoints(serverIP)
						.build();

				//Casandra's ketsapce is case sensitive.So need to wrap it in double quotes
				keyspace = "\""+keyspace+"\"";

				session = cluster.connect(keyspace);

				String cqlStatement = "SELECT dateof(now()) FROM system.local";
				for (Row row : session.execute(cqlStatement)) {
					System.out.println("\n\n============SUCCESS========\n\n");
					System.out.println("\n\n============CURRENT DATE FROM DB========\n\n");
					System.out.println(row.getDate(0));
					System.out.println("\n\n====================\n\n");
				}

				System.out.println("Closing DB session");

			} catch (Exception exception) {
				System.err.println("\n\nERROR ==> \n\n"+exception.getMessage()+"\n\n");
			} finally {
				if(session != null) {
					session.close();
				}
			}

		} else {
			System.err.println("\n\nIncorrect usage of program. Run with 2 arguments");
			System.err.println("Example:\n\nmvn exec:java -Dexec.mainClass=\"com.sm.cassandraester.DBConnector\" -Dexec.args=\"cassandra_DB_IP cassandra_keysapce\"\n\n");
		}
	}
}
