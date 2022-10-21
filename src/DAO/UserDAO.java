package DAO;

import Model.User;
import java.sql.SQLException;

public interface UserDAO extends DAO {

    User get(String userNameLogin, String passwordLogin) throws SQLException;

}
