package DAO;

import java.sql.SQLException;

public interface CustomerDAO extends DAO {

    int getID(String divName) throws SQLException;

}
