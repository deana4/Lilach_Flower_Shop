package il.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import il.client.SimpleClient;

import java.io.IOException;

public class App extends Application {
    private SimpleClient client = null;
    @Override
    public void start(Stage stage) throws IOException {
        try{
            client = SimpleClient.getClient();
            client.openConnection();

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("catalog.fxml"));
            Parent mainLayout = fxmlLoader.load();

            Scene scene = new Scene(mainLayout, 1000, 800);
            stage.setTitle("Catalog");
            stage.setScene(scene);
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
