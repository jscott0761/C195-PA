package dbclientapp.DAO;

import dbclientapp.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * Class for database queries relating to customers
 */
public class customerQuery {
    /**
     * Builds an observable list of all Customers
     *
     * @return all Customers
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "SELECT * from customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Customer_ID = rs.getInt("Customer_ID");
            String Customer_Name = rs.getString("Customer_Name");
            String Address = rs.getString("Address");
            String Postal_Code = rs.getString("Postal_Code");
            String Phone = rs.getString("Phone");
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Division_ID = rs.getInt("Division_ID");
            Customer customer = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID);
            customers.add(customer);
        }
        return customers;
    }

    /**
     * Deletes a selected customer and all their appointments from the database
     *
     * @param Customer_ID
     */
    public static void deleteCustomer(int Customer_ID) {
        try {
            String deleteCustomerAppointmentSql = "DELETE from appointments WHERE Customer_ID = ?";
            PreparedStatement ps2 = JDBC.connection.prepareStatement(deleteCustomerAppointmentSql);
            ps2.setInt(1, Customer_ID);
            ps2.executeUpdate();

            String sql = "DELETE from customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, (Customer_ID));
            ps.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

}

