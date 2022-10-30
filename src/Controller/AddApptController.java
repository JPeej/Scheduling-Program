package Controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImp;
import DAO.JDBC;
import Model.Appointment;
import Utility.DateAndTimeHandler;
import Utility.MyAlerts;
import Utility.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

/**Controller for Add Appointment menu. */
public class AddApptController implements Initializable {

    @FXML private TextField titleText;
    @FXML private TextField descriptText;
    @FXML private TextField locText;
    @FXML private ComboBox customerSel;
    @FXML private ComboBox contactSel;
    @FXML private ComboBox typeSel;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ComboBox startTime;
    @FXML private ComboBox endTime;
    Nav nav = new Nav();
    AppointmentDAO appointmentDAO = new AppointmentDAOImp();

    /**Event handler to Appointment Menu.
     * Saves data and updates database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveAppt(ActionEvent actionEvent) {
        try {
            String title = titleText.getText();
            String description = descriptText.getText();
            String location = locText.getText();
            String type = typeSel.getValue().toString();
            String contact = contactSel.getValue().toString();
            int customerID = appointmentDAO.cusNameToID(customerSel.getValue().toString());
            int contactID = appointmentDAO.conNameToID(contact);
            Timestamp apptStart =
                    DateAndTimeHandler.toTimestamp(startDate.getValue(), (LocalTime) startTime.getValue());
            Timestamp apptEnd =
                    DateAndTimeHandler.toTimestamp(endDate.getValue(), (LocalTime) endTime.getValue());
            if (checkBlanks(title, description, location, type, customerID, contact, apptStart, apptEnd)) {
                Timestamp createDateTime = DateAndTimeHandler.dateTimeToDB(ZonedDateTime.now().toString());
                Timestamp lastUpdateDateTime = DateAndTimeHandler.dateTimeToDB(ZonedDateTime.now().toString());
                int userID = JDBC.userID;
                String user = JDBC.user;
                Appointment newAppoint = new Appointment(title, description, type, location, contact, apptStart, apptEnd,
                        createDateTime, lastUpdateDateTime, user, user, contactID, customerID, userID);
                int rowsAffected = appointmentDAO.insert(newAppoint);
                if (rowsAffected > 0) {
                    nav.toAppointmentsMenu(actionEvent);
                    MyAlerts.alertInfo("New appointment created.");
                } else MyAlerts.alertError("New appointment SQL insertion failed. ");
            } else MyAlerts.alertError("Please fill all fields and choices. ");
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed.\nPlease restart program. " +
                    "Report to IT if problem continues.");
        }catch (SQLException | NullPointerException e) {
            MyAlerts.alertError("Please select/enter a value for every field.");
        }
    }

    /**Event handler to Appointment Menu.
     * Discards any changes, makes no changes to database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    public boolean checkBlanks(String title, String description, String location, String type, int cusID,
                               String contact, Timestamp startDT, Timestamp endDT) {
        return !(title.isBlank() | description.isBlank() | location.isBlank() | type.isBlank() |
                String.valueOf(cusID).isBlank() | contact.isBlank() | String.valueOf(startDT.toString()).isBlank() |
                String.valueOf(endDT.toString()).isBlank());
    }

    /**Initial method called upon screen load.
     * Sets values into appropriate combo boxes. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerSel.setItems(appointmentDAO.getCustomerNames());
        contactSel.setItems(appointmentDAO.getContactNames());
        typeSel.setItems(Appointment.types);
        DateAndTimeHandler.appointmentTimes();
        startTime.setItems(Appointment.times);
        endTime.setItems(Appointment.times);
    }

    public void onActionEndCombo(ActionEvent actionEvent) {
    }
}
