package il.client;



import il.client.MainPages.CatalogController;
import il.client.ocsf.AbstractClient;
import il.entities.Flower;

import java.util.*;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	private String lastMessage;

	@Override
	protected void handleMessageFromServer(Object msg) {
		if(msg.getClass().equals(String.class)){
			System.out.println("get messeg from server: "+ msg.toString());
			lastMessage=msg.toString();
			return;
		}
		if(msg.getClass().equals(LinkedList.class)){
			System.out.println("get Flower object!");
			CatalogController.setFlowerlist((LinkedList<Flower>)msg);
			lastMessage="";
			return;
		}
		lastMessage="";

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			//client = new SimpleClient("192.168.1.94", 3009);
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
