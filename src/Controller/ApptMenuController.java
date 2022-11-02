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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**Controller for Appointment menu. */
public class ApptMenuController implements Initializable {

    @FXML private TableView<Appointment> appointTable;
    @FXML private TableColumn<Appointment, Integer> idCol;
    @FXML private TableColumn<Appointment, String> titleCol;
    @FXML private TableColumn<Appointment, String> descriptCol;
    @FXML private TableColumn<Appointment, String> locCol;
    @FXML private TableColumn<Appointment, String> contactCol;
    @FXML private TableColumn<Appointment, String> typeCol;
    @FXML private TableColumn<Appointment, Timestamp> startCol;
    @FXML private TableColumn<Appointment, Timestamp> endCol;
    @FXML private TableColumn<Appointment, Integer> cusIDCol;
    @FXML private TableColumn<Appointment, Integer> userIDCol;
    AppointmentDAO appointmentDAO = new AppointmentDAOImp();
    ObservableList<Appointment> allAppointments;
    Nav nav = new Nav();
    Stage stage;

    /**Event handler to Customer Menu.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCustomerMenu(ActionEvent actionEvent) {
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
            nav.toAppointmentsMenu(actionEvent);
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

    /**Event handler to Add Appointment Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionAdd(ActionEvent actionEvent) {
        try {
            nav.navigate(actionEvent, Nav.addAppointmentLoc, Nav.addAppointmentTitle);
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT if issue persists.");
        }
    }

    /**Event handler to Modify Appointment Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionModify(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Nav.modifyAppointmentLoc));
            loader.load();
            ModifyApptController modifyApptController = loader.getController();
            modifyApptController.sendAppt(appointTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle(Nav.modifyAppointmentTitle);
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT");
        } catch (NullPointerException e) {
            MyAlerts.alertError("Please select an appointment to modify .");
        }
    }

    /**Deletes the selected appointment.
     * Prompts user on success with ID and type.
     * Reloads tableview.
     * Prompts user with errors.*/
    public void onActionDeleteAppt() {
        try {
            Appointment appointment = appointTable.getSelectionModel().getSelectedItem();
            int apptID = appointment.getAppointmentID();
            String apptType = appointment.getType();
            appointmentDAO.delete(appointment);
            MyAlerts.alertInfo("Appointment deleted.\nAppointment ID: " + apptID +"\nAppointment Type: "
                    + apptType);
            allAppointments = appointmentDAO.getAll();
            loadTable(allAppointments);
        } catch (SQLException e) {
            MyAlerts.alertError("Appointment deletion failed. ");
        } catch (NullPointerException e) {
            MyAlerts.alertError("Please select an appointment to delete.");
        }
    }

    /**Loads all appointments to the tableview. */
    public void onActionAll() {
        loadTable(allAppointments);
    }

    /**Filters appointments tableview to show only those with the next month.
     * One of my lambda expressions. Month class allowed me to make an easy to read stream with lambda expression.
     * The other option would have been to either implement something similar to the onActionByWeek method or,
     * to use many if/else statements. */
    public void onActionByMonth() {
        Month thisMonth = LocalDateTime.now().getMonth();
        List<Appointment> filteredAppts = allAppointments.stream()
                .filter(appointment -> appointment.getStartStamp().toLocalDateTime().getMonth().equals(thisMonth))
                .collect(Collectors.toList());
        ObservableList<Appointment> thisMonthsAppts = FXCollections.observableArrayList(filteredAppts);
        loadTable(thisMonthsAppts);
    }

    /**Filters appointments tableview to show only those within the next week.*/
    public void onActionByWeek() {
        LocalDateTime weekAway = LocalDateTime.now().plusDays(7);
        List<Appointment> filteredAppts = allAppointments.stream()
                .filter(appointment -> ((appointment.getStartStamp().toLocalDateTime().isEqual(LocalDateTime.now())
                        | appointment.getStartStamp().toLocalDateTime().isAfter(LocalDateTime.now())))
                        & appointment.getStartStamp().toLocalDateTime().isBefore(weekAway))
                .collect(Collectors.toList());
        ObservableList<Appointment> thisWeeksAppts = FXCollections.observableArrayList(filteredAppts);
        loadTable(thisWeeksAppts);
    }

    /**Load tableview with Appointment data provided.
     * @param appointments appointments, filtered or not, to populate the tableview*/
    public void loadTable(ObservableList<Appointment> appointments) {
        appointTable.setItems(appointments);
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startStamp"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endStamp"));
        cusIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /**Initial method called upon screen load.
     * Populates table view with appointment data. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allAppointments = appointmentDAO.getAll();
            loadTable(allAppointments);
        } catch (SQLException e) {
            MyAlerts.alertError("Appointment data failed to load. ");
        }
    }

}
