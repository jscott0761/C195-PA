package dbclientapp.DAO;

import dbclientapp.Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class appointmentQuery {
    /**
     * Builds an observable list of all Appointments
     * @return all appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String sql = "SELECT * from appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Type = rs.getString("Type");
            LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact_ID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(Appointment_ID, Title, Description, Location, Type, Start, End,Create_Date, Created_By, Last_Update, Last_Updated_By,Customer_ID ,User_ID, Contact_ID );
            appointments.add(appointment);
        }
        return appointments;
    }

}

