package DAO;

import Model.Customer;
import Utility.DateAndTimeHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class CustomerDAOImp implements CustomerDAO {

    /**CRUD Retrieve.
     * Retrieval of all customers in a sorted manner by ID.
     * @return customerResult ObservableList for viewing. */
    @Override
    public ObservableList<Customer> getAll() throws SQLException {
        ObservableList<Customer> customerResult = FXCollections.observableArrayList();
        String sql = "SELECT client_schedule.customers.Customer_ID, client_schedule.customers.Customer_Name, " +
                "client_schedule.customers.Address, client_schedule.customers.Postal_Code, " +
                "client_schedule.customers.Phone, client_schedule.customers.Create_Date, " +
                "client_schedule.customers.Created_By, client_schedule.customers.Last_Update, " +
                "client_schedule.customers.Last_Updated_By, client_schedule.customers.Division_ID, " +
                "client_schedule.first_level_divisions.Division, client_schedule.countries.Country_ID, " +
                "client_schedule.countries.Country FROM client_schedule.customers INNER JOIN " +
                "client_schedule.first_level_divisions ON " +
                "client_schedule.customers.Division_ID=client_schedule.first_level_divisions.Division_ID " +
                "INNER JOIN client_schedule.countries ON " +
                "client_schedule.first_level_divisions.Country_ID=client_schedule.countries.Country_ID;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            int divisionID = rs.getInt("Division_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            String division = rs.getString("Division");
            String country = rs.getString("Country");
            Customer newCustomer = new Customer(customerID, divisionID, name, address, zip, phone, createDate,
                    createBy, lastUpdate, lastUpdateBy, division, country);
            customerResult.add(newCustomer);
        }
        Comparator<Customer> customerComparator = Comparator.comparing(Customer::getCustomerID);
        customerResult.sort(customerComparator);
        return customerResult;
    }

    /**CRUD Create and Update.
     * @param newCustomer object to be inserted.
     * @return ps.executeUpdate() int */
    @Override
    public int insert(Object newCustomer) throws SQLException {
        String sql = "INSERT INTO client_schedule.customers (Customer_Name, Address, Postal_Code, Phone, Create_Date," +
                "Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, ((Customer) newCustomer).getName());
        ps.setString(2, ((Customer) newCustomer).getAddress());
        ps.setString(3, ((Customer) newCustomer).getZipcode());
        ps.setString(4, ((Customer) newCustomer).getPhoneNumber());
        ps.setTimestamp(5, ((Customer) newCustomer).getCreateDateStamp());
        ps.setString(6, ((Customer) newCustomer).getCreateBy());
        ps.setTimestamp(7, ((Customer) newCustomer).getLastUpdateStamp());
        ps.setString(8, ((Customer) newCustomer).getLastUpdateBy());
        ps.setInt(9, ((Customer) newCustomer).getDivisionID());
        return ps.executeUpdate();
    }

    /**CRUD Update.
     * @param modifiedCustomer object to be updated.
     * @return ps.executeUpdate() int*/
    @Override
    public int update(Object modifiedCustomer) throws SQLException {
        String sql = "UPDATE client_schedule.customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, " +
                "Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, ((Customer) modifiedCustomer).getName());
        ps.setString(2, ((Customer) modifiedCustomer).getAddress());
        ps.setString(3, ((Customer) modifiedCustomer).getZipcode());
        ps.setString(4, ((Customer) modifiedCustomer).getPhoneNumber());
        ps.setTimestamp(5, ((Customer) modifiedCustomer).getLastUpdateStamp());
        ps.setString(6, (((Customer) modifiedCustomer).getLastUpdateBy()));
        ps.setInt(7, ((Customer) modifiedCustomer).getDivisionID());
        ps.setInt(8, ((Customer) modifiedCustomer).getCustomerID());
        return ps.executeUpdate();
    }

    /**CRUD Delete.
     * @param customer object to be deleted.*/
    @Override
    public int delete(Object customer) throws SQLException {
            String sql = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1,((Customer) customer).getCustomerID());
            return ps.executeUpdate();
    }

    /**Overloaded CRUD Retrieve for divID.
     * @param divName name of division.
     * @return int division ID. */
    @Override
    public int getDivId(String divName) throws SQLException {
        String sql = "SELECT Division_ID FROM client_schedule.first_level_divisions WHERE Division = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, divName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("Division_ID");
        } else return -1;
    }

    /**Checks if customer has any appointments.
     * @param customerID customer to check
     * @return boolean true if appointment exists*/
    @Override
    public boolean appointmentExists(int customerID) throws SQLException {
        String sql = "SELECT Appointment_ID FROM client_schedule.appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    @Override
    public void deleteAppointments(int customerID) throws SQLException {
        String sql = "DELETE FROM client_schedule.appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.executeUpdate();
    }

    //Overridden but unused CRUD methods--------------------------------------------------------------------------------

    /**CRUD Retrieve.
     * Retrieval of one object.
     * @param id indexing or PK/FK id. */
    @Override
    public Object get(int id) {
        return null;
    }

}
