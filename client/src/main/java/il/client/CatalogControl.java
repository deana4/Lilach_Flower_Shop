package il.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class CatalogControl {
    public static void getItemsList() throws IOException, ClassNotFoundException, InterruptedException, JSONException {
        JSONObject cmd = new JSONObject();
        cmd.put("command", "getCatalogItems");
        SimpleClient.getClient().sendToServer(cmd.toString());
        TimeUnit.SECONDS.sleep(3);//need to wait to the server, need to use lock
    }

    public static void updateImage(String url, int id) throws IOException {

        File file;
        file = new File(url);
        byte[] bFile = new byte[(int) file.length()];
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            String base64String = Base64.getEncoder().encodeToString(bFile);
            JSONObject cmd = new JSONObject();
            cmd.put("command", "setImages");
            cmd.put("id", id);
            cmd.put("newImage", base64String);
            SimpleClient.getClient().sendToServer(cmd.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: load image in updateImage!");
        }
    }

    public static void setPrice(String idItemn, double newPrice) throws IOException, JSONException {
        JSONObject cmd = new JSONObject();
        cmd.put("command", "setPrice");
        cmd.put("id", idItemn);
        cmd.put("newPrice", newPrice);
        SimpleClient.getClient().sendToServer(cmd);
    }
}