package dbclientapp.Controller;

import dbclientapp.DAO.JDBC;
import dbclientapp.DAO.contactQuery;
import dbclientapp.DAO.customerQuery;
import dbclientapp.DAO.userQuery;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

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
     * Inserts new inputted appointment values into appointments table and returns user to appointment tables
     * Conjugates date and time fields to create start/end
     * @param event Save button pressed
     */
    @FXML
    void addAppointmentSaveOnClick(ActionEvent event) {
        LocalDate startDate = addAppointmentStartDate.getValue();
        String startTimeString = addAppointmentStartTime.getText();
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDateTime appointmentStart = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = addAppointmentEndDate.getValue();
        String endTimeString = addAppointmentEndTime.getText();
        LocalTime endTime = LocalTime.parse(endTimeString);
        LocalDateTime appointmentEnd = LocalDateTime.of(endDate, endTime);

        try{
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
