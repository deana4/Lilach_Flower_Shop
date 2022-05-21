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
import java.util.List;

public class SimpleServer extends AbstractServer {

    public SimpleServer(int port) throws Exception {
        super(port);
        System.out.println("Server listen on port:" + port);
//        testDB.initMySQL();
    }

    public void closeServer() throws IOException {
        testDB.closeSession();
        this.close();
    }


    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        try {
            System.out.print(client.getInetAddress() + ":");
            String msgString = msg.toString();
            System.out.println("get message: " + msgString);

            JSONObject cmd = new JSONObject(msgString);
            Message message = new Message("");
            if (cmd.getString("command").equals("getCatalogItems")) {
                List<Flower> flowerlist = CatalogControl.getAllItems();
                client.sendToClient(flowerlist);
                System.out.println("send Flowers to catalog");
            }
            if(cmd.getString("command").equals("setPrice")){
                int id = cmd.getInt("id");
                int price = cmd.getInt("newPrice");
                CatalogControl.setPrice(id, price);
            }
            if(cmd.getString("command").equals("setImages")){
                int id = cmd.getInt("id");
                String bytes64 = cmd.getString("newImage");
                byte[] bFile = Base64.getDecoder().decode(bytes64);
                CatalogControl.setImage(id, bFile);
            }


            if(cmd.getString("command").equals("register")){
                String username = cmd.getString("username");
                String name = cmd.getString("name");
                String pass = cmd.getString("pass");
                String id = cmd.getString("id");
                String credit_card = cmd.getString("credit_card");
                String plan = cmd.getString("plan");

                User newUser = new User(username, pass,credit_card, plan, name, id);

                RegisterControl.register(newUser);


                System.out.println("get register request:" + username);
            }

            if(cmd.getString("command").equals("login")){
                String username = cmd.getString("username");
                String pass = cmd.getString("pass");
                boolean isWorker = cmd.getBoolean("isWorker");

                User logInUser = LoginControl.tryLogIn(username, pass, isWorker);

                if (logInUser!=null){
                    System.out.println("successfully! login: "+ username);

                    message.setMessage("result login");
                    message.setUser(logInUser);

                    client.sendToClient(message);
                }
                else{
                    System.out.println("faild! login: "+ username);
                    client.sendToClient(false);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("handleMessageFromClient Error!" + client.getInetAddress());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
