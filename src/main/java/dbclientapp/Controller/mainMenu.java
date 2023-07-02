package dbclientapp.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for main menu
 */
public class mainMenu {
    @FXML
    private Button mainAppointmentsBtn;
    @FXML
    private Button mainCustomersBtn;
    @FXML
    private Button mainExit;
    @FXML
    private Button mainReportsBtn;
    Stage stage;
    Parent scene;

    /**
     * Loads appointment table/menu
     * @param event Appointment btn
     * @throws IOException
     */
    @FXML
    void mainAppointmentsOnClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/appointmentTable.fxml"));
        stage.setTitle("Appointment Table");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Loads customer table/menu
     * @param event Customer btn
     * @throws IOException
     */
    @FXML
    void mainCustomersOnClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/customerTable.fxml"));
        stage.setTitle("Customer Table");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Returns user to login screen
     * @param event Exit btn
     * @throws IOException
     */
    @FXML
    void mainExitOnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOGOUT");
        alert.setContentText("ARE YOU SURE YOU WANT TO RETURN TO THE LOGIN SCREEN?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
            stage.setTitle("Login Form");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**
     * Loads Reports
     * @param event Reports btn
     * @throws IOException
     */
    @FXML
    void mainReportsOnClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
        }

    }

