package il.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LogInControl {

    public static void logIn(String username, String pass, boolean isWorker) throws JSONException, IOException {
        JSONObject cmd = new JSONObject();
        cmd.put("command", "login");
        cmd.put("username", username);
        cmd.put("pass", pass);
        cmd.put("isWorker", isWorker);

        System.out.println("try log in:" + cmd.toString());

        SimpleClient.getClient().sendToServer(cmd.toString());
    }
}
