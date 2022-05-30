package il.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import il.client.SimpleClient;
import javafx.stage.StageStyle;
//import org.kordamp.bootstrapfx.BootstrapFX;
//import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class App extends Application {
    private SimpleClient client = null;

    public static String ip = "132.74.210.7";
    public static int port = 3000;

    @Override
    public void start(Stage stage) throws IOException {
        try{
            System.out.println("----Run Lilach----\nip: " + ip + ", port: "+port);
            client = SimpleClient.getClient();
            client.openConnection();

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
            Parent mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout, 1300, 800);
            stage.setTitle("Lilach");
            MainPageController.getInstance().setStage(stage);

            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        catch (Exception e){
            client.closeConnection();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
