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
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**Controller for ReportMenu. */
public class ReportMenuController implements Initializable {

    @FXML private RadioButton contactSchedule;
    @FXML private RadioButton typeReport;
    @FXML private RadioButton monthReport;
    @FXML private RadioButton expiredAppoints;
    @FXML private ComboBox<String> comboBox;
    @FXML private TableView<Appointment> reportTableView;
    @FXML private TableColumn<Appointment, Integer> apptID;
    @FXML private TableColumn<Appointment, String> apptTitle;
    @FXML private TableColumn<Appointment, String> apptDescript;
    @FXML private TableColumn<Appointment, String> custID;
    @FXML private TableColumn<Appointment, String> apptType;
    @FXML private TableColumn<Appointment, String> apptContact;
    @FXML private TableColumn<Appointment, String> apptCustomer;
    @FXML private TableColumn<Appointment, Timestamp> apptStart;
    @FXML private TableColumn<Appointment, Timestamp> apptEnd;
    Nav nav = new Nav();
    private ObservableList<Appointment> reportData = null;
    private ObservableList<Appointment> filteredData = null;
    AppointmentDAO appointmentDAO = new AppointmentDAOImp();
    private final ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December");

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

    public void setCombo(String group)  {
        try {
            reportTableView.getItems().clear();
            switch (group) {
                case "Contact":
                    comboBox.setItems(appointmentDAO.getContactNames());
                    break;
                case "Month":
                    comboBox.setItems(months);
                    break;
                case "Type":
                    comboBox.setItems(Appointment.types);
                    break;
            }
            comboBox.setPromptText("Select a: " + group);
        } catch (SQLException e) {
            MyAlerts.alertError("Dropdown menu could not load data.");
        }
    }

    public void onActionCombo() {
        try {
            String comboValue = comboBox.getValue();
            if (contactSchedule.isSelected()) filterContactSchedule(comboValue);
            else if (monthReport.isSelected()) filterCustomerMonth(comboValue);
            else if (typeReport.isSelected()) filterCustomerType(comboValue);
        } catch (NullPointerException ignored) {}
    }

    public void filterContactSchedule(String name) {
        List<Appointment> list =
                reportData.stream().filter
                (appointment -> appointment.getContact().contentEquals(name))
                .collect(Collectors.toList());
        ObservableList<Appointment> contactSchedule = FXCollections.observableArrayList(list);
        loadTable(contactSchedule);
    }

    public void filterCustomerMonth(String month) {
        List<Appointment> list =
                reportData.stream().filter
                        (appointment -> appointment.getStartStamp().toLocalDateTime().getMonth()
                                .equals(Month.valueOf(month.toUpperCase())))
                .collect(Collectors.toList());
        ObservableList<Appointment> monthSchedule = FXCollections.observableArrayList(list);
        loadTable(monthSchedule);
    }

    public void filterCustomerType(String type) {
        List<Appointment> list =
                reportData.stream().filter
                        (appointment -> appointment.getType().contentEquals(type))
                        .collect(Collectors.toList());
        ObservableList<Appointment> typeSchedule = FXCollections.observableArrayList(list);
        loadTable(typeSchedule);
    }

    public void filterExpired() {
        reportTableView.getItems().clear();
        comboBox.getItems().clear();
        comboBox.setPromptText("");
        List<Appointment> list =
                reportData.stream().filter
                        (appointment -> appointment.getStartStamp().toLocalDateTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
        ObservableList<Appointment> expiredSchedule = FXCollections.observableArrayList(list);
        loadTable(expiredSchedule);
    }


    /**Load tableview with Appointment data provided.
     * @param appointments appointments, filtered or not, to populate the tableview*/
    public void loadTable(ObservableList<Appointment> appointments) {
        reportTableView.setItems(appointments);
        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescript.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        custID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        apptCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("startStamp"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("endStamp"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTable(appointmentDAO.getAll());
            contactSchedule.setOnAction(e -> setCombo("Contact"));
            typeReport.setOnAction(e -> setCombo("Type"));
            monthReport.setOnAction(e -> setCombo("Month"));
            expiredAppoints.setOnAction(e -> filterExpired());
            comboBox.setItems(appointmentDAO.getContactNames());
            comboBox.setPromptText("Select a contact");
        } catch (SQLException e) {
            MyAlerts.alertError("Data retrieval failed.");
        }
        reportData = FXCollections.observableArrayList(reportTableView.getItems());
    }
}
