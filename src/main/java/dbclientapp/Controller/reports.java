package dbclientapp.Controller;

import dbclientapp.DAO.appointmentQuery;
import dbclientapp.DAO.contactQuery;
import dbclientapp.DAO.customerQuery;
import dbclientapp.Model.Appointment;
import dbclientapp.Model.Contact;
import dbclientapp.Model.Customer;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class reports implements Initializable {

    @FXML
    private TableView<Appointment> AppointmentsByContactTable;


    @FXML
    private TableColumn<Customer, String> customersByZipCol;


    @FXML
    private TableColumn<Customer, Integer> customersByZipQuantity;


    @FXML
    private TableView<Customer> customersByZipTable;

    @FXML
    private TableColumn<Appointment, String> appointmentsByTMMonth;

    @FXML
    private TableColumn<Appointment, Integer> appointmentsByTMQuantity;

    @FXML
    private TableView<Appointment> appointmentsByTMTable;

    @FXML
    private TableColumn<Appointment, String> appointmentsByTMType;

    @FXML
    private TableColumn<Appointment, Integer> customerReportCustomer;

    @FXML
    private TableColumn<Appointment, String> customerReportDescription;

    @FXML
    private TableColumn<Appointment, LocalDateTime> customerReportEnd;

    @FXML
    private TableColumn<Appointment, Integer> customerReportID;

    @FXML
    private TableColumn<Appointment, LocalDateTime> customerReportStart;

    @FXML
    private TableColumn<Appointment, String> customerReportTitle;

    @FXML
    private TableColumn<Appointment, String> customerReportType;

    @FXML
    private ComboBox<Contact> reportsContactComboBox;
    @FXML
    private ComboBox<Month> appointmentsByTMComboBox;
    @FXML
    private ComboBox<String> customersByZipCombo;
    @FXML
    private Button reportsExitBtn;
    Stage stage;
    Parent scene;

    /**
     * Collects the zip code from the combo box and checks the list of all customer to see if there is a matching zip
     * If there is a matching zip, the customer is added to the customersByZip list and the table is set to the list
     * @param event
     * @throws SQLException
     */

    @FXML
    void customersByZipOnClick(ActionEvent event) throws SQLException {
        String selectedZip = customersByZipCombo.getValue();
        ObservableList<Customer> customers = customerQuery.getAllCustomers();
        ObservableList<Customer> customersByZip = FXCollections.observableArrayList();
        for (Customer c : customers) {
            if (selectedZip.equals(c.getPostal_Code())) {
                customersByZip.add(c);
            }
        }
        customersByZipTable.setItems(customersByZip);
    }


    /**
     * LAMBDA
     * Allows customer to choose month, then creates list of all appointments for that month, and displays it to the tableview as well as a count for each type of appointment for given month
     * Loops through appointment list to see if the start date month is the same as the month selected in the combo box as well as checks if it has already been added to the types list
     * If appointment meets criteria appointment is added to appointmentsByTypeOL and the type is added to the types OL as well as the typeCount increments when the type is found
     * @param event month changed in combo box
     * @throws SQLException
     */
    @FXML
    void appointmentsByTMComboBox(ActionEvent event) throws SQLException {
        Month selectedMonth = appointmentsByTMComboBox.getValue();
        ObservableList<Appointment> appointments = appointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsByType = FXCollections.observableArrayList();
        ObservableList<String> types = FXCollections.observableArrayList();
        for (Appointment a : appointments) {
            int typeCount = 0;
            if (selectedMonth.equals(a.getStart().getMonth()) && !types.contains(a.getType())) {
                appointmentsByType.add(a);
                types.add(a.getType());
                typeCount++;
            }
        }
        appointmentsByTMTable.setItems(appointmentsByType);

    }
    /**
     *::LAMBDA I::
     * Sets appointment by contact tableview to observable list containing all appointments for selected contact
     * Lambda justification: Using a lambda to set this tableview allowed for much simpler code,
     * Rather than having to create observable lists and loop through them as in the cases, I was able to easily filter appointments into a list by those with a matching contact ID to the contact ID selected in the comboBox
     * After the list is compiled it is converted to an observable list which in turn is used to set the values for the table
     * @param event Combo box changed
     * @throws SQLException
     */
    @FXML
    void reportsContactComboBox(ActionEvent event) throws SQLException {
        Contact selectedContact = reportsContactComboBox.getValue();
        List<Appointment> appointmentsByContact = FXCollections.observableArrayList(appointmentQuery.getAllAppointments().stream().filter(appointment -> (appointment.getContact_ID() == (selectedContact.getContact_ID()))).toList());
        ObservableList<Appointment> appointmentsByContactOL = FXCollections.observableArrayList(appointmentsByContact);
        AppointmentsByContactTable.setItems(appointmentsByContactOL);
    }

    /**
     * Returns user to main menu upon confirmation
     * @param event Exit button pressed
     * @throws IOException
     */
    @FXML
    void reportsExitOnClick(ActionEvent event) throws IOException {
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
     * Initialized reports page, sets options for comboBoxes as well as propertyValue for columns.
     * TMQuantity Columns checks list of all appointments and increments a counter for appointment type each time it is found then returns the count as an object
     * This object can then be set to the table view column
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Month> getAllMonths = FXCollections.observableArrayList(Month.values());
        try {
            customerReportID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            customerReportTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            customerReportDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            customerReportType.setCellValueFactory(new PropertyValueFactory<>("Type"));
            customerReportStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
            customerReportEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerReportCustomer.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            appointmentsByTMType.setCellValueFactory(new PropertyValueFactory<>("Type"));
            appointmentsByTMQuantity.setCellValueFactory(data -> {
                int count = 0;
                try {
                    for (Appointment a : appointmentQuery.getAllAppointments()){
                        if (a.getType().equals(data.getValue().getType())){
                            count++;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return new SimpleIntegerProperty(count).asObject();
            });
            ObservableList<String> customerPostalCodes = FXCollections.observableArrayList();
            for (Customer c : customerQuery.getAllCustomers()){
                String customerPostal = c.getPostal_Code();
                customerPostalCodes.add(customerPostal);
            }
            customersByZipCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            customersByZipQuantity.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            customersByZipCombo.setItems(customerPostalCodes);
            reportsContactComboBox.setItems(contactQuery.getAllContacts());
            appointmentsByTMComboBox.setItems(getAllMonths);
            AppointmentsByContactTable.setItems(appointmentQuery.getAllAppointments());

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
