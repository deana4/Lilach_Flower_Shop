package il.server;

import il.entities.Flower;
import il.entities.Message;
import il.entities.User;
import il.server.ocsf.ConnectionToClient;
import il.server.ocsf.AbstractServer;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

public class SimpleServer extends AbstractServer {

    public SimpleServer(int port) throws Exception {
        super(port);
        System.out.println("Server listen on port:" + port);
        //testDB.initMySQL();
    }

    public void closeServer() throws IOException {
        testDB.closeSession();
        this.close();
    }


    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        try {
            Message message  = (Message) msg;

            Message sendMessage = new Message("");

            if(message.getMessage().equals("login")){
                String username = message.getUsername();
                String pass = message.getPass();
                boolean isWorker = message.isWorker();

                String result = LoginControl.checkLogin(username, pass, isWorker);

                if (result.equals("")){
                    System.out.println("successfully! login: "+ username);

                    sendMessage.setMessage("result login");
                    sendMessage.setLoginStatus(true);
                    sendMessage.setLoginResult("login was successful");
                    client.sendToClient(sendMessage);
                }
                else{
                    System.out.println("faild! login: "+ username);
                    sendMessage.setMessage("result login");
                    sendMessage.setLoginStatus(false);
                    sendMessage.setLoginResult(result);
                    client.sendToClient(sendMessage);
                }
            }


            if (message.getMessage().equals("getCatalogItems")) {
                sendMessage.setMessage("item catalog list");
                sendMessage.setListItem((LinkedList<Flower>) CatalogControl.getAllItems());
                client.sendToClient(sendMessage);
                System.out.println("send Flowers to catalog");
            }
            if(message.getMessage().equals("setPrice")){
                int id = message.getIdItem();
                double price = message.getNewPrice();
                CatalogControl.setPrice(id, price);
            }
//            if(message.getMessage().equals("setImages")){
//                int id = cmd.getInt("id");
//                String bytes64 = cmd.getString("newImage");
//                byte[] bFile = Base64.getDecoder().decode(bytes64);
//                CatalogControl.setImage(id, bFile);
//            }


            if(message.getMessage().equals("register")){
                String username = message.getUsername();
                String name = message.getName();
                String pass = message.getPass();
                String id = message.getId();
                String credit_card = message.getCredit_card();
                String plan = sendMessage.getPlan();

                User newUser = new User(username, pass,credit_card, plan, name, id);
                System.out.println("get register request:" + username);

                String result = RegisterControl.checknewUser(newUser);


                if(result.equals("")){
                    if(RegisterControl.register(newUser)){
                        sendMessage.setRegisterStatus(true);
                        sendMessage.setRegisterResult("user as been register!");
                    }
                    else{
                        sendMessage.setRegisterStatus(false);
                        sendMessage.setRegisterResult("Error: somethings wrong with the database.");
                    }
                }
                else{
                    sendMessage.setRegisterStatus(false);
                    sendMessage.setRegisterResult(result);
                }

                sendMessage.setMessage("result register");
                client.sendToClient(sendMessage);
            }



        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("handleMessageFromClient Error!" + client.getInetAddress());
        }

    }
}
