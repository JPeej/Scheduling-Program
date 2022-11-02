package DAO;

import Model.User;
import java.sql.SQLException;

/**Interface extension of DAO for User objects. */
public interface UserDAO extends DAO {


    User authenticateUser(String userNameLogin, String passwordLogin) throws SQLException;

    int getUserID(String userName) throws SQLException;

}
