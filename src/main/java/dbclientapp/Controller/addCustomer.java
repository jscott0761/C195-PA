package dbclientapp.Controller;

import dbclientapp.DAO.JDBC;
import dbclientapp.DAO.countryQuery;
import dbclientapp.DAO.divisionQuery;
import dbclientapp.Model.Country;
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
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * Controller class for add customer menu
 */
public class addCustomer implements Initializable {

    @FXML
    private TextField addCustomerAddress;

    @FXML
    private ComboBox<Country> addCustomerCountry;

    @FXML
    private ComboBox<Division> addCustomerDivision;

    @FXML
    private Button addCustomerExit;

    @FXML
    private TextField addCustomerID;

    @FXML
    private TextField addCustomerName;

    @FXML
    private TextField addCustomerPhone;

    @FXML
    private TextField addCustomerPostalCode;

    @FXML
    private Button saveCustomer;
    Stage stage;
    Parent scene;

    /**
     * Returns user to customer table
     * @param event Exit button pressed
     * @throws IOException
     */
    @FXML
    void addCustomerExitOnClick(ActionEvent event) throws IOException {
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
     * Collects values inputted by customer and adds customer to database, automatically increments user ID
     * @param event Save button clicked
     */
    @FXML
    void saveCustomerOnClick(ActionEvent event) {
        if (addCustomerName.getText().isEmpty()) {
            dbclientapp.Helper.helperFunctions.errorAlert("NAME EMPTY", "NAME CAN NOT BE LEFT EMPTY");
        }
        if (addCustomerAddress.getText().isEmpty()) {
            dbclientapp.Helper.helperFunctions.errorAlert("ADDRESS EMPTY", "ADDRESS CAN NOT BE LEFT EMPTY");
        }
        if (addCustomerPostalCode.getText().isEmpty()) {
            dbclientapp.Helper.helperFunctions.errorAlert("POSTAL CODE EMPTY", "POSTAL CODE CAN NOT BE LEFT EMPTY");
        }
        if (addCustomerPhone.getText().isEmpty()) {
            dbclientapp.Helper.helperFunctions.errorAlert("PHONE NUMBER EMPTY", "PHONE NUMBER CAN NOT BE LEFT EMPTY");
        }
        else if (!addCustomerName.getText().isBlank() && !addCustomerAddress.getText().isBlank() && !addCustomerPostalCode.getText().isBlank() && !addCustomerPhone.getText().isBlank()) {
            try {
                String sql = "INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = JDBC.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, null);
                ps.setString(2, addCustomerName.getText());
                ps.setString(3, addCustomerAddress.getText());
                ps.setString(4, addCustomerPostalCode.getText());
                ps.setString(5, addCustomerPhone.getText());
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(7, "test");
                ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(9, "test");
                ps.setInt(10, addCustomerDivision.getValue().getDivision_ID());
                ps.execute();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/customerTable.fxml"));
                stage.setTitle("Customer Table");
                stage.setScene(new Scene(scene));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * Gets selected value from country combo box and sets division combo box to appropriate options based on selection
     * @param event Country combo box option is selected
     * @throws SQLException
     */
    @FXML
    void onActionAddCustomerDivision(MouseEvent event) throws SQLException {
        Country selectedCountry = addCustomerCountry.getValue();
        if(selectedCountry == null){
            dbclientapp.Helper.helperFunctions.errorAlert("NO COUNTRY SELECTED", "YOU MUST FIRST SELECT A COUNTRY BEFORE SELECTING A DIVISION");
        }
        if (selectedCountry.toString().equals("U.S")) {
            ObservableList<Division> USDivisions = divisionQuery.getUSDivisions();
            addCustomerDivision.setItems(USDivisions);
        }
        if (selectedCountry.toString().equals("UK")) {
            ObservableList<Division> UKDivisions = divisionQuery.getUKDivisions();
            addCustomerDivision.setItems(UKDivisions);
        }
        if (selectedCountry.toString().equals("Canada")) {
            ObservableList<Division> CanadaDivisions = divisionQuery.getCanadaDivisions();
            addCustomerDivision.setItems(CanadaDivisions);
        }
    }

    /**
     * Initialized form, loads values into country combo box
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Country> countries = countryQuery.getAllCountries();
            addCustomerCountry.setItems(countries);
            addCustomerCountry.setPromptText("SELECT COUNTRY");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
