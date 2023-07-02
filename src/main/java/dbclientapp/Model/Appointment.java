package dbclientapp.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Creates Appointment class and sets constructor
 */
public class Appointment {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;

    /**
     * Constructor for Appointment class
     * @param Appointment_ID
     * @param Title
     * @param Description
     * @param Location
     * @param Type
     * @param Start
     * @param End
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Customer_ID
     * @param User_ID
     * @param Contact_ID
     */
    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End,LocalDateTime Create_Date, String Created_By, Timestamp Last_Update,String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID){
    this.Appointment_ID = Appointment_ID;
    this.Title = Title;
    this. Description = Description;
    this.Location = Location;
    this.Type = Type;
    this.Start = Start;
    this.End = End;
    this.Create_Date = Create_Date;
    this.Created_By = Created_By;
    this.Last_Update = Last_Update;
    this.Last_Updated_By = Last_Updated_By;
    this.Customer_ID = Customer_ID;
    this.User_ID = User_ID;
    this.Contact_ID = Contact_ID;
}

    /**
     * Getter for the Appointment ID
     * @return the appointment ID
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * Getter for the title
     * @return Title
     */
    public String getTitle() {
        return Title;
    }
    /**
     * Getter for the Description
     * @return Description
     */
    public String getDescription() {
        return Description;
    }
    /**
     * Getter for the Location
     * @return Location
     */
    public String getLocation() {
        return Location;
    }
    /**
     * Getter for the Appointment Type
     * @return Type
     */
    public String getType() {
        return Type;
    }
    /**
     * Getter for the Start date/time
     * @return start date/time
     */
    public LocalDateTime getStart() {
        return Start;
    }
    /**
     * Getter for the End date/time
     * @return End date/time
     */
    public LocalDateTime getEnd() {
        return End;
    }
    /**
     * Getter for Create date
     * @return Create date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }
    /**
     * Getter for Create by
     * @return Create by
     */
    public String getCreated_By() {
        return Created_By;
    }
    /**
     * Getter for Last update
     * @return Last Update
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }
    /**
     * Getter for Last updated by
     * @return Last updated by
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }
    /**
     * Getter for Customer ID
     * @return Customer ID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }
    /**
     * Getter for User ID
     * @return User ID
     */
    public int getUser_ID() {
        return User_ID;
    }
    /**
     * Getter for Contact ID
     * @return Contact ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }
    /**
     * Setter for Appointment ID
     * @param Appointment_ID ID to be set
     */
    public void setAppointment_ID(int Appointment_ID) {
        this.Appointment_ID = Appointment_ID;
    }
    /**
     * Setter for Title
     * @param Title Title to be set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }
    /**
     * Setter for Description
     * @param Description Description to be set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
    /**
     * Setter for Location
     * @param Location Location to be set
     */
    public void setLocation(String Location) {
        this.Location = Location;
    }
    /**
     * Setter for Type
     * @param Type Type to be set
     */
    public void setType(String Type) {
        this.Type = Type;
    }
    /**
     * Setter for Start Date/time
     * @param Start Date/Time to be set for start
     */
    public void setStart(LocalDateTime Start) {
        this.Start = Start;
    }
    /**
     * Setter for End Date/Time
     * @param End Date/Time to be set for end
     */
    public void setEnd(LocalDateTime End) {
        this.End = End;
    }
    /**
     * Setter for Create Date
     * @param Create_Date Date to be set for created date
     */
    public void setCreate_Date(LocalDateTime Create_Date) {
        this.Create_Date = Create_Date;
    }
    /**
     * Setter for Created By
     * @param Created_By value to be set for Created By
     */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }
    /**
     * Setter for Last update
     * @param Last_Update Value to be set for last update
     */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }
    /**
     * Setter for Last updated by
     * @param Last_Updated_By Value to be set for Last Updated By
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }
    /**
     * Setter for Customer ID
     * @param Customer_ID Value to be set for Customer ID
     */
    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }
    /**
     * Setter for User ID
     * @param User_ID User ID to be set
     */
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }
    /**
     * Setter for Contact ID
     * @param Contact_ID Contact ID to be set
     */
    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }


    }

