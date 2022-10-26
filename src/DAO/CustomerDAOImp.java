package DAO;

import Model.Customer;
import Utility.DateTimeConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO {

    /**CRUD Retrieve.
     * Retrieval of one object.
     * @param id indexing or PK/FK id. */
    @Override
    public Object get(int id) throws SQLException {
        return null;
    }

    /**CRUD Retrieve.
     * Retrieval of all objects of one object type. */
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
            Customer newCustomer = new Customer(customerID, name, address, zip, phone, zonedCreateDate, createBy,
                                   zonedLastUpdate, lastUpdateBy, division, country);
            customerResult.add(newCustomer);
        } return customerResult;
    }

    /**CRUD Create.
     * @param o object to be created. */
    @Override
    public int save(Object o) throws SQLException {
        return 0;
    }

    /**CRUD Create and Update.
     * @param o object to be inserted.*/
    @Override
    public int insert(Object o) throws SQLException {
        return 0;
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
