package il.client;

import il.client.DiffClasses.ComplaintClient;
import il.client.controls.OrderControl;
import il.client.controls.ReportControl;
import il.entities.Store;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class RevenueReportController {

    @FXML
    private MFXDatePicker end_date_store1;

    @FXML
    private MFXDatePicker end_date_store2;

    @FXML
    private MFXDatePicker start_date_store1;

    @FXML
    private MFXDatePicker start_date_store2;

    @FXML
    private MFXComboBox<String> store1_chooser;

    @FXML
    private MFXComboBox<String> store2_chooser;

    @FXML
    private MFXTextField sum_store1_text;

    @FXML
    private MFXTextField sum_store2_text;

    private double sum=0;


    @FXML
    private MFXButton store2;

    @FXML
    void initialize(){
        if(UserClient.getInstance().getPriority() == 5){
            this.store2_chooser.setVisible(false);
            this.start_date_store2.setVisible(false);
            this.end_date_store2.setVisible(false);
            this.sum_store2_text.setVisible(false);
            this.store2.setVisible(false);
            this.store1_chooser.setVisible(false);
            int store_id = UserClient.getInstance().getStoreId();
            String store="";
//            if(store_id == 1) {store="Haifa";}
//            if(store_id == 2) {store="Tel Aviv";}
//            if(store_id == 3) {store="Jerusalem";}
            System.out.println(store);
            this.store1_chooser.setValue(store);
            this.store1_chooser.setDisable(true);
        }
        this.sum_store1_text.setEditable(false);
        this.sum_store2_text.setEditable(false);

        this.store1_chooser.getItems().add("All");

        for(Store s: MainPageController.allStores){
            this.store1_chooser.getItems().add(s.getAddress());
            this.store2_chooser.getItems().add(s.getAddress());
        }
//        this.store1_chooser.getItems().add("Haifa");
//        this.store1_chooser.getItems().add("Tel Aviv");
//        this.store1_chooser.getItems().add("Jerusalem");
//        this.store2_chooser.getItems().add("All");
//        this.store2_chooser.getItems().add("Haifa");
//        this.store2_chooser.getItems().add("Tel Aviv");
//        this.store2_chooser.getItems().add("Jerusalem");
    }

    @FXML
    void Store1Chooser(ActionEvent event) {

    }

    @FXML
    void Store2Chooser(ActionEvent event) {

    }

    @FXML
    void store1ApplyClicked(ActionEvent event) {
        sum = 0.0;
        LocalDate start_date_store1 = this.start_date_store1.getValue();
        LocalDate end_date_store1 = this.end_date_store1.getValue();
        LocalDate startDate = start_date_store1;
        LocalDate endDate = end_date_store1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");

        String start_store1 = formatter.format(startDate);
        String end_store1 = formatter.format(endDate);
        String store = this.store1_chooser.getValue();
        int store_id=-2;
        for(Store s: MainPageController.allStores){
            if(s.getAddress().equals(store))
                store_id = s.getId();
        }
        if(store.equals("All")) {store_id=-1;}
        System.out.println(store_id);
        ReportControl report = new ReportControl(UserClient.getInstance().getOrdersEntity(), UserClient.getInstance().getComplaintsEntity(), start_store1, end_store1, store_id);
        sum = report.getSum();
//        for(;startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
////            System.out.println(formatter.format(startDate));
//            sum = sum + report.getSum();
//        }
        this.sum_store1_text.setText(Double.toString(sum));
    }

    @FXML
    void store2ApplyClicked(ActionEvent event) {
        sum = 0.0;
        LocalDate start_date_store2 = this.start_date_store2.getValue();
        LocalDate end_date_store2 = this.end_date_store2.getValue();
        LocalDate startDate = start_date_store2;
        LocalDate endDate = end_date_store2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");

        String start_store2 = formatter.format(startDate);
        String end_store2 = formatter.format(endDate);
        String store = this.store2_chooser.getValue();
        int store_id=-2;
        if(store.equals("Haifa")) {store_id=1;}
        if(store.equals("Tel Aviv")) {store_id=2;}
        if(store.equals("Jerusalem")) {store_id=3;}
        if(store.equals("All")) {store_id=-1;}

        ReportControl report = new ReportControl(UserClient.getInstance().getOrdersEntity(), UserClient.getInstance().getComplaintsEntity(), start_store2, end_store2, store_id);
        System.out.println(UserClient.getInstance().getOrdersEntity().get(0).toString());
        sum = report.getSum();
//        for(;startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
////            System.out.println(formatter.format(startDate));
//            sum = report.getSum();
//        }
        this.sum_store2_text.setText(Double.toString(sum));
    }

    public void make_report(){
//        ReportControl report = new ReportControl(UserClient.getInstance().getOrdersEntity(), UserClient.getInstance().getComplaintsEntity(), start_store1, end_store1, store_id);
//        report.getcountDate(); //date order format -> get how many compalint we hav in this day
//        report.getcountProduct(); //name of product --> how many products in this day
//        report.getSum(); // day -> the sum of the day
    }

}