package dbclientapp;

import dbclientapp.DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    /**
     * Loads the login form, therefore launching the application.
     *@param primaryStage
     */
    public void start(Stage primaryStage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("loginForm", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
        primaryStage.setTitle(resourceBundle.getString("windowTitle"));
        primaryStage.setScene(new Scene(root, 393, 303));
        primaryStage.show();
    }
    public static void main(String[] args){
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}