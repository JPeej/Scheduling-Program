package Controller;

import DAO.CustomerDAO;
import DAO.CustomerDAOImp;
import Model.Customer;
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
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**Controller for Customer Menu menu. */
public class CustomerMenuController implements Initializable {
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> idCol;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> addressCol;
    @FXML private TableColumn<Customer, String> zipCol;
    @FXML private TableColumn<Customer, String> divCol;
    @FXML private TableColumn<Customer, String> phoneCol;
    @FXML private TableColumn<Customer, Timestamp> createDateCol;
    @FXML private TableColumn<Customer, String> createByCol;
    @FXML private TableColumn<Customer, Timestamp> lastUpdateCol;
    @FXML private TableColumn<Customer, String> lastUpdateByCol;
    Stage stage;
    Nav nav = new Nav();
    CustomerDAO customerDAO = new CustomerDAOImp();

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

    /**Event handler to Add Customer Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionAdd(ActionEvent actionEvent) {
        try {
            nav.navigate(actionEvent, Nav.addCustomerLoc, Nav.addCustomerTitle);
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT if issue persists.");
        }
    }

    /**Event handler to Modify Customer Menu.
     * Passes data from selected customer on Customer menu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionModify(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Nav.modifyCustomerLoc));
            loader.load();
            ModifyCustomerController modifyCustomerController = loader.getController();
            modifyCustomerController.sendCustomer(customerTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle(Nav.modifyCustomerTitle);
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT");
        } catch (NullPointerException e) {
            MyAlerts.alertError("Please select a customer to modify.");
        }
    }

    /**Deletes the selected customer.
     * Rejects deletion if customer has appointments.
     * Reloads tableview.
     * Prompts user with errors.*/
    public void onActionDelete() {
        try {
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            String name = customer.getName();
            if (!customerDAO.appointmentExists(customer.getCustomerID())) {
                customerDAO.delete(customer);
                MyAlerts.alertInfo("Customer " + name + " deleted.");
                loadTable();
            } else MyAlerts.alertError("Customer has appointments scheduled.");
        } catch (SQLException e) {
            MyAlerts.alertError("Customer failed to delete.");
        } catch (NullPointerException e) {
            MyAlerts.alertError("Please select a customer to delete.");
        }
    }

    /**Load tableview with Customer data.*/
    public void loadTable() {
        try {
            ObservableList<Customer> customers = customerDAO.getAll();
            customerTable.setItems(customers);
            idCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            zipCol.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDateStamp"));
            createByCol.setCellValueFactory(new PropertyValueFactory<>("createBy"));
            lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdateStamp"));
            lastUpdateByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
            divCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        } catch (SQLException e) {
            MyAlerts.alertError("Customer data failed to load, contact IT. ");
        }
    }

    /**Called upon screen load.
     * Loads TableView with all Customer data.   */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }
}
