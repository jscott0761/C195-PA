package dbclientapp.DAO;
import dbclientapp.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;


public class userQuery {
    Stage stage;
    Parent scene;

    /**
     * Builds an observable list of all Users
     * @return
     * @throws SQLException
     */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "SELECT * from users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int User_ID = rs.getInt("User_ID");
            String User_Name = rs.getString("User_Name");
            String Password = rs.getString("Password");
            Date Created_Date = rs.getDate("Created_Date");
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            User user = new User(User_ID, User_Name, Password, Created_Date, Created_By, Last_Update, Last_Updated_By);
            users.add(user);
        }
        return users;
    }

    /**
     * Method that checks inputted credentials against the DB
     *
     * @param username
     * @param password
     * @return Users ID if credentials are valid
     * @throws IOException
     */
    public static int validate(String username, String password) throws IOException {
        JDBC jdbc = new JDBC();

        String sql = "SELECT * FROM users WHERE User_Name = '" + username + "' AND Password = '" + password + "'";
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            rs.next();
            if (rs.getString("User_Name").equals(username)) {
                if (rs.getString("Password").equals(password)) {
                    return rs.getInt("User_ID");
                }
            }
        } catch (Exception e) {
        }
        return -1;
    }
}