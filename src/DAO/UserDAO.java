package DAO;

import Model.User;
import java.sql.SQLException;

/**Interface extension of DAO for User objects. */
public interface UserDAO extends DAO {

    /**Overloaded method for user authentication on login menu. */
    User get(String userNameLogin, String passwordLogin) throws SQLException;

}
