package dbclientapp.Controller;

import dbclientapp.DAO.JDBC;
import dbclientapp.DAO.countryQuery;
import dbclientapp.DAO.divisionQuery;
import dbclientapp.Model.Country;
import dbclientapp.Model.Customer;
import dbclientapp.Model.Division;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class updateCustomer implements Initializable {

    @FXML
    private Button saveChanges;

    @FXML
    private TextField updateCustomerAddress;

    @FXML
    private ComboBox<Country> updateCustomerCountry;

    @FXML
    private ComboBox<Division> updateCustomerDivision;

    @FXML
    private Button updateCustomerExit;

    @FXML
    private TextField updateCustomerID;

    @FXML
    private TextField updateCustomerName;

    @FXML
    private TextField updateCustomerPhone;

    @FXML
    private TextField updateCustomerPostalCode;
    /**
     * Selected customer to update
     */
    private Customer customerToUpdate;
    /**
     * Initial value for division
     */
    private Division divisionToSet;
    /**
     * Initial value for country
     */
    private Country countryToSet;
    Stage stage;
    Parent scene;

    /**
     * Sets division list to appropriate values for selected country
     *
     * @param event Country is updated
     * @throws SQLException
     */
    @FXML
    void onActionUpdateCustomerDivision(MouseEvent event) throws SQLException {
        Country selectedCountry = updateCustomerCountry.getValue();
        if (selectedCountry == null) {
            dbclientapp.Helper.helperFunctions.errorAlert("NO COUNTRY SELECTED", "YOU MUST FIRST SELECT A COUNTRY BEFORE SELECTING A DIVISION");
        }
        if (selectedCountry.toString().equals("U.S")) {
            ObservableList<Division> USDivisions = divisionQuery.getUSDivisions();
            updateCustomerDivision.setItems(USDivisions);
        }
        if (selectedCountry.toString().equals("UK")) {
            ObservableList<Division> UKDivisions = divisionQuery.getUKDivisions();
            updateCustomerDivision.setItems(UKDivisions);
        }
        if (selectedCountry.toString().equals("Canada")) {
            ObservableList<Division> CanadaDivisions = divisionQuery.getCanadaDivisions();
            updateCustomerDivision.setItems(CanadaDivisions);
        }
    }

    /**
     * Updates the database with the new information inputted by the user, then returns user back to customer table
     * @param event
     */
    @FXML
    void saveChangesOnClick(ActionEvent event) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, updateCustomerName.getText());
            ps.setString(2, updateCustomerAddress.getText());
            ps.setString(3, updateCustomerPostalCode.getText());
            ps.setString(4, updateCustomerPhone.getText());
            ps.setTimestamp(5, Timestamp.valueOf(customerToUpdate.getCreate_Date()));
            ps.setString(6, customerToUpdate.getCreated_By());
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "test");
            ps.setInt(9, updateCustomerDivision.getValue().getDivision_ID());
            ps.setString(10, updateCustomerID.getText());
            ps.execute();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/customerTable.fxml"));
            stage.setTitle("Customer Table");
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        }
        /**
         * Returns customer to customer table menu upon confirmation
         * @param event Exit button pressed
         * @throws IOException
         */
        @FXML
        void updateCustomerExitOnClick (ActionEvent event) throws IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("RETURN TO CUSTOMER TABLE");
            alert.setContentText("ARE YOU SURE YOU WANT TO RETURN TO THE CUSTOMER TABLE?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/customerTable.fxml"));
                stage.setTitle("Customer Table");
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }

        /**
         * Sets values to those saved for the selected customer, division function checks if the division is a part of all divisions then sets the division equal to appropriate value
         * Country function checks customers current division and sets country based off of that
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            try {
                ObservableList<Country> countries = countryQuery.getAllCountries();
                updateCustomerCountry.setPromptText("SELECT COUNTRY");
                customerToUpdate = customerTable.getCustomerToUpdate();
                updateCustomerID.setText(String.valueOf(customerToUpdate.getCustomer_ID()));
                updateCustomerName.setText(customerToUpdate.getCustomer_Name());
                updateCustomerAddress.setText(customerToUpdate.getAddress());
                updateCustomerPostalCode.setText(customerToUpdate.getPostal_Code());
                updateCustomerPhone.setText(customerToUpdate.getPhone());
                for (Division d : divisionQuery.getAllDivisions()) {
                    if (customerToUpdate.getDivision_ID() == d.getDivision_ID()) {
                        this.divisionToSet = d;
                    }
                }
                updateCustomerDivision.setValue(divisionToSet);

                int divisionID = updateCustomerDivision.getValue().getDivision_ID();
                updateCustomerCountry.setItems(countries);
                if (divisionID <= 54) {
                    countryToSet = countryQuery.getAllCountries().get(0);
                    updateCustomerCountry.setValue(countryToSet);
                }
                if (divisionID >= 60 && divisionID <= 72) {
                    countryToSet = countryQuery.getAllCountries().get(2);
                    updateCustomerCountry.setValue(countryToSet);
                } else if (divisionID >= 101 && divisionID <= 104) {
                    countryToSet = countryQuery.getAllCountries().get(1);
                    updateCustomerCountry.setValue(countryToSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

