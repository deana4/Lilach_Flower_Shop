package il.client;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static java.lang.Integer.parseInt;

public class OrderController {

    @FXML
    private MFXTextField apartment_num_field;

    @FXML
    private MFXToggleButton bringDetailsChecker;

    @FXML
    private MFXButton cancelBTN;

    @FXML
    private MFXDatePicker date_picker;

    @FXML
    private MFXToggleButton deliveryChecker;

    @FXML
    private MFXToggleButton elseOrderChecker;

    @FXML
    private MFXTextField floor_num_field;

    @FXML
    private MFXToggleButton greetingChecker;

    @FXML
    private MFXTextField home_num_field;

    @FXML
    private MFXTextField my_credit_card_field;

    @FXML
    private MFXTextField my_mail_field;

    @FXML
    private MFXTextField my_name_field;

    @FXML
    private MFXComboBox<String> my_phone_choose;

    @FXML
    private MFXTextField my_phone_field;

    @FXML
    private MFXButton payBTN;

    @FXML
    private MFXTextField reciver_mail_field;

    @FXML
    private MFXTextField reciver_name_field;

    @FXML
    private MFXComboBox<String> reciver_phone_choose;

    @FXML
    private MFXTextField reciver_phone_field;

    @FXML
    private MFXTextField street_field;

    @FXML
    private Label sum_label;

    @FXML
    private MFXComboBox<String> time_choose;

    @FXML
    private MFXButton updateCartBTN;

    @FXML
    private Label reciver_name_label;

    @FXML
    private Label reciver_phone_label;

    @FXML
    private Label reciver_mail_label;

    @FXML
    private TextArea greeting_field;

    private int counter_else_order_clicks=0;

    private int counter_greeting_clicks=0;

    private int counter_delivery_clicks=0;

    private int counter_details_clicks=0;

    private int counter_correctness=0;

    @FXML
    private Label street_label;

    @FXML
    private Label home_num_label;

    @FXML
    private Label floor_num_label;

    @FXML
    private Label apartment_num_label;

    @FXML
    private AnchorPane order_first_pane;

    private MainPageController main_page_holder;

    private AddToCartController cart_controller;

    @FXML
    private AnchorPane order_pane2;

    @FXML
    private AnchorPane order_pane3;

    @FXML
    private AnchorPane order_pane4;

    @FXML
    private MFXTextField order_num_field;

    @FXML
    private MFXButton thankYouBTN;

    @FXML
    private MFXTextField city_field;

    @FXML
    private Label city_label;

    private boolean isDelivery;

    private boolean isPickUp;

    private boolean elseReciver;

    private boolean isGreeting;

    private double extra_for_delivery=30.0;

    @FXML
    private Label error_label;

    @FXML
    private MFXComboBox<String> store_chooser;

    @FXML
    void initialize() throws IOException {
        for(int i=10; i<=20; i++){
            this.time_choose.getItems().add(Integer.toString(i) + ":00");
            this.time_choose.getItems().add(Integer.toString(i) + ":30");
        }
        this.my_phone_choose.getItems().add("050");
        this.my_phone_choose.getItems().add("052");
        this.my_phone_choose.getItems().add("053");
        this.my_phone_choose.getItems().add("054");
        this.reciver_phone_choose.getItems().add("050");
        this.reciver_phone_choose.getItems().add("052");
        this.reciver_phone_choose.getItems().add("053");
        this.reciver_phone_choose.getItems().add("054");
        this.store_chooser.getItems().add("Store 1");
        this.store_chooser.getItems().add("Store 2");
        this.store_chooser.getItems().add("Store 3");
        this.store_chooser.getItems().add("Store 4");

        //if the user registered to spesific store he can only order from this store!!!!!!!!!!!!!!!!!
//        if(user.getplan()=="Store"){
//            this.store_chooser.setValue(user.getStore());
//            this.store_chooser.setDisable(true);
//        }

        this.greeting_field.setWrapText(true);
        this.sum_label.setText("0.00"); //change acoording to the cart
        //this.cart_controller.LoadCart();

        //bring the user details (name, credit card, mail-if exist, phone-if exist, address-if exist, plan, discount-if exist) as a field
        //check if there is an open order of the user and fill the fields according to it
    }

    @FXML
    void CancelBTNClicked(MouseEvent event) throws IOException, JSONException {
        //check in the server if there is an existing open order of this JSON. if there is delete from the DB this row
        JSONObject checkOpenOrder = readySendToServer(true);
        this.main_page_holder.LoadCatalogPage();
    }

    @FXML
    void PayBTNClicked(MouseEvent event) throws JSONException {

        //check correctness
        int counter = 0;
        int counter_general = checkGeneralOrder();
        if(elseReciver){
            int counter_reciver = checkReciver();
            if(counter_reciver!=4){
                this.error_label.setVisible(true);
                counter--;
               // return;
            }
//            else {
//                System.out.println("because of else we accept");
//                correctOrder();
//            }
        }
        if(isGreeting){
            int counter_greeting = checkGreeting();
            if(counter_greeting!=1){
                this.error_label.setVisible(true);
                counter--;
                //return;
            }
//            else {
//                System.out.println("because of greeting we accept");
//                correctOrder();
//            }
        }
        if(isDelivery){
            int counter_delivery = checkDelivery();
            if(counter_delivery!=5){
                this.error_label.setVisible(true);
                counter--;
                //return;
            }
//            else {
//                System.out.println("because of delivery we accept");
//                correctOrder();
//            }
        }
        if(counter_general!=8 || counter!=0){
            this.error_label.setVisible(true);
            return;
        }
        else {
            System.out.println("because of general we accept");
            correctOrder();
        }
    }

    @FXML
    void UpdateCartBTNClicked(ActionEvent event) throws IOException, JSONException {
        //send to server the open order for reconstruction
        JSONObject openToServer = readySendToServer(true);
        this.main_page_holder.LoadCartPage();
    }

    @FXML
    void bringDetailsClicked(MouseEvent event) {
        //get the details from the initialize we did here
        counter_details_clicks++;
        if(counter_details_clicks%2==1){
            this.my_name_field.setText("Liran Eliav");
            this.my_credit_card_field.setText("1234567890123456");
            String phone="0509446360";
            int third_digit = parseInt(String.valueOf(phone.charAt(2)));
            switch (third_digit){
                case 0: {this.my_phone_choose.setValue("050"); break;}
                case 2: {this.my_phone_choose.setValue("052"); break;}
                case 3: {this.my_phone_choose.setValue("053"); break;}
                case 4: {this.my_phone_choose.setValue("054"); break;}
            }
            this.my_phone_field.setText(phone.substring(3));
            this.my_mail_field.setText("lll@gmail.com");
            //address is from the type: String = address,city,home,floor,apartment
            String location = "Leon,Haifa,34,18,35";
            String[] parts = location.split(",");
            this.street_field.setText(parts[0]);
            this.city_field.setText(parts[1]);
            this.home_num_field.setText(parts[2]);
            this.floor_num_field.setText(parts[3]);
            this.apartment_num_field.setText(parts[4]);

        }
        else{
            this.my_name_field.setText("");
            this.my_credit_card_field.setText("");
            this.my_phone_choose.setValue("");
            this.my_phone_field.setText("");
            this.my_mail_field.setText("");
            this.street_field.setText("");
            this.city_field.setText("");
            this.home_num_field.setText("");
            this.floor_num_field.setText("");
            this.apartment_num_field.setText("");
        }
    }

    @FXML
    void deliveryClicked(MouseEvent event) {
        counter_delivery_clicks++;
        if(counter_delivery_clicks % 2 == 1){



            double new_sum=Double.parseDouble(this.sum_label.getText())+extra_for_delivery;
            this.sum_label.setText(String.valueOf(new_sum));
            isDelivery=true;
            isPickUp=false;
            this.street_field.setVisible(true);
            this.street_label.setVisible(true);
            this.home_num_field.setVisible(true);
            this.home_num_label.setVisible(true);
            this.floor_num_field.setVisible(true);
            this.floor_num_label.setVisible(true);
            this.apartment_num_field.setVisible(true);
            this.apartment_num_label.setVisible(true);
            this.city_field.setVisible(true);
            this.city_label.setVisible(true);
        }
        else {
            if(counter_delivery_clicks>1){
                double new_sum=Double.parseDouble(this.sum_label.getText())-extra_for_delivery;
                this.sum_label.setText(String.valueOf(new_sum));
            }
            isDelivery=false;
            isPickUp=true;
            this.street_field.setVisible(false);
            this.street_label.setVisible(false);
            this.home_num_field.setVisible(false);
            this.home_num_label.setVisible(false);
            this.floor_num_field.setVisible(false);
            this.floor_num_label.setVisible(false);
            this.apartment_num_field.setVisible(false);
            this.apartment_num_label.setVisible(false);
            this.city_field.setVisible(false);
            this.city_label.setVisible(false);
        }
    }

    @FXML
    void elseOrderClicked(MouseEvent event) {
        counter_else_order_clicks++;
        if(counter_else_order_clicks % 2 == 1) {
            elseReciver = true;
            this.reciver_name_field.setVisible(true);
            this.reciver_phone_choose.setVisible(true);
            this.reciver_phone_field.setVisible(true);
            this.reciver_mail_field.setVisible(true);
            this.reciver_name_label.setVisible(true);
            this.reciver_phone_label.setVisible(true);
            this.reciver_mail_label.setVisible(true);
        }
        else{
            counter_correctness = 0;
            this.payBTN.setDisable(false);
            this.error_label.setVisible(false);
            elseReciver=false;
            this.reciver_name_field.setVisible(false);
            this.reciver_phone_choose.setVisible(false);
            this.reciver_phone_field.setVisible(false);
            this.reciver_mail_field.setVisible(false);
            this.reciver_name_label.setVisible(false);
            this.reciver_phone_label.setVisible(false);
            this.reciver_mail_label.setVisible(false);
        }

    }

    @FXML
    void greetingClicked(MouseEvent event) {
        counter_greeting_clicks++;
        if(counter_greeting_clicks % 2 == 1){
            isGreeting=true;
            this.greeting_field.setVisible(true);
        }
        else{
            isGreeting=false;
            this.greeting_field.setVisible(false);
        }

    }


    @FXML
    void ThankYouBTNClicked(ActionEvent event) throws IOException {
        MainPageController.getInstance().OrderRefresh();
        this.main_page_holder.LoadHomePage();
    }

    public JSONObject readySendToServer(boolean isOpenOrder) throws JSONException {
        JSONObject toServer = new JSONObject();
        toServer.put("is_open_order",isOpenOrder);
        toServer.put("name", this.my_name_field.getText());
        toServer.put("credit_card",this.my_credit_card_field.getText());
        String phone = this.my_phone_choose.getSelectedItem() + this.my_phone_field.getText();
        toServer.put("phone", phone);
        toServer.put("mail", this.my_mail_field.getText().toLowerCase());
        toServer.put("get_date", this.date_picker.getText());
        toServer.put("get_time", this.time_choose.getSelectedItem());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String[] date_time = dtf.format(now).split(" ");
        toServer.put("order_date",date_time[0]);
        toServer.put("order_time", date_time[1]);
        toServer.put("sum",this.sum_label.getText());
        toServer.put("store", this.store_chooser.getSelectedItem());

        if(elseReciver){
            toServer.put("else_reciver", true);
            toServer.put("name_reciver", this.reciver_name_field.getText());
            String phone_reciver = this.reciver_phone_choose.getSelectedItem() + this.reciver_phone_field.getText();
            toServer.put("phone", phone_reciver);
            toServer.put("reciver_mail", this.reciver_name_field.getText().toLowerCase());
        }else{
            toServer.put("else_reciver", false);
            toServer.put("name_reciver", (Collection) null); //how to send null???
            toServer.put("phone", (Collection) null);
            toServer.put("reciver_mail", (Collection) null);
        }
        if(isDelivery){
            toServer.put("is_delivery",true);
            String location = this.street_field.getText()+","+this.city_field.getText()
                    +","+this.home_num_field.getText()+","+this.floor_num_field.getText()+","+this.apartment_num_field.getText();
            toServer.put("address",location);
            toServer.put("is_pickup",false);
        }
        else{
            toServer.put("is_delivery",false);
            toServer.put("address", (Collection) null);
            toServer.put("is_pickup",true);
        }
        if(isGreeting){
            toServer.put("is_greeting",true);
            toServer.put("greeting",this.greeting_field.getText());
        }
        else{
            toServer.put("is_greeting",false);
            toServer.put("greeting", (Collection) null);
        }


        return toServer;
    }

    //gets and sets

    public void setSum_label(String sum_label) {
        this.sum_label.setText(sum_label);
    }

    public void setMain_page_holder(MainPageController main_page_holder) {
        this.main_page_holder = main_page_holder;
    }

    public void setCart_controller(AddToCartController cart_controller) {
        this.cart_controller = cart_controller;
    }

    //end gets and sets

    //correctness functions

    private int nameCheck (String name, MFXTextField field_to_check){
        //check space in the name (which means that full name entered to te text field) and that the private anf family names are at least with 2 chars
        //name can contain only A-z
        if(name.equals("Please enter your full name") || name.equals("Incorrect name") || name.equals("Name can contain A-z")){
            field_to_check.clear();
            field_to_check.setPromptText("");
            return 0;
        }
        if(!name.contains(" ")){
            field_to_check.clear();
            field_to_check.setPromptText("Please enter your full name");
            return 0;
        }
        int space_index = name.indexOf(" ");
        if(space_index<=1 || space_index>=(name.length()-2)){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect name");
            return 0;
        }
        char[] name_char = name.toCharArray();
        for(int i=0; i<name.length(); i++){
            if((name_char[i]<'A' || name_char[i]>'Z') && (name_char[i]<'a' || name_char[i]>'z') && (name_char[i] != ' ')){
                field_to_check.clear();
                field_to_check.setPromptText("Name can contain A-z");
                return 0;
            }
        }
        return 1;
    }

    private int creditCardCheck (String creditCard){
        //checks the length (need to be 16 exactly) and chars (need to contain only digits)
        if(creditCard.length()!=16){
            this.my_credit_card_field.clear();
            this.my_credit_card_field.setPromptText("Incorrect Credit Card");
            return 0;
        }
        char[] credit_card = creditCard.toCharArray();
        for(int i=0; i<creditCard.length(); i++){
            if(credit_card[i]<'0' || credit_card[i]>'9'){
                this.my_credit_card_field.clear();
                this.my_credit_card_field.setPromptText("Incorrect Credit Card");
                return 0;
            }
        }
        return 1;
    }

    private int phoneCheck (String phone, MFXTextField field_to_check, MFXComboBox<String> comboBox_to_check) {
        //checks if kidumet was chosen
        //checks the length (need to be 7 exactly) and chars (need to contain only digits)
        int num_error = 0;
        if (comboBox_to_check.getValue() == null || comboBox_to_check.getValue() == "" || comboBox_to_check.getValue() == "miss Kidumet") {
            comboBox_to_check.setValue("miss Kidumet");
            num_error++;
        }
        if (phone.length() != 7) {
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect phone number");
            num_error++;
        } else {
            char[] phone_number = phone.toCharArray();
            for (int i = 0; i < phone.length(); i++) {
                if (phone_number[i] < '0' || phone_number[i] > '9') {
                    field_to_check.clear();
                    field_to_check.setPromptText("Incorrect phone number");
                    num_error++;
                }
            }
        }
        System.out.println("num error phone "+num_error);
        return 2-num_error;
    }

    private int mailCheck (String mail, MFXTextField field_to_check){
        //check @ in the mail and that the before and after the @ (till the dot) are at least with 2 chars
        //check that there is a dot after the @
        //mail can contain only A-z/digits
        if(!mail.contains("@") || !mail.contains(".")){
            field_to_check.clear();
            field_to_check.setPromptText("Please enter your full mail");
            return 0;
        }
        int at_index = mail.indexOf("@");
        if(at_index<=1 || at_index>=(mail.length()-2)){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect mail");
            return 0;
        }
        int dot_index = mail.indexOf(".");
        if(dot_index - at_index < 2){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect mail");
            return 0;
        }
        if(dot_index>=(mail.length()-2)){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect mail");
            return 0;
        }
        char[] mail_char = mail.toCharArray();
        //before the @
        for(int i=0; i<at_index; i++){
            if((mail_char[i]<'A' || mail_char[i]>'Z') && (mail_char[i]<'a' || mail_char[i]>'z') && (mail_char[i] < '0' || mail_char[i] > '9')){
                field_to_check.clear();
                field_to_check.setPromptText("Mail can contain only A-z or digits");
                return 0;
            }
        }
        //after the @
        for(int i=at_index+1; i<mail_char.length; i++){
            if((mail_char[i]<'A' || mail_char[i]>'Z') && (mail_char[i]<'a' || mail_char[i]>'z') && (mail_char[i] != '.')){
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect mail");
                return 0;
            }
        }
        return 1;
    }

    private int dateCheck (){
        //checks if we chose a date to the order
        if(this.date_picker.getValue()==null){
            this.date_picker.setText("Incorrect Date");
            return 0;
        }
        System.out.println(this.date_picker.getValue());
        return 1;
    }

    private int timeCheck(){
        if(this.time_choose.getValue()==null || this.time_choose.getValue()=="No Time"){
            this.time_choose.setValue("No Time");
            return 0;
        }
        return 1;
    }

    private int storeCheck(){
        if(this.store_chooser.getValue()==null || this.store_chooser.getValue()=="No Store"){
            this.store_chooser.setValue("No Store");
            return 0;
        }
        return 1;
    }

    private int greetingCheck(){
        System.out.println("greeting text "+this.greeting_field.getText());
        if(this.greeting_field.getText()==null || this.greeting_field.getText().equals("No greeting entered")||this.greeting_field.getText().equals("")){
            this.greeting_field.clear();
            this.greeting_field.setPromptText("No greeting entered");
            return 0;
        }
        return 1;
    }

    private int addressCheck (String address, MFXTextField field_to_check, String streetOrCity) {
        //length of street need to be at least 2. if there is a space it need to be at lest 2 chars before and at least 2 chars after
        //name can contain only A-z
        if(address.equals("")){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect "+streetOrCity);
            return 0;
        }
        if (address.contains("Please enter your ") || address.contains("Incorrect ") || address.contains(" can contain A-z")) {
            field_to_check.clear();
            field_to_check.setPromptText("");
            return 0;
        }
        char[] address_char = address.toCharArray();
        if(!address.contains(" ")){
            if(address.length()<2){
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect "+streetOrCity);
                return 0;
            }
        }
        if (address.contains(" ")) {
            int space_index = address.indexOf(" ");
            if (space_index <= 1 || space_index >= (address.length() - 2)) {
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect "+streetOrCity);
                return 0;
            }
            if(address_char[space_index+1]==' '){
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect "+streetOrCity);
                return 0;
            }

        }
        for(int i=0; i<address.length(); i++){
            if((address_char[i]<'A' || address_char[i]>'Z') && (address_char[i]<'a' || address_char[i]>'z') && (address_char[i] != ' ')){
                field_to_check.clear();
                field_to_check.setPromptText(streetOrCity+" can contain A-z");
                return 0;
            }
        }
        return 1;
    }

    private int homeCheck (String home, MFXTextField field_to_check) {
        //only digits and letters. digits befor letters
        if (field_to_check.getText() == null || home.equals("")) {
            field_to_check.clear();
            field_to_check.setPromptText("Please enter your home number");
            return 0;
        }
        if (home.equals("Please enter your home number") || home.equals("Incorrect home number") || home.equals("Home number can contain only A-z or digits")) {
            field_to_check.clear();
            field_to_check.setPromptText("");
            return 0;
        }

        char[] home_char = home.toCharArray();
        for (int i = 0; i < home_char.length - 1; i++) {
            if ((home_char[i] < '0' || home_char[i] > '9')) {
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect home number");
                return 0;
            }
        }
        int last_char = home_char.length - 1;
        if (last_char != 0) {
            if ((home_char[last_char] < 'A' || home_char[last_char] > 'Z')
                    && (home_char[last_char] < 'a' || home_char[last_char] > 'z')
                    && (home_char[last_char] < '0' || home_char[last_char] > '9')) {
                field_to_check.clear();
                field_to_check.setPromptText("Home number can contain only A-z or digits");
                return 0;
            }
        } else {
            if ((home_char[0] < '0' || home_char[0] > '9')) {
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect home number");
                return 0;
            }
        }
        return 1;
    }

    private int apartmentFloorCheck (String number, MFXTextField field_to_check, String apartmentOrFloor){
        //checks the chars (need to contain only digits)
        if(field_to_check.getText()==null || field_to_check.getText().equals("")||field_to_check.getText().contains("Incorrect ")){
            field_to_check.clear();
            field_to_check.setPromptText("Incorrect "+apartmentOrFloor);
            return 0;
        }
        char[] number_char = number.toCharArray();
        for(int i=0; i<number.length(); i++){
            if(number_char[i]<'0' || number_char[i]>'9'){
                field_to_check.clear();
                field_to_check.setPromptText("Incorrect "+apartmentOrFloor);
                return 0;
            }
        }
        return 1;
    }

    private int checkGeneralOrder(){
        int correct = 0;
        correct += nameCheck(this.my_name_field.getText(), this.my_name_field);
        System.out.println("because of name "+correct);
        correct += creditCardCheck(this.my_credit_card_field.getText());
        System.out.println("because of credit card "+correct);
        correct += phoneCheck(this.my_phone_field.getText(), this.my_phone_field, this.my_phone_choose); //checks 2 things
        System.out.println("because of phone "+correct);
        correct += mailCheck(this.my_mail_field.getText(), this.my_mail_field);
        System.out.println("because of mail "+correct);
        correct += dateCheck();
        System.out.println("because of date "+correct);
        correct += timeCheck();
        System.out.println("because of time "+correct);
        correct += storeCheck();
        System.out.println("because of store "+correct);
        System.out.println("total "+correct);
        return correct;
    }

    private int checkReciver(){
        int correct = 0;
        correct += nameCheck(this.reciver_name_field.getText(), this.reciver_name_field);
        System.out.println("because of reciver name "+correct);
        correct += phoneCheck(this.reciver_phone_field.getText(), this.reciver_phone_field, this.reciver_phone_choose); //checks 2 things
        System.out.println("because of reciver phone "+correct);
        correct += mailCheck(this.reciver_mail_field.getText(), this.reciver_mail_field);
        System.out.println("because of reciver mail "+correct);
        System.out.println("total reciver "+correct);
        return correct;
    }

    private int checkGreeting(){
        int correct = 0;
        correct += greetingCheck();
        System.out.println("because of greeting "+correct);
        System.out.println("total greeting "+correct);
        return correct;
    }

    private int checkDelivery(){
        int correct = 0;
        correct += addressCheck(this.street_field.getText(), this.street_field, "Street");
        System.out.println("because of street "+correct);
        correct += addressCheck(this.city_field.getText(), this.city_field, "City");
        System.out.println("because of city "+correct);
        correct += homeCheck(this.home_num_field.getText(), this.home_num_field);
        System.out.println("because of home "+correct);
        correct += apartmentFloorCheck(this.floor_num_field.getText(), this.floor_num_field, "floor");
        System.out.println("beacuse of floor "+correct);
        correct += apartmentFloorCheck(this.apartment_num_field.getText(), this.apartment_num_field, "apartment");
        System.out.println("because of apartment "+correct);
        System.out.println("total delivery "+correct);
        return correct;
    }

    private void correctOrder() throws JSONException {
        //send to server the order details
        JSONObject finishOrder = readySendToServer(false);
        this.order_pane2.setVisible(false);
        this.order_pane3.setVisible(false);
        this.order_pane4.setVisible(true);
        //get from server the order number of the order we just created!!!!
        this.order_num_field.setText("1234");
    }



}
