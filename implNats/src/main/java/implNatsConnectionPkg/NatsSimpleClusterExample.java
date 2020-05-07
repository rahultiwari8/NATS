package implNatsConnectionPkg;



import java.util.concurrent.CountDownLatch;

import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;

public class NatsSimpleClusterExample {
	  public NatsSimpleClusterExample() {
	  String[] servers = new String[] {
	        "nats://localhost:4222",
	       
	    };
	  
	 

	    Runnable producer = () -> {
	      ConnectionFactory cf = new ConnectionFactory();
	      cf.setServers(servers);
	      try {
	        Connection nc = cf.createConnection();
	        System.out.println("Producer connected to: " + nc.getConnectedUrl());
	        long i = 0;
	        while (i<=15) {
	          nc.publish("TEST", String.valueOf(i).getBytes());
	          System.out.println("Published: " + i + " elements...");
	          if (i % 1000000 == 0) {
	            System.out.println("Published: " + i + " elements...");
	          }
	          i++;
	        }
	      } catch (Exception e) {
	        System.out.println(e.getMessage());
	      }
	    };

	    new Thread(producer).start();
	  }

	  public static void main(String[] args) {
	    new NatsSimpleClusterExample();
	  }
	}
