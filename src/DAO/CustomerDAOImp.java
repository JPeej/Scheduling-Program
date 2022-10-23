package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO{

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
    public List getAll() throws SQLException {
        List customerResult = new ArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps =JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createDate = rs.getString("Create_Date");
            String createBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int divID = rs.getInt("Division_ID");
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
