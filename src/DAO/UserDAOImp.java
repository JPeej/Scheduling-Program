package DAO;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public User get(String userNameLogin, String passwordLogin) throws SQLException {
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
    public Object get(int id) throws SQLException {
        return null;
    }

    @Override
    public List getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Object o) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Object o) throws SQLException {
        return 0;
    }

    @Override
    public int update(Object o) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Object o) throws SQLException {
        return 0;
    }

}
