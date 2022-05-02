module il.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires il.entities;


    opens il.client to javafx.fxml;
    exports il.client;
}