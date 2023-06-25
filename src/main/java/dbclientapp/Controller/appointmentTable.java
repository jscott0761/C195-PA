package dbclientapp.Controller;

import dbclientapp.DAO.appointmentQuery;
import dbclientapp.Model.Appointment;
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
import java.time.ZoneId;
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
    /**
     * Appointment to update
     */
    public static Appointment appointmentToUpdate;

    /**
     * Gets appointment to update
     * @return appointment to update
     */
    public static Appointment getAppointmentToUpdate(){return appointmentToUpdate;}
    Stage stage;
    Parent scene;

    /**
     * Loads form to add a new appointment
     * @param event Add Appointment Button Clicked
     * @throws IOException
     */
    @FXML
    void addAppointmentOnClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addAppointment.fxml"));
        stage.setTitle("Add Appointment");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Returns user to main menu upon confirmation
     * @param event Exit button clicked
     * @throws IOException
     */
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

    /**
     * Deletes selected appointment from database upon confirmation
     * @param event Delete button pressed
     * @throws SQLException
     */
    @FXML
    void deleteAppointmentOnClick(ActionEvent event) throws SQLException {
        Appointment appointmentToDelete = appointmentTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DELETE APPOINTMENT");
        alert.setContentText("ARE YOU SURE YOU DELETE THIS APPOINTMENT?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            dbclientapp.DAO.appointmentQuery.deleteAppointment(appointmentTable.getSelectionModel().getSelectedItem().getAppointment_ID());
            Alert deletedAlert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setContentText("APPOINTMENT ID:" + " " + appointmentToDelete.getAppointment_ID() + "   " + "TYPE: " + appointmentToDelete.getType() + " " + "HAS BEEN DELETED");
            alert.showAndWait();
            appointmentTable.refresh();
        }
        ObservableList<Appointment> appointments = appointmentQuery.getAllAppointments();
        appointmentTable.setItems(appointments);
    }
    /**
     * Loads the update appointment form when user has selected a customer to update
     * @param event Update appointment Button Clicked
     * @throws IOException
     */
    @FXML
    void updateAppointmentOnClick(ActionEvent event) throws IOException {
        appointmentToUpdate = appointmentTable.getSelectionModel().getSelectedItem();
        if (appointmentToUpdate == null) {
            dbclientapp.Helper.helperFunctions.errorAlert("SELECT AN APPOINTMENT", "YOU MUST SELECT AN APPOINTMENT TO UPDATE");
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/updateAppointment.fxml"));
            stage.setTitle("Update Appointment");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void viewAllOnClick(ActionEvent event) throws SQLException {
        appointmentTable.setItems(appointmentQuery.getAllAppointments());
    }

    @FXML
    void viewMonthOnClick(ActionEvent event) {
        try {
            if (appointmentTable.getItems().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NO APPOINTMENTS FOUND");
                alert.setContentText("NO APPOINTMENTS HAVE BEEN FOUND FOR THE CURRENT MONTH");
                alert.showAndWait();
            }
            else {
                appointmentQuery.viewByWeek();
                appointmentTable.setItems(appointmentQuery.viewByWeek());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void viewWeekOnClick(ActionEvent event) throws SQLException {
        try {
            if (appointmentTable.getItems().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NO APPOINTMENTS FOUND");
                alert.setContentText("NO APPOINTMENTS HAVE BEEN FOUND FOR THE CURRENT WEEK");
                alert.showAndWait();
            }
            else {
                appointmentQuery.viewByWeek();
                appointmentTable.setItems(appointmentQuery.viewByWeek());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            ZoneId zoneId = ZoneId.systemDefault();
            userTimeZone.setText(String.valueOf(zoneId));
            viewAllBtn.setSelected(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

