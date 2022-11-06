package DAO;

import Model.User;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

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

    /**Gets ID of user.
     * @param userName user name entered
     * @return  positive int of ID if found*/
    @Override
    public int getUserID(String userName) throws SQLException {
        String sql = "SELECT User_ID FROM client_schedule.users WHERE ? = User_Name";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("User_ID");
        } else return -1;
    }

    /**Retrieval of appointments for the logged in user.
     * Used to check if user has appointments within the next 15 minutes.
     * @param userID id of the logged in user
     * @return HashMap key of appointment id and value of appointment start*/
    @Override
    public HashMap<String, Timestamp> getUserAppointments(int userID) throws SQLException {
        HashMap<String, Timestamp> userAppoints = new HashMap<>();
        String sql = "SELECT * FROM client_schedule.appointments WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String apptID = String.valueOf(rs.getInt("Appointment_ID"));
            Timestamp dateTime = rs.getTimestamp("Start");
            userAppoints.put(apptID, dateTime);
        } return userAppoints;
    }

    //Overridden but unused CRUD methods--------------------------------------------------------------------------------

    /**CRUD Retrieve.
     * Retrieval of one object.
     * @param id indexing or PK/FK id*/
    @Override
    public Object get(int id) {
        return null;
    }

    /**CRUD Retrieve.
     * Retrieval of all objects of one object type. */
    @Override
    public ObservableList getAll() {
        return null;
    }

    /**CRUD Create and Update.
     * @param o object to be inserted. */
    @Override
    public int insert(Object o) {
        return 0;
    }

    /**CRUD Update.
     * @param o object to be updated. */
    @Override
    public int update(Object o) {
        return 0;
    }

    /**CRUD Delete.
     * @param o object to be deleted. */
    @Override
    public int delete(Object o) {
        return 0;
    }

}
