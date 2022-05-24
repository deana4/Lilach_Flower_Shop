module il.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires il.entities;
    requires json;
    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;
    requires eventbus;

    opens il.client to javafx.fxml;
    exports il.client;
}