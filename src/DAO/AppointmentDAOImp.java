package DAO;

import Model.Appointment;
import Utility.DateAndTimeHandler;
import Utility.MyAlerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashMap;

public class AppointmentDAOImp implements AppointmentDAO{

    /**
     * CRUD Retrieve.
     * Retrieval of one object.
     * @param id indexing or PK/FK id
     */
    @Override
    public Object get(int id) throws SQLException {
        return null;
    }

    /**
     * CRUD Retrieve.
     * Retrieval of all objects of one object type.
     * @return appointmentResult ObservableList of Appointment data
     */
    @Override
    public ObservableList<Appointment> getAll() throws SQLException {
        ObservableList<Appointment> appointmentResult = FXCollections.observableArrayList();
        String sql = "SELECT client_schedule.appointments.Appointment_ID, client_schedule.appointments.Title, " +
                "client_schedule.appointments.Description, client_schedule.appointments.Location, " +
                "client_schedule.appointments.Type, client_schedule.appointments.Start, " +
                "client_schedule.appointments.End, client_schedule.appointments.Customer_ID, " +
                "client_schedule.appointments.User_ID, client_schedule.appointments.Contact_ID, " +
                "client_schedule.contacts.Contact_ID, client_schedule.contacts.Contact_Name " +
                "FROM client_schedule.appointments INNER JOIN client_schedule.contacts ON " +
                "client_schedule.appointments.Contact_ID=client_schedule.contacts.Contact_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String contact = rs.getString("Contact_Name");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            int cusID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            Appointment newAppoint = new Appointment(appID, title, description, type, location, contact, start, end,
                    cusID, userID);
            appointmentResult.add(newAppoint);
        }
        Comparator<Appointment> appointComparator = Comparator.comparing(Appointment::getAppointmentID);
        appointmentResult.sort(appointComparator);
        return appointmentResult;
    }

    /**
     * CRUD Create and Update.
     * @param newAppoint object to be inserted.
     * @return int number of rows affected
     */
    @Override
    public int insert(Object newAppoint) throws SQLException {
        String sql = "INSERT INTO client_schedule.appointments (Title, Description, Location, Type, Start, End, " +
                "Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, ((Appointment) newAppoint).getTitle());
        ps.setString(2, ((Appointment) newAppoint).getDescription());
        ps.setString(3, ((Appointment) newAppoint).getLocation());
        ps.setString(4, ((Appointment) newAppoint).getType());
        ps.setTimestamp(5, ((Appointment) newAppoint).getStartStamp());
        ps.setTimestamp(6, ((Appointment) newAppoint).getEndStamp());
        ps.setTimestamp(7, ((Appointment) newAppoint).getCreatedDate());
        ps.setString(8, ((Appointment) newAppoint).getCreateBy());
        ps.setTimestamp(9, ((Appointment) newAppoint).getUpdateDate());
        ps.setString(10, ((Appointment) newAppoint).getUpdateBy());
        ps.setInt(11, ((Appointment) newAppoint).getCustomerID());
        ps.setInt(12, ((Appointment) newAppoint).getUserID());
        ps.setInt(13, ((Appointment) newAppoint).getContactID());
        return ps.executeUpdate();
    }

    /**
     * CRUD Update.
     * @param o object to be updated.
     */
    @Override
    public int update(Object o) throws SQLException {
        return 0;
    }

    /**
     * CRUD Delete.
     * @param o object to be deleted.
     */
    @Override
    public int delete(Object o) throws SQLException {
        return 0;
    }

    /**CRUD retrieval of customer names.
     * @return ObservableList of String for combo box population. */
    @Override
    public ObservableList<String> getCustomerNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Customer_Name FROM client_schedule.customers";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Customer_Name");
                names.add(name);
            }
        } catch (SQLException e) {
            MyAlerts.alertError("Customer data failed to load.");
        }
        return names;
    }

    /**CRUD retrieval of contact names.
     * @return ObservableList of String for combo box population. */
    @Override
    public ObservableList<String> getContactNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_Name FROM client_schedule.contacts";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Contact_Name");
                names.add(name);
            }
        } catch (SQLException e) {
            MyAlerts.alertError("Contact data failed to load.");
        }
        return names;
    }

    /**CRUD retrieval of customer appointments.
     * @return Hashmap of Timestamps for comparison. */
    @Override
    public HashMap<Timestamp, Timestamp> getAppointments(int customerID) {
        HashMap<Timestamp , Timestamp > appointments = new HashMap<>();
        try {
            String sql = "SELECT Start, End FROM client_schedule.appointments WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                start = DateAndTimeHandler.timestampToClient(start);
                end = DateAndTimeHandler.timestampToClient(end);
                appointments.put(start, end);
            }
        } catch (SQLException e) {
            MyAlerts.alertError("Appointment data failed to load.");
        } return appointments;
    }

    /**CRUD retrieval of customer ID for customer.
     * @param customerName
     * @return customer ID if found */
    @Override
    public int cusNameToID(String customerName) {
        try {
            String sql = "SELECT Customer_ID FROM client_schedule.customers WHERE ? = Customer_Name";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Customer_ID");
            } else return -1;
        } catch (SQLException e) {
            MyAlerts.alertError("Customer not found in database.");
        } return -1;
    }

    /**CRUD retrieval of contact ID for contact.
     * @param contactName
     * @return contact ID if found */
    @Override
    public int conNameToID(String contactName) {
        try {
            String sql = "SELECT Contact_ID FROM client_schedule.contacts WHERE ? = Contact_Name";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, contactName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Contact_ID");
            } else return -1;
        } catch (SQLException e) {
            MyAlerts.alertError("Contact not found in database.");
        } return -1;
    }
}
