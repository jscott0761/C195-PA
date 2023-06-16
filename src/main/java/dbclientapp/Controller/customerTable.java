package dbclientapp.Controller;

import dbclientapp.DAO.JDBC;
import dbclientapp.DAO.appointmentQuery;
import dbclientapp.DAO.customerQuery;
import dbclientapp.Model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerTable implements Initializable {

    @FXML
    private Button addCustomerBtn;

    @FXML
    private TableColumn<Customer, String> customerAddress;
    @FXML
    private TableColumn<Customer, LocalDateTime> customerCreateDate;

    @FXML
    private TableColumn<Customer, String> customerCreatedBy;

    @FXML
    private TableColumn<Customer, Integer> customerDivisionID;

    @FXML
    private Button customerExitBtn;

    @FXML
    private TableColumn<Customer, Integer> customerID;

    @FXML
    private TableColumn<Customer, Timestamp> customerLastUpdate;

    @FXML
    private TableColumn<Customer, String> customerLastUpdatedBy;

    @FXML
    private TableColumn<Customer, String> customerName;

    @FXML
    private TableColumn<Customer, String> customerPhoneNumber;

    @FXML
    private TableColumn<Customer, String> customerPostalCode;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private Button deleteCustomerBtn;

    @FXML
    private Button updateCustomerBtn;
    /**
     * Customer to update
     */
    public static Customer customerToUpdate;

    /**
     * Gets customer to update
     * @return customer to update
     */
    public static Customer getCustomerToUpdate(){return customerToUpdate;}

    Stage stage;
    Parent scene;

    /**
     * Loads the add customer form
     * @param event Add customer button pressed
     * @throws IOException
     */
    @FXML
    void addCustomerOnClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addCustomer.fxml"));
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Returns user to main menu upon confirmation
     * @param event Exit button pressed
     * @throws IOException
     */
    @FXML
    void customerExitOnClick(ActionEvent event) throws IOException {
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
     * Deletes customer and all associated appointments from database upon user confirmation
     * @param event Delete button pressed
     * @throws SQLException
     */
    @FXML
    void deleteCustomerOnClick(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("DELETE CUSTOMER");
        alert.setContentText("ARE YOU SURE YOU DELETE THIS CUSTOMER? DOING SO WILL ALSO DELETE ALL CUSTOMER APPOINTMENTS");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            dbclientapp.DAO.customerQuery.deleteCustomer(customerTable.getSelectionModel().getSelectedItem().getCustomer_ID());
            customerTable.refresh();
        }
        ObservableList<Customer> customers = customerQuery.getAllCustomers();
        customerTable.setItems(customers);
    }
    @FXML
    void updateCustomerOnClick(ActionEvent event) throws IOException {
        customerToUpdate = customerTable.getSelectionModel().getSelectedItem();
        if (customerToUpdate == null) {
            dbclientapp.Helper.helperFunctions.errorAlert("SELECT A CUSTOMER", "YOU MUST SELECT A CUSTOMER TO UPDATE");
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/updateCustomer.fxml"));
            stage.setTitle("Update Customer");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**
     * Initializes the Customer menu, populates tableview
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            customerName.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            customerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
            customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            customerCreateDate.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
            customerCreatedBy.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
            customerLastUpdate.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));
            customerLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("Last_Updated_By"));
            customerDivisionID.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
            customerTable.setItems(customerQuery.getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
