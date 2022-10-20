package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddCustomerController {

    Nav nav = new Nav();

    @FXML
    public void onActionSaveCustomer(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }
}
