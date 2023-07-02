package dbclientapp.DAO;

import dbclientapp.Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class for database queries relating to contacts
 */
public class contactQuery {
    /**
     * Creates an observable list of all contacts in the sql database
     * @return all contacts
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String sql = "SELECT * from contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Contact_ID = rs.getInt("Contact_ID");
            String Contact_Name = rs.getString("Contact_Name");
            String Email = rs.getString("Email");
            Contact contact = new Contact(Contact_ID, Contact_Name, Email);
            contacts.add(contact);
        }
        return contacts;
    }
}
