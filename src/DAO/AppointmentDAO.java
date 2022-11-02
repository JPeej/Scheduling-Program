package DAO;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public interface AppointmentDAO extends DAO {

    ObservableList<String> getCustomerNames() throws SQLException;

    ObservableList<String> getContactNames() throws SQLException;

    HashMap<Timestamp, Timestamp> getAppointments(int customerID) throws SQLException;

    int cusNameToID(String customerName) throws SQLException;

    int conNameToID(String contactName) throws SQLException;

    boolean appointmentExists(int apptID) throws SQLException;
}
