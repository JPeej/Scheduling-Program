package DAO;

import Model.User;
import Utility.MyAlerts;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**CRUD class for User objects. */
public class UserDAOImp implements UserDAO{

    /**Retrieve of CRUD method for User objects.
     * Overloaded method for user authentication on login menu.
     * Passes SQL query to client_schedule database.
     * @param userNameLogin String.
     * @param passwordLogin String.
     * @return User if found and null if not.
     * */
    @Override
    public User authenticateUser(String userNameLogin, String passwordLogin) throws SQLException {
        String sql = "SELECT User_ID, User_Name, Password FROM users WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userNameLogin);
        ps.setString(2, passwordLogin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            return new User(userID, userName, password);
        } else {
            return null;
        }
    }

    @Override
    public int getUserID(String userName) {
        try {
            String sql = "SELECT User_ID FROM client_schedule.users WHERE ? = User_Name";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("User_ID");
            } else return -1;
        } catch (SQLException e) {
            MyAlerts.alertError("User not found.");
        } return -1;
    }

    //Overridden but null CRUD methods----------------------------------------------------------------------------------

    /**CRUD Retrieve.
     * Retrieval of one object.
     * @param id indexing or PK/FK id*/
    @Override
    public Object get(int id) throws SQLException {
        return null;
    }

    /**CRUD Retrieve.
     * Retrieval of all objects of one object type. */
    @Override
    public ObservableList getAll() throws SQLException {
        return null;
    }

    /**CRUD Create and Update.
     * @param o object to be inserted. */
    @Override
    public int insert(Object o) throws SQLException {
        return 0;
    }

    /**CRUD Update.
     * @param o object to be updated. */
    @Override
    public int update(Object o) throws SQLException {
        return 0;
    }

    /**CRUD Delete.
     * @param o object to be deleted. */
    @Override
    public int delete(Object o) throws SQLException {
        return 0;
    }
}
