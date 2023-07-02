package dbclientapp.Controller;

import dbclientapp.DAO.*;
import dbclientapp.Model.Appointment;
import dbclientapp.Model.Contact;
import dbclientapp.Model.Customer;
import dbclientapp.Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class for add appointment menu
 */
public class addAppointment implements Initializable {

    @FXML
    private ComboBox<Contact> addAppointmentContact;

    @FXML
    private ComboBox<Customer> addAppointmentCustomerID;

    @FXML
    private TextArea addAppointmentDesc;

    @FXML
    private DatePicker addAppointmentEndDate;

    @FXML
    private TextField addAppointmentEndTime;

    @FXML
    private Button addAppointmentExitBtn;

    @FXML
    private TextField addAppointmentID;

    @FXML
    private TextField addAppointmentLocation;

    @FXML
    private Button addAppointmentSaveBtn;

    @FXML
    private DatePicker addAppointmentStartDate;

    @FXML
    private TextField addAppointmentStartTime;

    @FXML
    private TextField addAppointmentTitle;

    @FXML
    private TextField addAppointmentType;

    @FXML
    private ComboBox<User> addAppointmentUserID;
    Stage stage;
    Parent scene;

    /**
     * Returns customer to appointment table upon confirmation
     * @param event Exit button is pressed
     * @throws IOException
     */
    @FXML
    void addAppointmentExitOnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RETURN TO APPOINTMENT TABLE");
        alert.setContentText("ARE YOU SURE YOU WANT TO RETURN TO THE APPOINTMENT TABLE?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/appointmentTable.fxml"));
            stage.setTitle("Appointment Table");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Inserts new inputted appointment values into appointments table and returns user to appointment tables after validating inputs
     * Conjugates date and time fields to create start/end
     * Checks overlapping appointments
     * Ensures appointments start before end and end after start
     * @param event Save button pressed
     */
    @FXML
    void addAppointmentSaveOnClick(ActionEvent event) throws SQLException {
        LocalDate startDate = addAppointmentStartDate.getValue();
        String startTimeString = addAppointmentStartTime.getText();
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDateTime appointmentStart = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = addAppointmentEndDate.getValue();
        String endTimeString = addAppointmentEndTime.getText();
        LocalTime endTime = LocalTime.parse(endTimeString);
        LocalDateTime appointmentEnd = LocalDateTime.of(endDate, endTime);

        ZoneId est = ZoneId.of("America/New_York");
        LocalTime openEST = LocalTime.of(8, 0);
        LocalTime closeEST = LocalTime.of(22, 0);
        ZonedDateTime appointmentStartZDT = ZonedDateTime.of(appointmentStart, ZoneId.systemDefault());
        ZonedDateTime appointmentsEndZDT = ZonedDateTime.of(appointmentEnd, ZoneId.systemDefault());
        ZonedDateTime startEST = appointmentStartZDT.withZoneSameInstant(est);
        ZonedDateTime endEST = appointmentsEndZDT.withZoneSameInstant(est);
        LocalTime startTimeEST = startEST.toLocalTime();
        LocalTime endTimeEST = endEST.toLocalTime();

        Calendar appointmentDateCalender = Calendar.getInstance();
        ZonedDateTime appointmentDateZDT = appointmentStart.atZone(ZoneId.systemDefault());
        Date appointmentStartDate = Date.from(Instant.from(appointmentDateZDT));
        appointmentDateCalender.setTime(appointmentStartDate);
        int day = appointmentDateCalender.get(Calendar.DAY_OF_WEEK);

        if (startTimeEST.isBefore(openEST) || startTimeEST.isAfter(closeEST) || endTimeEST.isBefore(openEST) || endTimeEST.isAfter(closeEST)) {
            dbclientapp.Helper.helperFunctions.errorAlert("INVALID TIME", "APPOINTMENT IS OUTSIDE OF BUSINESS HOURS");
            return;
        }
        if (!(day >= Calendar.MONDAY && day <= Calendar.FRIDAY)) {
            dbclientapp.Helper.helperFunctions.errorAlert("INVALID DATE", "APPOINTMENT IS OUTSIDE BUSINESS WEEK");
            return;
        }
        for (Appointment a : appointmentQuery.getAllAppointments()) {
            LocalDateTime appointmentStartTime = a.getStart();
            LocalDateTime appointmentEndTime = a.getEnd();
            if (a.getCustomer_ID() == addAppointmentCustomerID.getValue().getCustomer_ID()) {
                if (appointmentStart.isBefore(appointmentStartTime) && appointmentEnd.isAfter(appointmentEndTime)){
                    dbclientapp.Helper.helperFunctions.errorAlert("OVERLAPPING APPOINTMENT", "THIS APPOINTMENT OVERLAPS A PREEXISTING APPOINTMENT");
                    return;
                }
                 if ((appointmentStart.isAfter(appointmentStartTime) || appointmentStart.isEqual(appointmentStartTime)) && appointmentStart.isBefore(appointmentEndTime)) {
                     dbclientapp.Helper.helperFunctions.errorAlert("OVERLAPPING START TIME", "THIS APPOINTMENT'S START TIME OVERLAPS A PREEXISTING APPOINTMENT");
                     return;
                 }
                if ((appointmentEnd.isBefore(appointmentEndTime) || appointmentEnd.isEqual(appointmentEndTime)) && appointmentEnd.isAfter(appointmentStartTime)){
                    dbclientapp.Helper.helperFunctions.errorAlert("OVERLAPPING END TIME", "THIS APPOINTMENT'S END TIME OVERLAPS A PREEXISTING APPOINTMENT");
                    return;
                }
            }
        }
        if(appointmentEnd.isBefore(appointmentStart) || appointmentStart.isAfter(appointmentEnd)){
            dbclientapp.Helper.helperFunctions.errorAlert("INVALID TIME", "APPOINTMENT CAN NOT END BEFORE START TIME OR START AFTER END TIME");
        }
        else {
            try {
                String sql = "INSERT INTO appointments VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = JDBC.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, null);
                ps.setString(2, addAppointmentTitle.getText());
                ps.setString(3, addAppointmentDesc.getText());
                ps.setString(4, addAppointmentLocation.getText());
                ps.setInt(14, addAppointmentContact.getValue().getContact_ID());
                ps.setString(5, addAppointmentType.getText());
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(9, "test");
                ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(11, "test");
                ps.setTimestamp(6, Timestamp.valueOf(appointmentStart));
                ps.setTimestamp(7, Timestamp.valueOf(appointmentEnd));
                ps.setInt(12, addAppointmentCustomerID.getValue().getCustomer_ID());
                ps.setInt(13, addAppointmentUserID.getValue().getUser_ID());
                ps.execute();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/appointmentTable.fxml"));
                stage.setTitle("Appointment Table");
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                dbclientapp.Helper.helperFunctions.errorAlert("INPUT ERROR", "PLEASE ENSURE ALL VALUES ARE FILLED IN FOLLOWING CORRECT FORMAT");
            }
        }
    }

    /**
     * Initializes form, sets values for combo box's
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Contact> contacts = contactQuery.getAllContacts();
            addAppointmentContact.setItems(contacts);

            ObservableList<Customer> customers = customerQuery.getAllCustomers();
            addAppointmentCustomerID.setItems(customers);

            ObservableList<User> users = userQuery.getAllUsers();
            addAppointmentUserID.setItems(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
