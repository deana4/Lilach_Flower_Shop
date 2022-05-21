package il.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterControl {

    public static void register(String name, String username, String pass, String id, String credit_card, String plan) throws JSONException, IOException {
        JSONObject cmd = new JSONObject();
        cmd.put("command", "register");
        cmd.put("name", name);
        cmd.put("username", username);
        cmd.put("pass", pass);
        cmd.put("id", id);
        cmd.put("credit_card", credit_card);
        cmd.put("plan", plan);

        System.out.println("send register requests to server:" + cmd.toString());

        SimpleClient.getClient().sendToServer(cmd.toString());
    }

}
