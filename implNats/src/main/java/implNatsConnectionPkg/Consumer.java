package implNatsConnectionPkg;

import java.util.concurrent.CountDownLatch;

import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;

public class Consumer {
	
	public static void main(String[] args)
	{
		String[] servers = new String[] {
		        "nats://localhost:4222",
		       
		    };
	
	 final CountDownLatch latch = new CountDownLatch(1);
	    Runnable consumer = () -> {
	      ConnectionFactory cf = new ConnectionFactory();
	      cf.setServers(servers);

	      try {
	    	
	        Connection nc = cf.createConnection();
	        System.out.println("Consumer connected to: " + nc.getConnectedUrl());
	        nc.subscribe("TEST", m -> System.out.println(new String(m.getData())));
	        System.out.println("Subscribed : "  + " elements...");
	        nc.flush();
	        
	      } catch (Exception e) {
	        System.out.println(e.getMessage());
	      }
	    };

	    new Thread(consumer).start();

}
}
