package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMenuController implements Initializable {

    Nav nav = new Nav();

    @FXML
    public void onActionCustomersMenu(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    @FXML
    public void onActionApptMenu(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    @FXML
    public void onActionReportsMenu(ActionEvent actionEvent) throws IOException {
        nav.toReportsMenu(actionEvent);
    }

    @FXML
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.addCustomerLoc, Nav.addCustomerTitle);
    }

    @FXML
    public void onActionModifyCustomer(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.modifyCustomerLoc, Nav.modifyCustomerTitle);
    }

    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
