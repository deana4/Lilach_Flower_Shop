package il.client;


import il.client.controls.LogInControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.stage.StageStyle;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App extends Application {
    private SimpleClient client = null;

    public static String ip = "127.0.0.1";
    public static int port = 3000;

    public static Stage AppStage;

    public static void generateAddres(){
        System.out.println("----Lilach GUI----");
        Scanner myObj = new Scanner(System.in);
        String init;
        System.out.println("Server IP: ");
        ip = myObj.nextLine();
        System.out.println("Server port: ");
        port = myObj.nextInt();
    }

    @Override
    public void start(Stage stage) throws IOException {

        try{
            generateAddres();
            System.out.println("----Run Lilach----\nip: " + ip + ", port: "+port);

            client = SimpleClient.getClient();
            client.openConnection();
            AppStage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
            Parent mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout, 1300, 800);
            stage.setTitle("Lilach");
            MainPageController.getInstance().setStage(AppStage);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
//            stage.initStyle(StageStyle.UNDECORATED);
//            test erea
            stage.show();
            MainPageController.getInstance().LoadHomePage();
        }
        catch (Exception e){
            client.closeConnection();
        }
    }
    public static Stage getAppStage(){
        return App.AppStage;
    }
    public static void main(String[] args) {
        launch();
    }

    public static void refreshSystemCompletely(boolean bool) throws IOException {
        if(bool){
            LogInControl.logout(UserClient.getInstance().getId());
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainPage.fxml"));
            Parent mainLayout = fxmlLoader.load();
            Scene scene = new Scene(mainLayout, 1300, 800);
            AppStage.setTitle("Lilach");
            MainPageController.getInstance().setStage(AppStage);
            scene.setFill(Color.TRANSPARENT);
            AppStage.setScene(scene);
        }else{
            System.out.println("Not refreshing due to request");
        }
    }
}
