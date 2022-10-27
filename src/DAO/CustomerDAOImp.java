package DAO;

import Model.Customer;
import Utility.DateTimeConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerDAOImp implements CustomerDAO {

    /**CRUD Retrieve.
     * Retrieval of one object.
     * @param id indexing or PK/FK id. */
    @Override
    public Object get(int id) throws SQLException {
        return null;
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
            int divID = rs.getInt("Division_ID");
            return divID;
        } return -1;
    }

    @Override
    public ArrayList<Integer> getAllDivIDs() throws SQLException {
        return null;
    }

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
            String zonedCreateDate = DateTimeConverter.dateTimeToClient(rs.getTimestamp("Create_Date"));
            String createBy = rs.getString("Created_By");
            String zonedLastUpdate = DateTimeConverter.dateTimeToClient(rs.getTimestamp("Last_Update"));
            String lastUpdateBy = rs.getString("Last_Updated_By");
            String division = rs.getString("Division");
            String country = rs.getString("Country");
            Customer newCustomer = new Customer(customerID, divisionID, name, address, zip, phone, zonedCreateDate,
                    createBy, zonedLastUpdate, lastUpdateBy, division, country);
            customerResult.add(newCustomer);
        }
        Comparator<Customer> customerComparator = Comparator.comparing(Customer::getCustomerID);
        customerResult.sort(customerComparator);
        return customerResult;
    }

    /**CRUD Create and Update.
     * @param newCustomer object to be inserted.
     * @return ps.executeUpdate() int. */
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
     * @param o object to be updated.*/
    @Override
    public int update(Object o) throws SQLException {
        return 0;
    }

    /**CRUD Delete.
     * @param o object to be deleted.*/
    @Override
    public int delete(Object o) throws SQLException {
        return 0;
    }

}
