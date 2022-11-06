package DAO;

import Model.User;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

/**Interface extension of DAO for User objects. */
public interface UserDAO extends DAO {


    User authenticateUser(String userNameLogin, String passwordLogin) throws SQLException;

    int getUserID(String userName) throws SQLException;

    HashMap<String, Timestamp> getUserAppointments(int userID) throws SQLException;

}
