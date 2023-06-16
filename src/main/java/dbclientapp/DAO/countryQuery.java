package dbclientapp.DAO;
import dbclientapp.Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class countryQuery {
    /**
     * Builds an observable list of all countries
     * @return all countries
     * @throws SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String sql = "SELECT * from countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Country_ID = rs.getInt("Country_ID");
            String Country = rs.getString("Country");
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            Country country = new Country(Country_ID, Country, Create_Date, Created_By, Last_Update, Last_Updated_By);
            countries.add(country);
        }
        return countries;
    }

}
