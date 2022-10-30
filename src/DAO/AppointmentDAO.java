package DAO;

import javafx.collections.ObservableList;

public interface AppointmentDAO extends DAO {

    public ObservableList<String> getCustomerNames();

    public ObservableList<String> getContactNames();

    public int cusNameToID(String customerName);

    public int conNameToID(String contactName);
}
