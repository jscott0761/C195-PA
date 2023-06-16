package dbclientapp.Controller;
import dbclientapp.DAO.*;
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
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/** Controller for form that checks inputted credentials and provides access if they match credentials from database, others provides error message.
 * @author Joseph Scott
 */
public class loginForm implements Initializable{
    @FXML
    private Button loginBtn;
    @FXML
    private Button loginExitBtn;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginPasswordLabel;
    @FXML
    private Label loginRegion;
    @FXML
    private Label loginRegionLabel;
    @FXML
    private Label loginUserIDLabel;
    @FXML
    private TextField loginUserName;
    @FXML
    private Label loginTitle;

    Stage stage;
    Parent scene;

    /**
     * Closes the application upon confirmation.
     * @param event Exit Button
     */
    @FXML
    void loginExitOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");
        alert.setContentText("ARE YOU SURE YOU WANT TO EXIT THE APPLICATION?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**
     * Uses validate method from userQuery in DAO, if method returns a valid ID access is given to the application, if not an error message is displayed
     * @param event login button
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void loginOnClick(ActionEvent event) throws SQLException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("loginForm", Locale.getDefault());
        String username = loginUserName.getText();
        String password = loginPassword.getText();
        int userID = userQuery.validate(username, password);

        if (userID > 0) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
            stage.setTitle("Main Menu");
            stage.setScene(new Scene(scene));
            stage.show();
        } else if (userID < 0) {
            dbclientapp.Helper.helperFunctions.errorAlert(resourceBundle.getString("errorHeader"), resourceBundle.getString("errorMessage"));
        }
    }

    /**
     * Initializes the form, sets language to match default locale of user and displays users ZoneID in a string
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        try {
            resourceBundle = ResourceBundle.getBundle("loginForm", Locale.getDefault());
            loginRegionLabel.setText(resourceBundle.getString("loginForm"));
            loginRegionLabel.setText(resourceBundle.getString("Region"));
            loginUserIDLabel.setText(resourceBundle.getString("Username"));
            loginPasswordLabel.setText(resourceBundle.getString("Password"));
            loginExitBtn.setText(resourceBundle.getString("Exit"));
            loginBtn.setText(resourceBundle.getString("Login"));
            loginTitle.setText(resourceBundle.getString("loginTitle"));
            loginRegion.setText(String.valueOf(zoneId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
