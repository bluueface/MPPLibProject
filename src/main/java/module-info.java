module com.lib {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens com.lib to javafx.fxml;
    exports com.lib;
    exports com.lib.controllers;
    opens com.lib.controllers to javafx.fxml;
    exports com.lib.dataaccess;
    opens com.lib.dataaccess to javafx.fxml;
}