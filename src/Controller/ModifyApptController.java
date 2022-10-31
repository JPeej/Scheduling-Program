package Controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImp;
import Model.Appointment;
import Utility.DateAndTimeHandler;
import Utility.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**Controller for Modify Appointment menu. */
public class ModifyApptController implements Initializable {

    public TextField apptIDText;
    public TextField conIDText;
    public TextField cusIDText;
    public TextField titleText;
    public TextField descriptText;
    public TextField locText;
    public DatePicker startDateSel;
    public DatePicker endDateSel;
    public ComboBox startTimeCombo;
    public ComboBox endTimeCombo;
    public ComboBox conCombo;
    public ComboBox typeCombo;
    public ComboBox cusCombo;
    Nav nav = new Nav();
    AppointmentDAO appointmentDAO = new AppointmentDAOImp();

    /**Event handler to Appointment Menu.
     * Saves data and updates database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveAppt(ActionEvent actionEvent) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cusCombo.setItems(appointmentDAO.getCustomerNames());
        conCombo.setItems(appointmentDAO.getContactNames());
        typeCombo.setItems(Appointment.types);
        DateAndTimeHandler.appointmentTimes();
        startTimeCombo.setItems(Appointment.times);
        endTimeCombo.setItems(Appointment.times);
    }
}
