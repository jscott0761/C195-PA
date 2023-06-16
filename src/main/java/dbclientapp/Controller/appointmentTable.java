package dbclientapp.Controller;
import dbclientapp.DAO.appointmentQuery;
import dbclientapp.Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class appointmentTable implements Initializable {
    @FXML
    private Button addAppointmentBtn;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentContact;
    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerID;
    @FXML
    private TableColumn<Appointment, String> appointmentDescription;
    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentEnd;
    @FXML
    private Button appointmentExitBtn;
    @FXML
    private TableColumn<Appointment, Integer> appointmentID;
    @FXML
    private TableColumn<Appointment, String> appointmentLocation;
    @FXML
    private TableColumn<Appointment, LocalDateTime> appointmentStart;
    @FXML
    private TableColumn<Appointment, String> appointmentTitle;
    @FXML
    private TableColumn<Appointment, String> appointmentType;
    @FXML
    private TableColumn<Appointment, Integer> appointmentUserID;
    @FXML
    private ToggleGroup appointmentViewBy;
    @FXML
    private Button deleteAppointmentBtn;
    @FXML
    private Label timeZoneLabel;
    @FXML
    private Button updateAppointmentBtn;
    @FXML
    private Text userTimeZone;
    @FXML
    private RadioButton viewAllBtn;
    @FXML
    private RadioButton viewMonthBtn;
    @FXML
    private RadioButton viewWeekBtn;
    Stage stage;
    Parent scene;

    @FXML
    void addAppointmentOnClick(ActionEvent event) {

    }

    @FXML
    void appointmentExitOnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RETURN TO MAIN MENU");
        alert.setContentText("ARE YOU SURE YOU WANT TO RETURN TO THE MAIN MENU?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
            stage.setTitle("Main Menu");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void deleteAppointmentOnClick(ActionEvent event) {

    }

    @FXML
    void updateAppointmentOnClick(ActionEvent event) {

    }

    @FXML
    void viewAllOnClick(ActionEvent event) {

    }

    @FXML
    void viewMonthOnClick(ActionEvent event) {
    ObservableList<Appointment> appointmentsByMonth = FXCollections.observableArrayList();
    }

    @FXML
    void viewWeekOnClick(ActionEvent event) {

    }


    /**
     * Initializes the appointment menu, populates tableview
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointmentID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
            appointmentContact.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
            appointmentType.setCellValueFactory(new PropertyValueFactory<>("Type"));
            appointmentStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
            appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
            appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
            appointmentTable.setItems(appointmentQuery.getAllAppointments());
            viewAllBtn.setSelected(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

