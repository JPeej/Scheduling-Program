package DAO;

import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.util.HashMap;

public interface AppointmentDAO extends DAO {

    ObservableList<String> getCustomerNames();

    ObservableList<String> getContactNames();

    HashMap<Timestamp, Timestamp> getAppointments(int customerID);

    int cusNameToID(String customerName);

    int conNameToID(String contactName);

    boolean appointmentExists(int apptID);
}
