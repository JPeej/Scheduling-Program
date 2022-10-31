package Controller;

import DAO.AppointmentDAO;
import DAO.AppointmentDAOImp;
import Model.Appointment;
import Utility.MyAlerts;
import Utility.Nav;
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
import java.util.ResourceBundle;

/**Controller for Appointment menu. */
public class ApptMenuController implements Initializable {

    @FXML private TableView appointTable;
    @FXML private TableColumn idCol;
    @FXML private TableColumn titleCol;
    @FXML private TableColumn descriptCol;
    @FXML private TableColumn locCol;
    @FXML private TableColumn contactCol;
    @FXML private TableColumn typeCol;
    @FXML private TableColumn startCol;
    @FXML private TableColumn endCol;
    @FXML private TableColumn cusIDCol;
    @FXML private TableColumn userIDCol;
    Nav nav = new Nav();
    Stage stage;

    /**Event handler to Customer Menu.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCustomerMenu(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    /**Event handler to Appointment Menu.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionApptMenu(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    /**Event handler to Reports Menu.
     * See Nav.toReportsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionReportsMenu(ActionEvent actionEvent) throws IOException {
        nav.toReportsMenu(actionEvent);
    }

    /**Event handler to Add Appointment Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionAddAppt(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.addAppointmentLoc, Nav.addAppointmentTitle);
    }

    /**Event handler to Modify Appointment Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionModifyAppt(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Nav.modifyAppointmentLoc));
            loader.load();
            ModifyApptController modifyApptController = loader.getController();
            modifyApptController.sendAppt((Appointment) appointTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle(Nav.modifyAppointmentTitle);
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT");
        } catch (NullPointerException e) {
            MyAlerts.alertError("Please select an appointment to modify first.");
        }
    }

    /**Event handler to exit program.
     * Closes program and connection to database.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**Initial method called upon screen load.
     * Populates table view with appointment data. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDAO appointmentDAO = new AppointmentDAOImp();
        try {
            ObservableList<Appointment> appointments = appointmentDAO.getAll();
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
        } catch (SQLException e) {
            MyAlerts.alertError("Appointment data failed to load, contact IT. ");
        }
    }
}
