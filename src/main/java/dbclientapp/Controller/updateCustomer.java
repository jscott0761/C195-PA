package dbclientapp.Controller;

import dbclientapp.DAO.countryQuery;
import dbclientapp.DAO.divisionQuery;
import dbclientapp.Model.Country;
import dbclientapp.Model.Customer;
import dbclientapp.Model.Division;
import javafx.collections.FXCollections;
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
import java.sql.SQLException;
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
    private Division divisionToSet;
    Stage stage;
    Parent scene;

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


    @FXML
    void saveChangesOnClick(ActionEvent event) {

    }

    /**
     * Returns customer to customer table menu upon confirmation
     *
     * @param event Exit button pressed
     * @throws IOException
     */
    @FXML
    void updateCustomerExitOnClick(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Country> countries = countryQuery.getAllCountries();
            updateCustomerCountry.setItems(countries);
            updateCustomerCountry.setPromptText("SELECT COUNTRY");
            customerToUpdate = customerTable.getCustomerToUpdate();
            updateCustomerID.setText(String.valueOf(customerToUpdate.getCustomer_ID()));
            updateCustomerName.setText(customerToUpdate.getCustomer_Name());
            updateCustomerAddress.setText(customerToUpdate.getAddress());
            updateCustomerPostalCode.setText(customerToUpdate.getPostal_Code());
            updateCustomerPhone.setText(customerToUpdate.getPhone());
            for(Division d : divisionQuery.getAllDivisions()){
                if(customerToUpdate.getDivision_ID() == d.getDivision_ID()){
                    this.divisionToSet = d;
                }
            }
                updateCustomerDivision.setValue(divisionToSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
