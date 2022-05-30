package il.client;


import il.client.events.RegisterEvent;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterController extends ParentClass{

    public static void setCurrectRegister(int currectRegister) {
        RegisterController.currectRegister = currectRegister;
    }

    public static int currectRegister = -1;

    @FXML
    private MFXTextField credit_card_tf;

    @FXML
    private Label errorWarning;

    @FXML
    private MFXTextField id_tf;

    @FXML
    private MFXTextField name_tf;

    @FXML
    private MFXTextField pass_tf;

    @FXML
    private MFXComboBox<String> plan_chooser;

    @FXML
    private MFXButton reg_btn;

    @FXML
    private MFXTextField username_tf;

    @FXML
    private MFXComboBox<String> store_choose;

    private MainPageController main_controller;

    @FXML
    void initialize(){
        { //initialize combobox
            EventBus.getDefault().register(this);
            this.plan_chooser.getItems().add("Specific Store Member");
            this.plan_chooser.getItems().add("Store Wide Member");
            this.plan_chooser.getItems().add("Yearly Member");
            this.store_choose.getItems().add("Store 1");
            this.store_choose.getItems().add("Store 2");
            this.store_choose.getItems().add("Store 3");
            this.store_choose.getItems().add("Store 4");
        }
    }

    @FXML
    void PlanChooserClicked(ActionEvent event) {
        if(this.plan_chooser.getValue()=="Store Wide Member" || this.plan_chooser.getValue()=="Yearly Member"){
            this.store_choose.setValue("All Stores");
            this.store_choose.setDisable(true);
        }
        else{
            this.store_choose.setValue("Choose Store");
            this.store_choose.setDisable(false);
        }

    }

    @FXML
    void RegisterBTNClicked(ActionEvent event) throws JSONException, IOException {
        int counter_of_correctness = 6;
        String name = this.name_tf.getText();
        String username = this.username_tf.getText();
        String pass = this.pass_tf.getText();
        String id = this.id_tf.getText();
        String credit_card = this.credit_card_tf.getText();
        String plan;
        if(this.plan_chooser.getValue()==null){
            plan_chooser.setValue("No plan");
            plan="";
            counter_of_correctness--;
        }
        else {
            plan = this.plan_chooser.getValue().toString();
        }

        String[] settings = {name, username, pass, id, credit_card};
        boolean[] result = isEmpty(settings);

        for(int i=0; i<result.length; i++){
            if(result[i]) {
                switch (i) {
                    case 0: {name_tf.setPromptText("Name is Empty"); counter_of_correctness--; break;}
                    case 1: {username_tf.setPromptText("UserName is Empty"); counter_of_correctness--; break;}
                    case 2: {pass_tf.setPromptText("Password is Empty"); counter_of_correctness--; break;}
                    case 3: {id_tf.setPromptText("ID is Empty"); counter_of_correctness--; break;}
                    case 4: {credit_card_tf.setPromptText("creditCard is Empty"); counter_of_correctness--; break;}
                }
            }
        }

        //check if username already exist in the DB ==> if(in DB) {counter--;}
        //else{ nameCheck
        int name_check = nameCheck(name);
        if(name_check == 0){
            counter_of_correctness--;
        }else{
            this.name_tf.setText(name);
        }

        int pass_check = passwordCheck(pass);
        if(pass_check == 0){
            counter_of_correctness--;
        }

        int id_check = idCheck(id);
        if(id_check == 0){
            counter_of_correctness--;
        }

        int credit_card_check = creditCardCheck(credit_card);
        if(credit_card_check == 0){
            counter_of_correctness--;
        }

        int store_check = storeCheck(this.plan_chooser.getValue());
        if(store_check == 0){
            counter_of_correctness--;
        }

        if(counter_of_correctness == 7){
            //send register details to the server
            String store = this.store_choose.getValue();
            String[] registerDetails = {name, username, pass, id, credit_card, plan,store};

            //need to add store here
            RegisterControl.register(name, username, pass, id ,credit_card, plan);
        }
        else{
            this.errorWarning.setVisible(true);
        }
        currectRegister=-1;
    }


    @Subscribe
    public void registerComplite(RegisterEvent event){
        Platform.runLater(()->{
            if(event.isStatusRegister()){
                System.out.println("you register sucssesfuly");
                this.errorWarning.setVisible(false);
                //move to the login page

                try {
                    this.main_controller.LoadLoginPage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                String warning = event.getResult();//this string has the warning to show to the client
                System.out.println(warning);
                plan_chooser.setValue(warning);
                this.errorWarning.setVisible(true);
            }
            currectRegister=-1;
        });
    }

    /*checkers*/



    private boolean[] isEmpty(String[] settings){
        boolean result[] = new boolean[5];
        for(int i=0; i<settings.length; i++){
            result[i]= (settings[i] == "");
        }
        return result;
    }

    private int passwordCheck(String password){
        //needs to check length (bigger then 8), chars (A-z0-9),
        if(password.length()<8){
            this.pass_tf.clear();
            this.pass_tf.setPromptText("|Password| should be at least 8");
            return 0;
        }
        char[] pass = password.toCharArray();
        for (int i=0; i<password.length(); i++){
            if((pass[i]<'A' || pass[i]>'Z') && (pass[i]<'a' || pass[i]>'z') && (pass[i]<'0' || pass[i]>'9')){
                this.pass_tf.clear();
                this.pass_tf.setPromptText("A-z and 0-9 symbols only");
                return 0;
            }
        }
        return 1;
    }

    private int creditCardCheck (String creditCard){
        //checks the length (need to be 16 exactly) and chars (need to contain only digits)
        if(creditCard.length()!=16){
            this.credit_card_tf.clear();
            this.credit_card_tf.setPromptText("Incorrect Credit Card");
            return 0;
        }
        char[] credit_card = creditCard.toCharArray();
        for(int i=0; i<creditCard.length(); i++){
            if(credit_card[i]<'0' || credit_card[i]>'9'){
                this.credit_card_tf.clear();
                this.credit_card_tf.setPromptText("Incorrect Credit Card");
                return 0;
            }
        }
        return 1;
    }

    private int idCheck (String id){
        //checks the length of the id (needs to be 9 exactly) and chars (need to contain only digits)
        if(id.length()!=9){
            this.id_tf.clear();
            this.id_tf.setPromptText("Incorrect ID");
            return 0;
        }
        char[] id_char = id.toCharArray();
        for(int i=0; i<id.length(); i++){
            if(id_char[i]<'0' || id_char[i]>'9'){
                this.id_tf.clear();
                this.id_tf.setPromptText("Incorrect ID");
                return 0;
            }
        }
        return 1;
    }

    private int nameCheck (String name){
        //check space in the name (which means that full name entered to te text field) and that the private anf family names are at least with 2 chars
        //name can contain only A-z
        if(!name.contains(" ")){
            this.name_tf.clear();
            this.name_tf.setPromptText("Please enter your full name");
            return 0;
        }
        int space_index = name.indexOf(" ");
        if(space_index<=1 || space_index>=(name.length()-2)){
            this.name_tf.clear();
            this.name_tf.setPromptText("Incorrect name");
            return 0;
        }
        char[] name_char = name.toCharArray();
        for(int i=0; i<name.length(); i++){
            if((name_char[i]<'A' || name_char[i]>'Z') && (name_char[i]<'a' || name_char[i]>'z') && (name_char[i] != ' ')){
                this.name_tf.clear();
                this.name_tf.setPromptText("Name can contain A-z");
                return 0;
            }
        }
        return 1;
    }

    private int storeCheck(String plan) {
        if (plan == "Specific Store Member") {
            if (this.store_choose.getValue() == null || this.store_choose.getValue() == "No Store" || this.store_choose.getValue() == "All Stores" || this.store_choose.getValue() == "Choose Store") {
                this.store_choose.setDisable(false);
                this.store_choose.setValue("No Store");
                return 0;
            }

        }
        return 1;
    }


    public void setMain_controller(MainPageController main_controller) {
        this.main_controller = main_controller;
    }

}
