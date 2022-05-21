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
	protected void handleMessageFromServer(Object msg){
		Message message = (Message) msg;
		System.out.println("get message from server: "+ message.getMessage());


		if(message.getMessage().equals("item catalog list")){
			System.out.println("get Flower object!");
			CatalogController.setFlowerlist(message.getListItem());
			return;
		}

		if(message.getMessage().equals("result login")){
			if(message.getUser()!=null){
				LoginController.setCorrectLogin(true);
			}
			else{
				LoginController.setCorrectLogin(false);
			}
			return;
		}

		if(message.getMessage().equals("result register")){
			if(message.isRegisterStatus()==true)
				RegisterController.setCurrectRegister(1);
			else
				RegisterController.setCurrectRegister(0);
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient(App.ip, App.port);
		}
		return client;
	}

}
