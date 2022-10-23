package Controller;

import Utility.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {
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
    Nav nav = new Nav();

    /**Event handler to Customer Menu.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCustomersMenu(ActionEvent actionEvent) throws IOException {
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

    /**Event handler to Add Customer Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.addCustomerLoc, Nav.addCustomerTitle);
    }

    /**Event handler to Modify Customer Menu.
     * See Nav.navigate.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionModifyCustomer(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.modifyCustomerLoc, Nav.modifyCustomerTitle);
    }

    /**Event handler to exit program.
     * Closes program and connection to database.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
