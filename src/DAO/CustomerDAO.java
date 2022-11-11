package DAO;

import java.sql.SQLException;

/**Interface extension of DAO for Customer objects. */
public interface CustomerDAO extends DAO {

    int getDivId(String divName) throws SQLException;

    boolean appointmentExists(int customerID) throws SQLException;

    void deleteAppointments(int customerID) throws SQLException;
}
