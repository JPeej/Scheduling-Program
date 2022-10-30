package DAO;

import Model.Appointment;
import Utility.DateTimeConverter;
import Utility.MyAlerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Locale;

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
            String start = DateTimeConverter.dateTimeToClient(rs.getTimestamp("Start"));
            String end = DateTimeConverter.dateTimeToClient(rs.getTimestamp("End"));
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
     * @param o object to be inserted.
     */
    @Override
    public int insert(Object o) throws SQLException {
        return 0;
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

    @Override
    public int cusNameToID() {
        return 0;
    }

    @Override
    public int conNameToID() {
        return 0;
    }
}
