package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterController extends MainPageController{

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
    void initialize(){
        this.plan_chooser.getItems().add("Specific Store Member");
        this.plan_chooser.getItems().add("Store Wide Member");
        this.plan_chooser.getItems().add("Yearly Member");
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
            plan_chooser.setValue("Plan is not chosen");
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
                    case 0: {name_tf.setText("Name is Empty"); counter_of_correctness--; break;}
                    case 1: {username_tf.setText("UserName is Empty"); counter_of_correctness--; break;}
                    case 2: {pass_tf.setText("Password is Empty"); counter_of_correctness--; break;}
                    case 3: {id_tf.setText("ID is Empty"); counter_of_correctness--; break;}
                    case 4: {credit_card_tf.setText("creditCard is Empty"); counter_of_correctness--; break;}
                }
            }
        }

        //check if username already exist in the DB ==> if(in DB) {counter--;}
        //else{ nameCheck
        int name_check = nameCheck(name);
        if(name_check == 0){
            counter_of_correctness--;
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

        if(counter_of_correctness == 6){
            //send register details to the server
            String[] registerDetails = {name, username, pass, id, credit_card, plan};


            RegisterControl.register(name, username, pass, id ,credit_card, plan);

            while(currectRegister<0){
            }

            System.out.println("you register sucssesfuly");



            this.errorWarning.setVisible(false);
            //move to the catalog page
        }
        else{
            this.errorWarning.setVisible(true);
        }
        currectRegister=-1;
    }

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
            this.pass_tf.setText("|Password| should be at least 8");
            return 0;
        }
        char[] pass = password.toCharArray();
        for (int i=0; i<password.length(); i++){
            if((pass[i]<'A' || pass[i]>'Z') && (pass[i]<'a' || pass[i]>'z') && (pass[i]<'0' || pass[i]>'9')){
                this.pass_tf.setText("A-z and 0-9 symbols only");
                return 0;
            }
        }
        return 1;
    }

    private int creditCardCheck (String creditCard){
        //checks the length (need to be 16 exactly) and chars (need to contain only digits)
        if(creditCard.length()!=16){
            this.credit_card_tf.setText("Incorrect Credit Card");
            return 0;
        }
        char[] credit_card = creditCard.toCharArray();
        for(int i=0; i<creditCard.length(); i++){
            if(credit_card[i]<'0' || credit_card[i]>'9'){
                this.credit_card_tf.setText("Incorrect Credit Card");
                return 0;
            }
        }
        return 1;
    }

    private int idCheck (String id){
        //checks the length of the id (needs to be 9 exactly) and chars (need to contain only digits)
        if(id.length()!=9){
            this.id_tf.setText("Incorrect ID");
            return 0;
        }
        char[] id_char = id.toCharArray();
        for(int i=0; i<id.length(); i++){
            if(id_char[i]<'0' || id_char[i]>'9'){
                this.id_tf.setText("Incorrect ID");
                return 0;
            }
        }
        return 1;
    }

    private int nameCheck (String name){
        //check space in the name (which means that full name entered to te text field) and that the private anf family names are at least with 2 chars
        //name can contain only A-z
        if(!name.contains(" ")){
            this.name_tf.setText("Please enter your full name");
            return 0;
        }
        int space_index = name.indexOf(" ");
        if(space_index<=1 || space_index>=(name.length()-2)){
            this.name_tf.setText("Incorrect name");
            return 0;
        }
        char[] name_char = name.toCharArray();
        for(int i=0; i<name.length(); i++){
            if((name_char[i]<'A' || name_char[i]>'Z') && (name_char[i]<'a' || name_char[i]>'z') && (name_char[i] != ' ')){
                this.name_tf.setText("Name can contain A-z");
                return 0;
            }
        }
        return 1;
    }
}
