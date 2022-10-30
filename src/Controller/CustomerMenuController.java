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
import java.util.ResourceBundle;

/**Controller for Customer Menu menu. */
public class CustomerMenuController implements Initializable {
    @FXML private TableView customerTable;
    @FXML private TableColumn idCol;
    @FXML private TableColumn nameCol;
    @FXML private TableColumn addressCol;
    @FXML private TableColumn zipCol;
    @FXML private TableColumn divCol;
    @FXML private TableColumn phoneCol;
    @FXML private TableColumn createDateCol;
    @FXML private TableColumn createByCol;
    @FXML private TableColumn lastUpdateCol;
    @FXML private TableColumn lastUpdateByCol;
    Stage stage;
    Nav nav = new Nav();

    /**Event handler to Customer Menu.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionCustomersMenu(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    /**Event handler to Appointment Menu.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionApptMenu(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    /**Event handler to Reports Menu.
     * See Nav.toReportsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionReportsMenu(ActionEvent actionEvent) throws IOException {
        nav.toReportsMenu(actionEvent);
    }

    /**Event handler to Add Customer Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.addCustomerLoc, Nav.addCustomerTitle);
    }

    /**Event handler to Modify Customer Menu.
     * Passes data from selected customer on Customer menu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionModifyCustomer(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Nav.modifyCustomerLoc));
            loader.load();
            ModifyCustomerController modifyCustomerController = loader.getController();
            modifyCustomerController.sendCustomer((Customer) customerTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle(Nav.modifyCustomerTitle);
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed. Contact IT");
        } catch (NullPointerException e) {
            MyAlerts.alertError("Please select a customer to modify first.");
        }
    }

    /**Event handler to exit program.
     * Closes program and connection to database.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void test() {}

    /**Called upon screen load.
     * Loads TableView with all Customer data.   */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerDAO customerDAO = new CustomerDAOImp();
        try {
            ObservableList<Customer> customers = customerDAO.getAll();
            customerTable.setItems(customers);
            idCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            zipCol.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            createDateCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
            createByCol.setCellValueFactory(new PropertyValueFactory<>("createBy"));
            lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
            lastUpdateByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
            divCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        } catch (SQLException e) {
            MyAlerts.alertError("Customer data failed to load, contact IT. ");
        }
    }
}
