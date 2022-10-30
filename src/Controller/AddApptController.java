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
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

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
    public void onActionSaveAppt(ActionEvent actionEvent) throws IOException {
        String title = titleText.getText();
        String description = descriptText.getText();
        String location = locText.getText();
        int customerID = appointmentDAO.cusNameToID(customerSel.toString());
        int contactID = appointmentDAO.conNameToID(contactSel.toString());
        String type = typeSel.toString();
        Timestamp apptStart =
                DateAndTimeHandler.toTimestamp(startDate.getValue(), (LocalTime) startTime.getValue());
        Timestamp apptEnd =
                DateAndTimeHandler.toTimestamp(endDate.getValue(), (LocalTime) endTime.getValue());
        int user = JDBC.userID;
        Appointment newAppoint = new Appointment(title, description, type, location, apptStart, apptEnd, contactID,
                customerID, user);
        nav.toAppointmentsMenu(actionEvent);
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
        LocalTime startSelect = (LocalTime) startTime.getValue();
        LocalTime endSelect = (LocalTime) endTime.getValue();
        if (startSelect.isAfter(endSelect) | startSelect.equals(endSelect)) {
            MyAlerts.alertError("Please select an end time that is after the start time. ");
        }
    }

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
