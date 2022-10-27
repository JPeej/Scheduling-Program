package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**Interface extension of DAO for Customer objects. */
public interface CustomerDAO extends DAO {

    /**Getter for division ID. */
    int getDivId(String divName) throws SQLException;

    ArrayList<Integer> getAllDivIDs() throws SQLException;

}
