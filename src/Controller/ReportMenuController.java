package Controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImp;
import Model.Appointment;
import Utility.MyAlerts;
import Utility.Nav;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**Controller for ReportMenu. */
public class ReportMenuController implements Initializable {

    @FXML private TableView<Appointment> conTable;
    @FXML private TableColumn<Appointment, Integer> conAppID;
    @FXML private TableColumn<Appointment, Integer> conCustID;
    @FXML private TableColumn<Appointment, String> conTitle;
    @FXML private TableColumn<Appointment, String> conDescript;
    @FXML private TableColumn<Appointment, String> conType;
    @FXML private TableColumn<Appointment, String> conContact;
    @FXML private TableColumn<Appointment, String> conCust;
    @FXML private TableColumn<Appointment, Timestamp> conStart;
    @FXML private TableColumn<Appointment, Timestamp> conEnd;
    @FXML private ComboBox<String> contactCombo;
    @FXML private TableView<Appointment> expiredTable;
    @FXML private TableColumn<Appointment, Integer> expID;
    @FXML private TableColumn<Appointment, String> expCon;
    @FXML private TableColumn<Appointment, String> expTitle;
    @FXML private TableColumn<Appointment, Timestamp> expStart;
    @FXML private TableView<Appointment> customerTable;
    @FXML private TableColumn<Appointment, String> cusMonth;
    @FXML private TableColumn<Appointment, String> cusType;
    @FXML private TableColumn<Appointment, Integer> cusCount;
    Nav nav = new Nav();
    private ObservableList<Appointment> reportData = null;
    AppointmentDAO appointmentDAO = new AppointmentDAOImp();

    /**Event handler to Customer Menu.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCustomersMenu(ActionEvent actionEvent) {
        try {
            nav.toCustomersMenu(actionEvent);
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT if issue persists.");
        }
    }

    /**Event handler to Appointment Menu.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionApptMenu(ActionEvent actionEvent) {
        try {
            nav.toAppointmentsMenu(actionEvent);
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT if issue persists.");
        }
    }

    /**Event handler to Reports Menu.
     * See Nav.toReportsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionReportsMenu(ActionEvent actionEvent) {
        try {
            nav.toReportsMenu(actionEvent);
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT if issue persists.");
        }
    }

    /**Event handler to exit program.
     * Closes program and connection to database.*/
    @FXML
    public void onActionExit() {
        System.exit(0);
    }

    public void filterByContact(ObservableList<Appointment> appointments, String contact) {
        List<Appointment> filterAppointments =
                appointments.stream().filter(
                        appointment -> appointment.getContact().contentEquals(contact))
                        .collect(Collectors.toList());
        ObservableList<Appointment> contactData = FXCollections.observableArrayList(filterAppointments);
        loadContactTable(contactData);
    }

    public ObservableList<Appointment> filterExpiredData(ObservableList<Appointment> appointments) {
        List<Appointment> filterAppointments =
                appointments.stream().filter(
                        appointment -> appointment.getStartStamp().toLocalDateTime().isBefore(LocalDateTime.now()))
                        .collect(Collectors.toList());
        return FXCollections.observableArrayList(filterAppointments);
    }

    public void loadExpiredTable() {
        expiredTable.setItems(filterExpiredData(reportData));
        expID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        expCon.setCellValueFactory(new PropertyValueFactory<>("contact"));
        expTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        expStart.setCellValueFactory(new PropertyValueFactory<>("startStamp"));
    }

    public void loadCustomerTable() {
        try {
            customerTable.setItems(appointmentDAO.getReport());
            cusMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
            cusType.setCellValueFactory(new PropertyValueFactory<>("type"));
            cusCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        } catch (SQLException e) {
            MyAlerts.alertError("Customer Month / Type report failed to load.");
        }
    }

    /**Load tableview with Appointment data provided.
     * @param appointments appointments, filtered or not, to populate the tableview*/
    public void loadContactTable(ObservableList<Appointment> appointments) {
        conTable.setItems(appointments);
        conAppID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        conTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        conDescript.setCellValueFactory(new PropertyValueFactory<>("description"));
        conType.setCellValueFactory(new PropertyValueFactory<>("type"));
        conCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        conContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        conCust.setCellValueFactory(new PropertyValueFactory<>("customer"));
        conStart.setCellValueFactory(new PropertyValueFactory<>("startStamp"));
        conEnd.setCellValueFactory(new PropertyValueFactory<>("endStamp"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            reportData = FXCollections.observableArrayList(appointmentDAO.getAll());
            loadContactTable(reportData);
            loadExpiredTable();
            loadCustomerTable();
            contactCombo.setItems(appointmentDAO.getContactNames());
            contactCombo.setOnAction(e -> filterByContact(reportData, contactCombo.getValue()));
        } catch (SQLException e) {
            MyAlerts.alertError("Report page failed to load data.");
    }


}
}
