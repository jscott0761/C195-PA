package dbclientapp.DAO;

import dbclientapp.Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * Class for database queries relating to divisions
 */
public class divisionQuery {
    /**
     * Creates an Observable list of all divisions
     *
     * @return all divisions
     * @throws SQLException
     */
    public static ObservableList<Division> getAllDivisions() throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        String sql = "SELECT * from first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Division_ID = rs.getInt("Division_ID");
            String Division = rs.getString("Division");
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Country_ID = rs.getInt("Country_ID");
            Division division = new Division(Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID);
            divisions.add(division);
        }
        return divisions;
    }

    /**
     * Creates an Observable list of all divisions within the US
     *
     * @return US Divisions
     * @throws SQLException
     */
    public static ObservableList<Division> getUSDivisions() throws SQLException {
        ObservableList<Division> USDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 1";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Division_ID = rs.getInt("Division_ID");
            String Division = rs.getString("Division");
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Country_ID = rs.getInt("Country_ID");
            Division division = new Division(Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID);
            USDivisions.add(division);
        }
        return USDivisions;
    }

    /**
     * Creates an Observable list of all divisions within the UK
     *
     * @return UK Divisions
     * @throws SQLException
     */
    public static ObservableList<Division> getUKDivisions() throws SQLException {
        ObservableList<Division> UKDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 2";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Division_ID = rs.getInt("Division_ID");
            String Division = rs.getString("Division");
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Country_ID = rs.getInt("Country_ID");
            Division division = new Division(Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID);
            UKDivisions.add(division);
        }
        return UKDivisions;
    }

    /**
     * Creates an Observable list of all divisions within Canada
     *
     * @return Canada Divisions
     * @throws SQLException
     */
    public static ObservableList<Division> getCanadaDivisions() throws SQLException {
        ObservableList<Division> CanadaDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 3";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Division_ID = rs.getInt("Division_ID");
            String Division = rs.getString("Division");
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Country_ID = rs.getInt("Country_ID");
            Division division = new Division(Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID);
            CanadaDivisions.add(division);
        }
        return CanadaDivisions;
    }

    /**
     * Takes the division id and returns the country name associated with that division ID
     * @param Division_ID
     * @return division ID name, otherwise null
     */
    public static String divisionIDtoCountry(int Division_ID) {
        if (Division_ID >= 1 && Division_ID <= 54) {
            return "U.S";
        }
        if (Division_ID >= 60 && Division_ID <= 72) {
            return "Canada";
        }
        if (Division_ID >= 101 && Division_ID <= 104) {
            return "U.K.";
        }
        else return null;
        }

}