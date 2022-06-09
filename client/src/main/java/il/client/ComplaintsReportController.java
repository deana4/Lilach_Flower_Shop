package il.client;

import il.client.controls.ReportControl;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class ComplaintsReportController {

    @FXML
    private NumberAxis xAxis;
    @FXML
    private CategoryAxis yAxis;

    @FXML
    private NumberAxis xAxis2;

    @FXML
    private CategoryAxis yAxis2;

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
    private BarChart<String, Number> store1_diagram;

    @FXML
    private MFXComboBox<String> store2_chooser;

    @FXML
    private BarChart<String, Number> store2_diagram;

    @FXML
    private MFXButton store2;

    @FXML
    void initialize(){
        if(UserClient.getInstance().getPriority() == 5){
            this.store2_chooser.setVisible(false);
            this.start_date_store2.setVisible(false);
            this.end_date_store2.setVisible(false);
            this.store2_diagram.setVisible(false);
            this.store2.setVisible(false);
            this.store1_chooser.setVisible(false);
            int store_id = UserClient.getInstance().getStoreId();
            String store="";
            if(store_id == 1) {store="Haifa";}
            if(store_id == 2) {store="Tel Aviv";}
            if(store_id == 3) {store="Jerusalem";}
            System.out.println(store);
            this.store1_chooser.setValue(store);
            this.store1_chooser.setDisable(true);
        }

        this.store1_chooser.getItems().add("All");
        this.store1_chooser.getItems().add("Haifa");
        this.store1_chooser.getItems().add("Tel Aviv");
        this.store1_chooser.getItems().add("Jerusalem");
        this.store2_chooser.getItems().add("All");
        this.store2_chooser.getItems().add("Haifa");
        this.store2_chooser.getItems().add("Tel Aviv");
        this.store2_chooser.getItems().add("Jerusalem");
    }


    @FXML
    void Store1Chooser(ActionEvent event) {

    }

    @FXML
    void Store2Chooser(ActionEvent event) {

    }

    @FXML
    void store1ApplyClicked(ActionEvent event) {
        LocalDate start_date_store1 = this.start_date_store1.getValue();
        LocalDate end_date_store1 = this.end_date_store1.getValue();
        LocalDate startDate = start_date_store1;
        LocalDate endDate = end_date_store1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");

        String start_store1 = formatter.format(startDate);
        String end_store1 = formatter.format(endDate);
        String store = this.store1_chooser.getValue();
        int store_id=-2;
        if(store.equals("Haifa")) {store_id=1;}
        if(store.equals("Tel Aviv")) {store_id=2;}
        if(store.equals("Jerusalem")) {store_id=3;}
        if(store.equals("All")) {store_id=-1;}
        System.out.println(store_id);
        ReportControl report = new ReportControl(UserClient.getInstance().getOrdersEntity(), UserClient.getInstance().getComplaintsEntity(), start_store1, end_store1, store_id);
//        for(;startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
////            System.out.println(formatter.format(startDate));
//            sum = sum + report.getSum();
//        }

        store1_diagram.setBarGap(0);
        //gap between categories; you can pick another value
        store1_diagram.setCategoryGap(5);
        //there're some bugs with the chart layout not updating when this is enabled, so disable it
        store1_diagram.setAnimated(false);

        xAxis.setLabel("Number");
        xAxis.setTickLabelRotation(1);

        yAxis.setLabel("Complaint Date");

        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Complaint");
      //  ObservableList<XYChart.Data> data  = FXCollections.observableArrayList();

            for(;startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
                series1.getData().addAll(
                        new XYChart.Data<>(formatter.format(startDate), report.getcountDate(formatter.format(startDate)))
                );
            }

        store1_diagram.getData().addAll(series1);

        //        report.getcountDate(); //date order format -> get how many compalint we have in this day
    }

    @FXML
    void store2ApplyClicked(ActionEvent event) {

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
        System.out.println(store_id);
        ReportControl report = new ReportControl(UserClient.getInstance().getOrdersEntity(), UserClient.getInstance().getComplaintsEntity(), start_store2, end_store2, store_id);
//        for(;startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
////            System.out.println(formatter.format(startDate));
//            sum = sum + report.getSum();
//        }

        store2_diagram.setBarGap(0);
        //gap between categories; you can pick another value
        store2_diagram.setCategoryGap(5);
        //there're some bugs with the chart layout not updating when this is enabled, so disable it
        store2_diagram.setAnimated(false);

        xAxis.setLabel("Number");
        xAxis.setTickLabelRotation(1);

        yAxis.setLabel("Complaint Date");

        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Complaint");
        //  ObservableList<XYChart.Data> data  = FXCollections.observableArrayList();

        for(;startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
            series1.getData().addAll(
                    new XYChart.Data<>(formatter.format(startDate), report.getcountDate(formatter.format(startDate)))
            );
        }

        store2_diagram.getData().addAll(series1);
    }

}
