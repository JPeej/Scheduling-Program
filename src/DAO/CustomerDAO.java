package DAO;

import java.sql.SQLException;

/**Interface extension of DAO for Customer objects. */
public interface CustomerDAO extends DAO {

    /**Getter for division ID. */
    int getDivId(String divName) throws SQLException;

    int countAppointments(int CustomerID);
}
