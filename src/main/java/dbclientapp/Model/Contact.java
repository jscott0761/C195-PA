package dbclientapp.Model;

public class Contact {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    /**
     * Constructor for Contact Class
     *
     * @param Contact_ID
     * @param Contact_Name
     * @param Email
     */
    public Contact(int Contact_ID, String Contact_Name, String Email) {
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
    }

    /**
     * Getter for Contact ID
     *
     * @return Contact ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * Getter for Contact Name
     *
     * @return Contact Name
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     * Getter for Email
     *
     * @return Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Setter for Contact ID
     *
     * @param Contact_ID Contact ID to be set
     */
    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }

    /**
     * Setter for Contact Name
     *
     * @param Contact_Name Contact Name to be set
     */
    public void setContact_Name(String Contact_Name) {
        this.Contact_Name = Contact_Name;
    }

    /**
     * Setter for email
     *
     * @param Email email to be set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    /**
     * Overrides toString method to make results in combo boxes understandable by the application user
     * @return Contact Name
     */
    @Override
    public String toString() {
        return (Contact_Name);
    }
}
