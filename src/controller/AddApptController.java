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
import java.util.*;

/**Controller for Add Appointment menu. */
public class AddApptController implements Initializable {

    @FXML private TextField titleText;
    @FXML private TextField descriptText;
    @FXML private TextField locText;
    @FXML private DatePicker startDateSel;
    @FXML private DatePicker endDateSel;
    @FXML private ComboBox<LocalTime> startTimeCombo;
    @FXML private ComboBox<LocalTime> endTimeCombo;
    @FXML private ComboBox<String> cusCombo;
    @FXML private ComboBox<String> conCombo;
    @FXML private ComboBox<String> typeCombo;
    Nav nav = new Nav();
    AppointmentDAO appointmentDAO = new AppointmentDAOImp();

    /**Event handler to Appointment Menu.
     * Calls methods to verify values.
     * Saves data and updates database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveAppt(ActionEvent actionEvent) {
        try {
            String title = titleText.getText();
            String description = descriptText.getText();
            String location = locText.getText();
            String type = typeCombo.getValue();
            String contact = conCombo.getValue();
            int customerID = appointmentDAO.cusNameToID(cusCombo.getValue());
            int contactID = appointmentDAO.conNameToID(contact);

            LocalDateTime ldtStart = LocalDateTime.of(startDateSel.getValue(), startTimeCombo.getValue());
            LocalDateTime ldtEnd = LocalDateTime.of(endDateSel.getValue(), endTimeCombo.getValue());
            Timestamp stampStart = Timestamp.valueOf(ldtStart);
            Timestamp stampEnd = Timestamp.valueOf(ldtEnd);
            Timestamp createDateTime = Timestamp.valueOf(LocalDateTime.now());
            Timestamp lastUpdateDateTime = Timestamp.valueOf(LocalDateTime.now());

            int userID = JDBC.userID;
            String user = JDBC.user;

            ArrayList<Boolean> valueChecks = new ArrayList<>();
            valueChecks.add(checkBlanks(title, description, location, type, customerID, contact));
            valueChecks.add(checkStartBeforeEnd(stampStart, stampEnd));
            valueChecks.add(checkApptNotInPast(stampStart));
            valueChecks.add(checkOverlap(stampStart,stampEnd,customerID, 0));
            if (!valueChecks.contains(false)) {
                Appointment newAppoint = new Appointment(title, description, type, location, contact, stampStart,
                        stampEnd, createDateTime, lastUpdateDateTime, user, user, contactID, customerID, userID);
                int rowsAffected = appointmentDAO.insert(newAppoint);
                if (rowsAffected > 0) {
                    nav.toAppointmentsMenu(actionEvent);
                    MyAlerts.alertInfo("New appointment created.");
                } else MyAlerts.alertError("Appointment creation to database failed.");
            }
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed.\nPlease restart program. " +
                    "\nReport to IT if problem continues.");
        } catch (SQLException | NullPointerException e) {
            MyAlerts.alertError("Please select/enter a value for every field.");
        }
    }

    /**Event handler to Appointment Menu.
     * Discards any changes, makes no changes to database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCancel(ActionEvent actionEvent) {
        try {
            nav.toAppointmentsMenu(actionEvent);
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT if issue persists.");
        }
    }

    /**Verifies user did not request a start to come after an end.
     * @param startRequest Timestamp created by startDate and startTime
     * @param endRequest Timestamp created by endDate and endTime
     * @return boolean true if start before end*/
    public boolean checkStartBeforeEnd(Timestamp startRequest, Timestamp endRequest) {
        if (startRequest.after(endRequest) | startRequest.equals(endRequest)) {
            MyAlerts.alertError("Please be sure appointment start date/time is before end date/time.");
            return false;
        } else return true;
    }

    /**Verifies user did not request an appointment in the past.
     * @param startRequest date and time timestamp of appointment start
     * @return boolean, true if not in past*/
    public boolean checkApptNotInPast(Timestamp startRequest) {
        Timestamp now = Timestamp.from(Instant.now());
        if (startRequest.before(now)) {
            MyAlerts.alertError("Appointments cannot be scheduled in the past.");
            return false;
        } else return true;
    }

    /**Verifies no overlap of previously scheduled appointments of the customer.
     * Prevents checking of the same appointmentID in case of no appointment Timestamp modification.
     * @param startRequest date and time timestamp of appointment start
     * @param endRequest date and time timestamp of appointment end
     * @param customerID customer to have appointment with
     * @param appointmentID appointment ID
     * @return boolean true if no appointment timestamp overlap exists*/
    public boolean checkOverlap(Timestamp startRequest, Timestamp endRequest, int customerID, int appointmentID) {
        try {
            HashMap<Timestamp, Timestamp> appointments = appointmentDAO.getAppointments(customerID);
            Timestamp confirmedStart;
            Timestamp confirmedEnd;
            for (Map.Entry<Timestamp, Timestamp> confirmedAppts : appointments.entrySet()) {
                if (!appointmentDAO.appointmentExists(appointmentID)) {
                    confirmedStart = confirmedAppts.getKey();
                    confirmedEnd = confirmedAppts.getValue();
                } else continue;
                if ((startRequest.after(confirmedStart) | startRequest.equals(confirmedStart))
                        && startRequest.before(confirmedEnd)) {
                    MyAlerts.alertError("Requested appointment overlaps appointment scheduled:\nStart: " +
                            confirmedAppts.getKey() + "\nEnd: " + confirmedAppts.getValue());
                    return false;
                } else if (endRequest.after(confirmedStart) &&
                        (endRequest.before(confirmedEnd)) | endRequest.equals(confirmedEnd)) {
                    MyAlerts.alertError("Requested appointment overlaps appointment scheduled:\nStart: " +
                            confirmedAppts.getKey() + "\nEnd: " + confirmedAppts.getValue());
                    return false;
                }
            } return true;
        } catch (SQLException e) {
            MyAlerts.alertError("SQL CRUD methods failed. Contact IT.");
        } return false;
    }

    /**Verifies users submitted data into all fields.
     * @param title title field submitted
     * @param description description field submitted
     * @param location location field submitted
     * @param type type field submitted
     * @param cusID customer field submitted
     * @param contact contact field submitted
     * @return boolean true if all fields filled*/
    public boolean checkBlanks(String title, String description, String location, String type, int cusID,
                               String contact) {
        if (title.isBlank() | description.isBlank() | location.isBlank() | type.isBlank() |
                String.valueOf(cusID).isBlank() | contact.isBlank()) {
            MyAlerts.alertError("Please fill all fields and choices. ");
            return false;
        } else return true;
    }

    /**Initial method called upon screen load.
     * Sets values into appropriate combo boxes. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cusCombo.setItems(appointmentDAO.getCustomerNames());
            conCombo.setItems(appointmentDAO.getContactNames());
            typeCombo.setItems(Appointment.types);
            DateAndTimeHandler.appointmentTimes();
            startTimeCombo.setItems(Appointment.times);
            endTimeCombo.setItems(Appointment.times);
        } catch (SQLException e) {
            MyAlerts.alertError("SQL CRUD methods failed. Contact IT.");
        }
    }

}