module dbclientapp.c195_pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens dbclientapp to javafx.fxml;
    exports dbclientapp;
    exports dbclientapp.Controller;
    opens dbclientapp.Controller to javafx.fxml;
    exports dbclientapp.Model;
    opens dbclientapp.Model to javafx.fxml;
    requires javafx.base;


}