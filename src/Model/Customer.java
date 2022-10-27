package Model;

import java.sql.Timestamp;

/**Manages Customer objects within program. */
public class Customer {

    private int customerID;
    private int divisionID;
    private String name;
    private String address;
    private String zipcode;
    private String phoneNumber;
    private String createBy;
    private String lastUpdateBy;
    private String country;
    private String division;
    private String createDate;
    private String lastUpdate;
    private Timestamp createDateStamp;
    private Timestamp lastUpdateStamp;

    /**Constructor for Customer, retrieval.*/
    public Customer(int customerID, int divisionID, String name, String address, String zipcode, String phoneNumber,
                    String createDate, String createBy, String lastUpdate, String lastUpdateBy,
                    String division, String country) {

        this.customerID = customerID;
        this.divisionID = divisionID;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createBy = createBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.division = division;
        this.country = country;
    }

    /**Constructor for Customer, insertion.*/
    public Customer(int divisionID, String name, String address, String zipcode, String phoneNumber,
                    Timestamp createDateStamp, String createBy, Timestamp lastUpdateStamp, String lastUpdateBy) {
        this.divisionID = divisionID;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.createDateStamp = createDateStamp;
        this.createBy = createBy;
        this.lastUpdateStamp = lastUpdateStamp;
        this.lastUpdateBy = lastUpdateBy;
    }

    //Getters & Setters ------------------------------------------------------------------------------------------------

    /**Get customer ID.
     * @return this.customerID. */
    public int getCustomerID() {
        return customerID;
    }

    /**Set customer ID.
     * @param customerID int. */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**Get customer name.
     * @return this.name.*/
    public String getName() {
        return name;
    }

    /**Set user name.
     * @param name String. */
    public void setName(String name) {
        this.name = name;
    }

    /**Get customer address.
     * @return this.address. */
    public String getAddress() {
        return address;
    }

    /**Set customer address.
     * @param address String. */
    public void setAddress(String address) {
        this.address = address;
    }

    /**Get user zipcode.
     * @return this.zipcode. */
    public String getZipcode() {
        return zipcode;
    }

    /**Set customer zipcode.
     * @param zipcode String. */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**Get customer phone number.
     * @return this.phoneNumber. */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**Set customer phone number.
     * @param phoneNumber String. */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**Get customer create date.
     * @return this.createDate. */
    public String getCreateDate() {
        return createDate;
    }

    /**Set customer create date.
     * @param createDate ZoneDateTime */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**Get customer created by.
     * @return this.createdBy. */
    public String getCreateBy() {
        return createBy;
    }

    /**Set customer create by.
     * @param createBy String */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**Get customer last update.
     * @return this.lastUpdate. */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**Set customer last update.
     * @param lastUpdate ZonedDateTime */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**Get customer last update by.
     * @return this.lastUpdateBy. */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**Set customer last update by.
     * @param lastUpdateBy String. */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**Get customer country.
     * @return this.country. */
    public String getCountry() {
        return country;
    }

    /**Set customer country.
     * @param country String. */
    public void setCountry(String country) {
        this.country = country;
    }

    /**Get customer division.
     * @return this.division. */
    public String getDivision() {
        return division;
    }

    /**Set customer division.
     * @param division String. */
    public void setDivision(String division) {
        this.division = division;
    }

    /**Get customer divisionID.
     * @return this.divisionID. */
    public int getDivisionID() {
        return divisionID;
    }

    /**Set customer divisionID.
     * @param divisionID int. */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**Get customer createDateStamp.
     * @return this.createDateStamp. */
    public Timestamp getCreateDateStamp() {
        return createDateStamp;
    }

    /**Set customer createDateStamp.
     * @param createDateStamp Timestamp. */
    public void setCreateDateStamp(Timestamp createDateStamp) {
        this.createDateStamp = createDateStamp;
    }

    /**Get customer lastUpdateStamp.
     * @return this.lastUpdateStamp. */
    public Timestamp getLastUpdateStamp() {
        return lastUpdateStamp;
    }

    /**Set customer lastUpdateStamp.
     * @param lastUpdateStamp Timestamp. */
    public void setLastUpdateStamp(Timestamp lastUpdateStamp) {
        this.lastUpdateStamp = lastUpdateStamp;
    }
}
