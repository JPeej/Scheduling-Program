package Model;

import java.sql.Timestamp;

/**Manages Appointment objects within program. */
public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String type;
    private String location;
    private String contact;
    private String start;
    private String end;
    private Timestamp startStamp;
    private Timestamp endStamp;
    private int contactID;
    private int customerID;
    private int userID;

    /**Constructor for Appointment class, retrieval. */
    public Appointment(int appointmentID, String title, String description, String type, String location,
                       String contact, String start, String end, int customerID, int userID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
        this.contact = contact;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
    }


    //Getters & Setters ------------------------------------------------------------------------------------------------
    /**Get appointment ID.
     * @return this.appointmentID. */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**Set appointment ID.
     * @param appointmentID int. */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**Get appointment title.
     * @return this.title. */
    public String getTitle() {
        return title;
    }

    /**Set appointment title.
     * @param title String. */
    public void setTitle(String title) {
        this.title = title;
    }

    /**Get appointment description.
     * @return this.description. */
    public String getDescription() {
        return description;
    }

    /**Set appointment description.
     * @param description String. */
    public void setDescription(String description) {
        this.description = description;
    }

    /**Get appointment location.
     * @return this.location. */
    public String getLocation() {
        return location;
    }

    /**Set appointment location.
     * @param location String. */
    public void setLocation(String location) {
        this.location = location;
    }

    /**Get appointment customer ID.
     * @return this.customerID. */
    public int getCustomerID() {
        return customerID;
    }

    /**Set appointment customer ID.
     * @param customerID int. */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**Get appointment contact ID.
     * @return this.contactID. */
    public int getUserID() {
        return userID;
    }

    /**Set appointment contact ID.
     * @param userID int. */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**Get appointment type.
     * @return this.type. */
    public String getType() {
        return type;
    }

    /**Set appointment type.
     * @param type String. */
    public void setType(String type) {
        this.type = type;
    }

    /**Get appointment start stamp.
     * @return this.startStamp. */
    public Timestamp getStartStamp() {
        return startStamp;
    }

    /**Set appointment start stamp.
     * @param startStamp Timestamp. */
    public void setStartStamp(Timestamp startStamp) {
        this.startStamp = startStamp;
    }

    /**Get appointment end stamp.
     * @return this.endStamp. */
    public Timestamp getEndStamp() {
        return endStamp;
    }

    /**Set appointment end stamp.
     * @param endStamp Timestamp. */
    public void setEndStamp(Timestamp endStamp) {
        this.endStamp = endStamp;
    }

    /**Get appointment start.
     * @return this.start. */
    public String getStart() {
        return start;
    }

    /**Set appointment start.
     * @param start String. */
    public void setStart(String start) {
        this.start = start;
    }

    /**Get appointment end.
     * @return this.end. */
    public String getEnd() {
        return end;
    }

    /**Set appointment end.
     * @param end String. */
    public void setEnd(String end) {
        this.end = end;
    }

    /**Get appointment contact.
     * @return this.contact. */
    public String getContact() {
        return contact;
    }

    /**Set appointment contact.
     * @param contact String. */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**Get appointment contactId.
     * @return this.contactID. */
    public int getContactID() {
        return contactID;
    }

    /**Set appointment contact Id.
     * @param contactID int. */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
