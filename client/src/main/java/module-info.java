module il.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires il.entities;
    requires json;
    requires MaterialFX;
//    requires MaterialFX;
//    requires VirtualizedFX;

    opens il.client.DiffClasses to javafx.fxml;
    opens il.client to javafx.fxml;
    exports il.client;
    exports il.client.DiffClasses;
}