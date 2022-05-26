module il.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires il.entities;
    requires json;
    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;

    opens il.client.DiffClasses to javafx.fxml;
    opens il.client to javafx.fxml;
    exports il.client;
}