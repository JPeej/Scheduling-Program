package Model;

import DAO.JDBC;
import Utility.MyAlerts;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    /**Constructor for Customer, insertion.
     * Validates values. */
    public Customer(int divisionID, String name, String address, String zipcode, String phoneNumber,
                    Timestamp createDateStamp, String createBy, Timestamp lastUpdateStamp, String lastUpdateBy) {
        if (valueValidation(divisionID, name, address, zipcode, phoneNumber)) {
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
    }

    /**Method to call all validation methods.
     * @param divID
     * @param name
     * @param address
     * @param zip
     * @param phone */
    public boolean valueValidation(int divID, String name, String address, String zip, String phone) {
        ArrayList<Boolean> booleans = new ArrayList<>();
        booleans.add( validateDivID(divID));
        booleans.add(validateName(name));
        booleans.add(validateAddress(address));
        booleans.add(validateZip(zip));
        booleans.add(validatePhone(phone));
        return !booleans.contains(false);
    }

    /**Validate provided division ID for customer creation.
     * If no division is selected divID = 0 and no match exists.
     * Prompts user with error.
     * @param divID
     * @return boolean*/
    public boolean validateDivID(int divID) {
        try {
            String sql = "SELECT * FROM client_schedule.first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, divID);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                MyAlerts.alertError("Please select a state / province after selecting a country.");
                return false;
            } else return true;
        } catch (SQLException e) {
            MyAlerts.alertError("Please select a state / province after selecting a country.");}
            return false;
        }

    /**Validate provided name for customer creation.
     * Split string argument and check size. .
     * Prompts user with error.
     * @param name  */
    //TODO not working upon first test
    //TODO waiting upon instructor response for correct implementation of customer name
    public boolean validateName(String name){
        String[] nameSplit = name.split(" ");
        if (Arrays.stream(nameSplit).count() < 2) {
            MyAlerts.alertError("Please enter a first and last name.");
            return false;
        } else return true;
    }

    /**Validate provided address for customer creation.
     * Check for non Alphanumeric characters.
     * Check size, address should at least contain building number and street as is found in pre-populated database.
     * Check that building number is provided.
     * Prompts user with error.
     * @param address */
    public boolean validateAddress(String address) {
        for (int i = 0; i < address.length(); i++) {
            Character testChar = address.charAt(i);
            if (!(Character.isAlphabetic(testChar) | Character.isDigit(testChar) | Character.isSpaceChar(testChar))) {
                MyAlerts.alertError("No non-alphanumeric characters in address.");
                return false;
            }
        }

        String[] splitAddress = address.split(" ");
        if (!(Arrays.stream(splitAddress).count() >= 2)) {
            MyAlerts.alertError("Please format address as one of the following:\n" +
                                        "U.S. address: 123 ABC Street, White Plains\n" +
                                        "Canadian address: 123 ABC Street, Newmarket\n" +
                                        "UK address: 123 ABC Street, Greenwich, London");
            return false;
        }

        String houseNumber = splitAddress[0];
        for (int i = 0; i < houseNumber.length(); i++) {
            Character testChar = houseNumber.charAt(i);
            if (!Character.isDigit(testChar)) {
                MyAlerts.alertError("Please enter a building number first in the address field.");
                return false; }
        } return true;
    }

    /**Validate provided zipcode for customer creation.
     * Zip/Postal codes may have Alphanumeric characters and '-'.
     * Checks for spaces.
     * @param zip*/
    public boolean validateZip(String zip) {
        if (zip.contains(" ")) {
            MyAlerts.alertError("No spaces allowed in zip code.");
            return false;
        } else return true;
    }

    /**Validate provided phone number for customer creation.
     * Verifies no spaces.
     * Verifies separated by '-'.
     * Verifies digits only.
     * @param phone*/
    public boolean validatePhone(String phone) {
        if (phone.contains(" ")) {
            MyAlerts.alertError("No spaces allowed in phone number.");
            return false;
        }
        if (!phone.contains("-")) {
            MyAlerts.alertError("Please separate with '-'.");
            return false;
        }
        String phoneReduced = phone.replaceAll("-", "");
        for (int i = 0; i < phoneReduced.length(); i++) {
            if (!Character.isDigit(i)) {
                MyAlerts.alertError("Only digits in phone number.");
                return false;
            }
        } return true;
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
