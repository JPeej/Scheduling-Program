package Controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImp;
import Model.Appointment;
import Utility.MyAlerts;
import Utility.Nav;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ResourceBundle;

/**Controller for ReportMenu. */
public class ReportMenuController implements Initializable {

    @FXML private ComboBox<String> comboBox;
    @FXML private ToggleGroup reportRadio;
    @FXML private TableView<Appointment> reportTableView;
    @FXML private TableColumn<Appointment, Integer> apptID;
    @FXML private TableColumn<Appointment, String> apptTitle;
    @FXML private TableColumn<Appointment, String> apptDescript;
    @FXML private TableColumn<Appointment, String> apptLoc;
    @FXML private TableColumn<Appointment, String> apptType;
    @FXML private TableColumn<Appointment, String> apptContact;
    @FXML private TableColumn<Appointment, String> apptCustomer;
    @FXML private TableColumn<Appointment, Timestamp> apptStart;
    @FXML private TableColumn<Appointment, Timestamp> apptEnd;
    private ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December");
    private ObservableList<Appointment> reportData;
    private String comboValue;
    AppointmentDAO appointmentDAO = new AppointmentDAOImp();
    Nav nav = new Nav();

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

    public void onActionScheduleReport(ActionEvent actionEvent) {

    }

    public void onActionTypeReport(ActionEvent actionEvent) {

    }

    public void onActionMonthReport(ActionEvent actionEvent) {
        comboBox.setItems(months);
        comboBox.setPromptText("Select month");
        comboBox.valueProperty().addListener((comboSelection, oldMonth, newMonth) -> comboValue = newMonth);

        System.out.println(comboValue);
        ObservableList<Appointment> filteredAppoints;
//        reportData.stream()
//                .filter(appointment -> appointment)

    }

    public void onActionExpired(ActionEvent actionEvent) {
    }

    public String onActionCombo() {
        return comboBox.getSelectionModel().getSelectedItem();
    }

    /**Load tableview with Appointment data provided.
     * @param appointments appointments, filtered or not, to populate the tableview*/
    public void loadTable(ObservableList<Appointment> appointments) {
        reportTableView.setItems(appointments);
        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescript.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptLoc.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        apptCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("startStamp"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("endStamp"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            reportData = appointmentDAO.getAll();
        } catch (SQLException e) {
            MyAlerts.alertError("Data retrieval failed.");
        }
    }


}
