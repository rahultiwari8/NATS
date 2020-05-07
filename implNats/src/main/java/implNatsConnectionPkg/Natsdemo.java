package implNatsConnectionPkg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import io.nats.client.Connection;
import io.nats.client.Connection.Status;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;

public class Natsdemo {
	
	
	public static void main(String[] args) throws IllegalStateException, InterruptedException {
		
		Connection nc = null;
		try {
			nc = Nats.connect("nats://localhost:4222");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Status s=nc.getStatus();
		
		System.out.println(s.toString());
	
		
		nc.publish("subject", "hello world".getBytes(StandardCharsets.UTF_8));
		System.out.println(s.toString());
		
		Subscription sub = nc.subscribe("subject");
		Message msg = sub.nextMessage(Duration.ofMillis(500));

		String response = new String(msg.getData(), StandardCharsets.UTF_8);
		
		System.out.println("Response is ->> "+ response);
	}
	
	
	
	
	
}
