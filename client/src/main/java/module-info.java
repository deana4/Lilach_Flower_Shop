module il.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens il.client to javafx.fxml;
    exports il.client;
}