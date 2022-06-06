module il.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires il.entities;
    requires json;
    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;
    requires eventbus;
    requires VirtualizedFX;

    opens il.client to javafx.fxml;
    exports il.client;
    exports il.client.events;
    opens il.client.DiffClasses to javafx.fxml;
    exports il.client.DiffClasses;
    exports il.client.control;
    opens il.client.control to javafx.fxml;
}