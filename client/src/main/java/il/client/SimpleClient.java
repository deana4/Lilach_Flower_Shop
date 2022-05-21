package il.client;



import il.client.ocsf.AbstractClient;
import il.entities.Flower;
import il.entities.Message;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	private String lastMessage;

	@Override
	protected void handleMessageFromServer(Object msg) throws JSONException {

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

		Message message = (Message) msg;

		if(message.getMessage().equals("result login")){
			if(message.getUser()!=null){
				LoginController.setCorrectLogin(true);
			}
			else{
				LoginController.setCorrectLogin(false);
			}
			return;
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			//client = new SimpleClient("192.168.1.94", 3009);
			client = new SimpleClient("127.0.0.1", 3000);
		}
		return client;
	}

}
