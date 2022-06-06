module il.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires il.entities;
    requires json;
    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;
    requires eventbus;
    requires VirtualizedFX;
    requires java.sql;
    requires mysql.connector.java;

    opens il.client to javafx.fxml;
    exports il.client;
    exports il.client.events;
    exports il.client.controls;
    opens il.client.DiffClasses to javafx.fxml;
    exports il.client.DiffClasses;
    opens il.client.controls to javafx.fxml;
}