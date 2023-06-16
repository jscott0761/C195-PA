package dbclientapp.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Customer {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Division_ID;

    /**
     * Constructor for Customer class
     *
     * @param Customer_ID
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Division_ID
     */
    public Customer(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, LocalDateTime Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Division_ID = Division_ID;
    }

    /**
     * Getter for Customer ID
     * @return Customer ID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }
    /**
     * Getter for Customer Name
     * @return Customer Name
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }
    /**
     * Getter for  Address
     * @return  Address
     */
    public String getAddress() {
        return Address;
    }
    /**
     * Getter for Postal Code
     * @return Postal Code
     */
    public String getPostal_Code() {
        return Postal_Code;
    }
    /**
     * Getter for Phone
     * @return Phone
     */
    public String getPhone() {
        return Phone;
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
     * Getter for Last Updated By
     * @return Last Updated By
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }
    /**
     * Getter for Division ID
     * @return Division ID
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * Setter for Customer ID
     * @param Customer_ID the Customer ID to set
     */
    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }
    /**
     * Setter for Customer Name
     * @param Customer_Name Customer name to be set
     */
    public void setCustomer_Name(String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }
    /**
     * Setter for Address
     * @param Address Customer name to be set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * Setter for Postal Code
     * @param Postal_Code the Postal code to be set
     */
    public void setPostal_Code(String Postal_Code) {
        this.Postal_Code = Postal_Code;
    }

    /**
     * Setter for Phone
     * @param Phone Phone number to be set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
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
     * @param Created_By Value for Created By to be set
     */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /**
     * Setter for Last Update
     * @param Last_Update Value for Last Update to be set
     */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    /**
     * Setter for Last Updated By
     * @param Last_Updated_By Value for last updated by to be set
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    /**
     * Setter for division ID
     * @param Division_ID Division ID to be set
     */
    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }
}