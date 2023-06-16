package dbclientapp.Model;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    private int User_ID;
    private String User_Name;
    private String Password;
    private Date Created_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    /**
     * Constructor for User Class
     * @param User_ID
     * @param User_Name
     * @param Password
     * @param Created_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     */
    public User(int User_ID, String User_Name, String Password, Date Created_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By){
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
        this.Created_By = Created_By;
        this.Created_Date = Created_Date;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }
    /**
     * Getter for the User_ID
     * @return the User_ID
     */
    public int getUser_ID(){return User_ID;}
    /**
     * Getter for the User_Name
     * @return the User_Name
     */
    public String getUser_Name(){return User_Name;}
    /**
     * Getter for the Password
     * @return the Password
     */
    public String getPassword(){return Password;}
    /**
     * Getter for the Created_Date
     * @return the Created_Date
     */
    public Date getCreated_Date(){return Created_Date;}
    /**
     * Getter for the Created_By
     * @return the Created_By
     */
    public String getCreated_By(){return Created_By;}
    /**
     * Getter for the Last_Update
     * @return the Last_Update
     */
    public Timestamp getLast_Update(){return Last_Update;}
    /**
     * Getter for the Last_Updated_By
     * @return the Last_Updated_By
     */
    public String getLast_Updated_By(){return Last_Updated_By;}

    /**
     * Setter for User_ID
     * @param User_ID  the User ID to set
     */
    public void setUser_ID(int User_ID){
        this.User_ID = User_ID;}
    /**
     * Setter for User_Name
     * @param User_Name the username to set
     */
    public void setUser_Name(String User_Name){this.User_Name = User_Name;}
    /**
     * Setter for Password
     * @param Password the password to set
     */
    public void setPassword(String Password){this.Password = Password;}
    /**
     * Setter for Created_Date
     * @param Created_Date the date to set
     */
    public void setCreated_Date(Date Created_Date){this.Created_Date = Created_Date;}
    /**
     * Setter for Created_By
     * @param Created_By the value for created by
     */
    public void setCreated_By(String Created_By){this.Created_By = Created_By;}
    /**
     * Setter for Last_Update
     * @param Last_Update the last_update
     */
    public void setLast_Update(Timestamp Last_Update){this.Last_Update = Last_Update;}
    /**
     * Setter for Last_Updated_By
     * @param Last_Updated_By value for who last updated user
     */
    public void setLast_Updated_By(String Last_Updated_By){this.Last_Updated_By = Last_Updated_By;}
}
