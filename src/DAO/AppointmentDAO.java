package DAO;

import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public interface AppointmentDAO extends DAO {

    public ObservableList<String> getCustomerNames();

    public ObservableList<String> getContactNames();

    public HashMap<Timestamp, Timestamp> getAppointments(int customerID);

    public int cusNameToID(String customerName);

    public int conNameToID(String contactName);
}
