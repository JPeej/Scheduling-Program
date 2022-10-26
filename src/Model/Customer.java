package Model;

import java.time.ZonedDateTime;

/**Manages Customer objects within program. */
public class Customer {

    private int customerID;
    private String name;
    private String address;
    private String zipcode;
    private String phoneNumber;
    private String createDate;
    private String createBy;
    private String lastUpdate;
    private String lastUpdateBy;
    private String country;
    private String division;
    public Customer(int customerID, String name, String address, String zipcode, String phoneNumber,
                    String createDate, String createBy, String lastUpdate, String lastUpdateBy,
                    String division, String country) {

        this.customerID = customerID;
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
}
