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
import java.util.HashMap;
import java.util.Map;
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

            LocalDateTime ldtStart = LocalDateTime.of(startDate.getValue(), (LocalTime) startTime.getValue());
            LocalDateTime ldtEnd = LocalDateTime.of(endDate.getValue(), (LocalTime) endTime.getValue());
            Timestamp stampStart = Timestamp.valueOf(ldtStart);
            Timestamp stampEnd = Timestamp.valueOf(ldtEnd);
            Timestamp createDateTime = Timestamp.valueOf(LocalDateTime.now());
            Timestamp lastUpdateDateTime = Timestamp.valueOf(LocalDateTime.now());

            int userID = JDBC.userID;
            String user = JDBC.user;

            if (checkDates(stampStart, stampEnd, customerID)) {
                if (checkBlanks(title, description, location, type, customerID, contact)) {
                    Appointment newAppoint = new Appointment(title, description, type, location, contact, stampStart,
                            stampEnd, createDateTime, lastUpdateDateTime, user, user, contactID, customerID, userID);
                    int rowsAffected = appointmentDAO.insert(newAppoint);
                    if (rowsAffected > 0) {
                        nav.toAppointmentsMenu(actionEvent);
                        MyAlerts.alertInfo("New appointment created.");
                    } else MyAlerts.alertError("New appointment SQL insertion failed. ");
                } else MyAlerts.alertError("Please fill all fields and choices. ");
            }
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

    public void onActionEndCombo(ActionEvent actionEvent) {
    }

    public boolean checkDates(Timestamp startRequest, Timestamp endRequest, int customerID) {
        if (startRequest.after(endRequest) | startRequest.equals(endRequest)) {
            MyAlerts.alertError("Please be sure appointment start date/time is before end date/time.");
            return false;
        }

        if (startRequest.before(Timestamp.from(Instant.now()))) {
            MyAlerts.alertError("Appointments cannot be scheduled in the past.");
            return false;
        }

        HashMap<Timestamp, Timestamp> appointments = appointmentDAO.getAppointments(customerID);
        for (Map.Entry<Timestamp, Timestamp> confirmedAppts : appointments.entrySet()) {
            Timestamp confirmedStart = confirmedAppts.getKey();
            Timestamp confirmedEnd = confirmedAppts.getValue();
            if ((startRequest.after(confirmedStart) | startRequest.equals(confirmedStart))
                    && startRequest.before(confirmedEnd)) {
                MyAlerts.alertError("Requested appointment overlaps appointment scheduled:\nStart: " +
                        confirmedAppts.getKey() + "\nEnd: " + confirmedAppts.getValue() );
                return false;
            }
            else if (endRequest.after(confirmedStart) &&
                    (endRequest.before(confirmedEnd)) | endRequest.equals(confirmedEnd)) {
                MyAlerts.alertError("Requested appointment overlaps appointment scheduled:\nStart: " +
                        confirmedAppts.getKey() + "\nEnd: " + confirmedAppts.getValue() );
                return false;
            }
        } return true;
    }

    public boolean checkBlanks(String title, String description, String location, String type, int cusID,
                               String contact) {
        return !(title.isBlank() | description.isBlank() | location.isBlank() | type.isBlank() |
                String.valueOf(cusID).isBlank() | contact.isBlank());
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


}
