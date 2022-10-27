package DAO;

import java.sql.SQLException;

/**Interface extension of DAO for Customer objects. */
public interface CustomerDAO extends DAO {

    /**Getter for division ID. */
    int getID(String divName) throws SQLException;

}
