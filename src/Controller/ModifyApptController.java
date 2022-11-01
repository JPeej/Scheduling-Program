package Controller;

import DAO.JDBC;
import Model.Appointment;
import Utility.MyAlerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**Controller for Modify Appointment menu. */
public class ModifyApptController extends AddApptController implements Initializable {

    @FXML private TextField apptIDText;
    @FXML private TextField conIDText;
    @FXML private TextField cusIDText;
    @FXML private TextField titleText;
    @FXML private TextField descriptText;
    @FXML private TextField locText;
    @FXML private DatePicker startDateSel;
    @FXML private DatePicker endDateSel;
    @FXML private ComboBox<LocalTime> startTimeCombo;
    @FXML private ComboBox<LocalTime> endTimeCombo;
    @FXML private ComboBox<String> conCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private ComboBox<String> cusCombo;

    /**Event handler to Appointment Menu.
     * Saves data and updates database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveAppt(ActionEvent actionEvent){
        try {
            String title = titleText.getText();
            String description = descriptText.getText();
            String location = locText.getText();
            String type = typeCombo.getValue().toString();
            String contact = conCombo.getValue().toString();
            int customerID = appointmentDAO.cusNameToID(cusCombo.getValue().toString());
            int contactID = appointmentDAO.conNameToID(contact);

            LocalDateTime ldtStart = LocalDateTime.of(startDateSel.getValue(), startTimeCombo.getValue());
            LocalDateTime ldtEnd = LocalDateTime.of(endDateSel.getValue(), endTimeCombo.getValue());
            Timestamp stampStart = Timestamp.valueOf(ldtStart);
            Timestamp stampEnd = Timestamp.valueOf(ldtEnd);
            Timestamp lastUpdateDateTime = Timestamp.valueOf(LocalDateTime.now());

            int appointmentID = Integer.parseInt(apptIDText.getText());
            int userID = JDBC.userID;
            String user = JDBC.user;

            if (checkDates(stampStart, stampEnd, customerID, appointmentID)) {
                if (checkBlanks(title, description, location, type, customerID, contact)) {
                    Appointment newAppoint = new Appointment(appointmentID, title, description, type, location, stampStart,
                            stampEnd, lastUpdateDateTime, user, contactID, customerID, userID);
                    int rowsAffected = appointmentDAO.update(newAppoint);
                    if (rowsAffected > 0) {
                        nav.toAppointmentsMenu(actionEvent);
                        MyAlerts.alertInfo("Appointment updated.");
                    } else MyAlerts.alertError("Appointment update to database failed.");
                } else MyAlerts.alertError("Please fill all fields and choices.");
            }
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed.\nPlease restart program. " +
                    "\nReport to IT if problem persists.");
        }catch (SQLException | NullPointerException e) {
            MyAlerts.alertError("Please select/enter a value for every field.");
        }
    }

    /**Populates Modify Appointment menu with appointment data.
     * @param appointment */
    public void sendAppt(Appointment appointment) {
        apptIDText.setText(String.valueOf(appointment.getAppointmentID()));
        cusIDText.setText(String.valueOf(appointment.getCustomerID()));
        conIDText.setText(String.valueOf(appointment.getContactID()));
        titleText.setText(appointment.getTitle());
        descriptText.setText(appointment.getDescription());
        locText.setText(appointment.getLocation());
        conCombo.getSelectionModel().select(appointment.getContact());
        cusCombo.getSelectionModel().select(appointment.getCustomer());
        typeCombo.getSelectionModel().select(appointment.getType());

        LocalDate localStartDate = appointment.getStartStamp().toLocalDateTime().toLocalDate();
        LocalDate localEndDate = appointment.getEndStamp().toLocalDateTime().toLocalDate();
        LocalTime localStartTime = appointment.getStartStamp().toLocalDateTime().toLocalTime();
        LocalTime localEndTime = appointment.getEndStamp().toLocalDateTime().toLocalTime();
        startDateSel.setValue(localStartDate);
        endDateSel.setValue(localEndDate);
        startTimeCombo.getSelectionModel().select(localStartTime);
        endTimeCombo.getSelectionModel().select(localEndTime);
    }

}