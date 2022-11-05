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
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**Controller for ReportMenu. */
public class ReportMenuController implements Initializable {

    @FXML private TableView conTable;
    @FXML private TableColumn conAppID;
    @FXML private TableColumn conCustID;
    @FXML private TableColumn conTitle;
    @FXML private TableColumn conDescript;
    @FXML private TableColumn conType;
    @FXML private TableColumn conContact;
    @FXML private TableColumn conCust;
    @FXML private TableColumn conStart;
    @FXML private TableColumn conEnd;
    @FXML private ComboBox contactCombo;
    @FXML private TableView expiredTable;
    @FXML private TableColumn expID;
    @FXML private TableColumn expCon;
    @FXML private TableColumn expTitle;
    @FXML private TableColumn expStart;
    @FXML private TableView customerTable;
    @FXML private TableColumn cusMonth;
    @FXML private TableColumn cusType;
    @FXML private TableColumn cusCount;
    Nav nav = new Nav();
    private ObservableList<Appointment> reportData = null;
    private ObservableList<Appointment> expiredData = null;
    private ObservableList<Appointment> contactData = null;
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
        contactData = FXCollections.observableArrayList(filterAppointments);
        loadContactTable(contactData);
    }


    public ObservableList<Appointment> filterExpiredData(ObservableList<Appointment> appointments) {
        List<Appointment> filterAppointments =
                appointments.stream().filter(
                        appointment -> appointment.getStartStamp().toLocalDateTime().isBefore(LocalDateTime.now()))
                        .collect(Collectors.toList());
        expiredData = FXCollections.observableArrayList(filterAppointments);
        return expiredData;
    }


    public void loadExpiredTable() {
        expiredTable.setItems(filterExpiredData(reportData));
        expID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        expCon.setCellValueFactory(new PropertyValueFactory<>("contact"));
        expTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        expStart.setCellValueFactory(new PropertyValueFactory<>("startStamp"));
    }

    public void loadCustomerTable() throws SQLException {
        customerTable.setItems(appointmentDAO.getReport());
        cusMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        cusType.setCellValueFactory(new PropertyValueFactory<>("type"));
        cusCount.setCellValueFactory(new PropertyValueFactory<>("count"));
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
            contactCombo.setOnAction(e -> filterByContact(reportData, (String) contactCombo.getValue()));
        } catch (SQLException e) {
            MyAlerts.alertError("Report page failed to load data.");
    }


}
}
