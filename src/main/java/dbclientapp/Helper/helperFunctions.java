package dbclientapp.Helper;

import javafx.scene.control.Alert;

/**
 * class for helper functions to be used throughout application
 */
public class helperFunctions {
    /**
     * A method to quickly make error messages
     *
     * @param Title
     * @param message
     */
    public static void errorAlert(String Title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}