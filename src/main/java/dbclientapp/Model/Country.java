package dbclientapp.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * Creates Country class and sets constructor
 */
public class Country {
    private int Country_ID;
    private String Country;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    /**
     * Constructor for Country Class
     * @param Country_ID
     * @param Country
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     */
    public Country(int Country_ID, String Country, LocalDateTime Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By){
        this.Country_ID = Country_ID;
        this.Country = Country;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }

    /**
     * Getter for Country ID
     * @return Country ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * Getter for Country
     * @return Country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Getter for Create Date
     * @return Create Date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * Getter for Created By
     * @return Created By
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * Getter for Last Update
     * @return Last Update
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * Getter for Last Upodated By
     * @return Last Updated By
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * Setter for Country ID
     * @param Country_ID Country ID to be Set
     */
    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
    }

    /**
     * Setter for Country
     * @param Country Country to be set
     */
    public void setCountry(String Country) {
        this.Country = Country;
    }

    /**
     * Setter for Create Date
     * @param Create_Date Create Date to be set
     */
    public void setCreate_Date(LocalDateTime Create_Date) {
        this.Create_Date = Create_Date;
    }

    /**
     * Setter for Created By
     * @param Created_By Value for Created by to be set
     */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /**
     * Setter for Last Update
     * @param Last_Update value for last update to be set
     */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    /**
     * Setter for last updated by
     * @param Last_Updated_By Value to last updated by to be set
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    /**
     * Overrides toString method to make results in combo boxes understandable by the application user
     * @return Country Name
     */
    @Override
    public String toString(){
        return (Country);
    }
}
