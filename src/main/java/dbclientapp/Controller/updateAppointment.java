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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class updateAppointment implements Initializable {

    @FXML
    private ComboBox<Contact> updateAppointmentContact;

    @FXML
    private ComboBox<Customer> updateAppointmentCustomerID;

    @FXML
    private TextArea updateAppointmentDesc;

    @FXML
    private DatePicker updateAppointmentEndDate;

    @FXML
    private TextField updateAppointmentEndTime;

    @FXML
    private TextField updateAppointmentID;

    @FXML
    private TextField updateAppointmentLocation;

    @FXML
    private DatePicker updateAppointmentStartDate;

    @FXML
    private TextField updateAppointmentStartTime;

    @FXML
    private TextField updateAppointmentTitle;

    @FXML
    private TextField updateAppointmentType;

    @FXML
    private ComboBox<User> updateAppointmentUserID;
    /**
     * Appointment to be updated
     */
    private Appointment appointmentToUpdate;
    /**
     * Initial value for Contact
     */
    private Contact contactToSet;
    /**
     * Initial value for Customer
     */
    private Customer customerToSet;
    /**
     * Initial value for User
     */
    private User userToSet;
    Stage stage;
    Parent scene;


    /**
     * Returns user to main menu upon confirmation
     * @param event Exit button pressed
     * @throws IOException
     */
    @FXML
    void updateAppointmentExitOnClick(ActionEvent event) throws IOException {
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
     * Inserts updated appointment values into appointments table and returns user to appointment tables after validating inputs
     * Conjugates date and time fields to update start/end
     * Checks overlapping appointments
     * Ensures appointments start before end and end after start
     * @param event Save button pressed
     */
    @FXML
    void updateAppointmentSaveOnClick(ActionEvent event) throws SQLException {
        LocalDate startDate = updateAppointmentStartDate.getValue();
        String startTimeString = updateAppointmentStartTime.getText();
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDateTime appointmentStart = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = updateAppointmentEndDate.getValue();
        String endTimeString = updateAppointmentEndTime.getText();
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
            if (a.getCustomer_ID() == updateAppointmentCustomerID.getValue().getCustomer_ID() && (appointmentToUpdate.getAppointment_ID() != a.getAppointment_ID())){
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
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, updateAppointmentTitle.getText());
            ps.setString(2, updateAppointmentDesc.getText());
            ps.setString(3, updateAppointmentLocation.getText());
            ps.setString(4, updateAppointmentType.getText());
            ps.setTimestamp(5, Timestamp.valueOf(appointmentStart));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
            ps.setTimestamp(7, Timestamp.valueOf(appointmentToUpdate.getCreate_Date()));
            ps.setString(8, appointmentToUpdate.getCreated_By());
            ps.setTimestamp(9,Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(10, "test");
            ps.setInt(11, updateAppointmentCustomerID.getValue().getCustomer_ID());
            ps.setInt(12, updateAppointmentUserID.getValue().getUser_ID());
            ps.setInt(13, updateAppointmentContact.getValue().getContact_ID());
            ps.setString(14, updateAppointmentID.getText());
            ps.execute();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/appointmentTable.fxml"));
            stage.setTitle("Appointment Table");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Initializes form,sets value options for combo box's
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointmentToUpdate = appointmentTable.getAppointmentToUpdate();
            ObservableList<Contact> contacts = contactQuery.getAllContacts();
            updateAppointmentContact.setItems(contacts);

            ObservableList<Customer> customers = customerQuery.getAllCustomers();
            updateAppointmentCustomerID.setItems(customers);

            ObservableList<User> users = userQuery.getAllUsers();
            updateAppointmentUserID.setItems(users);

            updateAppointmentID.setText(String.valueOf(appointmentToUpdate.getAppointment_ID()));
            updateAppointmentTitle.setText(appointmentToUpdate.getTitle());
            updateAppointmentDesc.setText(appointmentToUpdate.getDescription());
            updateAppointmentLocation.setText(appointmentToUpdate.getLocation());
            for (Contact c : contactQuery.getAllContacts()){
                if (appointmentToUpdate.getContact_ID() == c.getContact_ID()) {
                    this.contactToSet = c;
                }
            }
            updateAppointmentContact.setValue(contactToSet);
            updateAppointmentType.setText(appointmentToUpdate.getType());
            updateAppointmentStartDate.setValue(appointmentToUpdate.getStart().toLocalDate());
            updateAppointmentStartTime.setText(String.valueOf(appointmentToUpdate.getStart().toLocalTime()));
            updateAppointmentEndDate.setValue(appointmentToUpdate.getEnd().toLocalDate());
            updateAppointmentEndTime.setText(String.valueOf(appointmentToUpdate.getEnd().toLocalTime()));
            for(Customer cu : customerQuery.getAllCustomers()){
                if(appointmentToUpdate.getCustomer_ID() == cu.getCustomer_ID()){
                    this.customerToSet = cu;
                }
            }
            updateAppointmentCustomerID.setValue(customerToSet);
            for(User u : userQuery.getAllUsers()){
                if(appointmentToUpdate.getUser_ID() == u.getUser_ID()){
                    this.userToSet = u;
                }
            }
            updateAppointmentUserID.setValue(userToSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

