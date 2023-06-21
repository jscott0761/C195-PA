package dbclientapp.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Division {
    private int Division_ID;
    private String Division;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Country_ID;

    public Division(int Division_ID, String Division, LocalDateTime Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Country_ID) {
        this.Division_ID = Division_ID;
        this.Division = Division;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Country_ID = Country_ID;
    }

    /**
     * Getter for Division ID
     *
     * @return Division ID
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * Getter for Division
     *
     * @return Division
     */
    public String getDivision() {
        return Division;
    }

    /**
     * Getter for Create Date
     *
     * @return Create Date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * Getter for Created By
     *
     * @return Created By
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * Getter for Last Update
     *
     * @return Last Update
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * Getter for Last Updated By
     *
     * @return Last Updated By
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * Getter for Country ID
     *
     * @return Country ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * Setter for Division ID
     *
     * @param Division_ID Division ID to be Set
     */
    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }

    /**
     * Setter for Division
     *
     * @param Division Division to be set
     */
    public void setDivision(String Division) {
        this.Division = Division;
    }

    /**
     * Setter for Create Date
     *
     * @param Create_Date Create Date to be Set
     */
    public void setCreate_Date(LocalDateTime Create_Date) {
        this.Create_Date = Create_Date;
    }

    /**
     * Setter for Created By
     *
     * @param Created_By Value for Created By to be set
     */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /**
     * Setter for Last Update
     *
     * @param Last_Update Value for Last Update to be set
     */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    /**
     * Setter for Last Updated By
     *
     * @param Last_Updated_By Value for Last Updated By to be set
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    /**
     * Setter for Country ID
     *
     * @param Country_ID Country ID to be set
     */
    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
    }
    /**
     * Overrides toString method to make results in combo boxes understandable by the application user
     * @return Division Name
     */

    /**
     * Overrides toString method to make results in combo boxes understandable by the application user
     * @return Division Name
     */
    @Override
    public String toString() {
        return (Division);
    }
}
