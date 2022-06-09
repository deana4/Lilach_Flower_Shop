package il.client;

import il.client.events.*;
import il.client.ocsf.AbstractClient;
import il.entities.*;
import org.greenrobot.eventbus.EventBus;

import java.util.*;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg){
		Message message = (Message) msg;
		System.out.println("get message from server: "+ message.getMessage());


		if(message.getMessage().equals("item catalog list")){
			System.out.println("get init data");
			EventBus.getDefault().post(new CatalogItemsEvent(message.getListItem(), message.getListStors()));
		}

		if(message.getMessage().equals("result login")){
			LoginEvent eventlogIN = null;
			if(!message.isLoginStatus())
				EventBus.getDefault().post(new LoginEvent(false, message.getLoginResult()));
			else{
				if(!message.isWorker()){//user
					eventlogIN = new LoginEvent(true, message.getUser(), message.getListComplains(), message.getListOrder(), message.getListStors());
					eventlogIN.setId(message.getUser().getId());
					if(message.getPermision()==3 || message.getPermision()==5)
						eventlogIN.setStoreId(message.getStoreID());
				}
				else{//worker
					eventlogIN = new LoginEvent(message.getUsername(), message.getPermision());
					eventlogIN.setId(message.getIddatabase());
					eventlogIN.setPassword(message.getPass());
					switch (message.getPermision()){
						case 5://system admin send all information
							eventlogIN.setComplainList(message.getListComplains());
							eventlogIN.setOrderList(message.getListOrder());
							eventlogIN.setStoreEmploeey(message.getListEmploeeys());
							eventlogIN.setStoreUser(message.getListUsers());
							break;
						case 4://networkmaneger
							eventlogIN.setComplainList(message.getListComplains());
							eventlogIN.setOrderList(message.getListOrder());
							break;
						case 3:
							//report
							eventlogIN.setStoreId(message.getStoreID());
							eventlogIN.setComplainList(message.getListComplains());
							eventlogIN.setOrderList(message.getListOrder());
							break;
						case 2:
							eventlogIN.setComplainList(message.getListComplains());
							eventlogIN.setOrderList(message.getListOrder());
							break;
						case 1:
							eventlogIN.setStoreId(message.getStoreID());
							eventlogIN.setOrderList(message.getListOrder());
							break;
					}

				}
				EventBus.getDefault().post(eventlogIN);
			}
		}

		if(message.getMessage().equals("result register")){
			EventBus.getDefault().post(new RegisterEvent(message.isRegisterStatus(), message.getRegisterResult()));
		}
		if(message.getMessage().equals("result client info update")){
			EventBus.getDefault().post(new UpdateinfroEvent(message.getUodateResult(),message.getListUsers(),message.getListEmploeeys()));
		}
		if(message.getMessage().equals("result new Order")){
			EventBus.getDefault().post(new OrderEvent(message.getOrder()));
		}

	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient(App.ip, App.port);
		}
		return client;
	}

}
