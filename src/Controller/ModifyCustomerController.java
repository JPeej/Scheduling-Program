package Controller;

import Model.Customer;
import Utility.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**Controller for Modify Customer menu. */
public class ModifyCustomerController implements Initializable {

    @FXML private ComboBox countryCombo;
    @FXML private ComboBox divCombo;
    @FXML private TextField idText;
    @FXML private TextField zipText;
    @FXML private TextField phoneText;
    @FXML private TextField addressText;
    @FXML private TextField nameText;
    Nav nav = new Nav();

    /**Event handler to Customer Menu.
     * Saves data and updates database.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveCustomer(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    /**Event handler to Customer Menu.
     * Discards any changes, makes no changes to database.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    public void onActionCountryCombo(ActionEvent actionEvent) {
    }

    public void sendCustomer(Customer customer) {
        idText.setText(String.valueOf(customer.getCustomerID()));
        nameText.setText(customer.getName());
        phoneText.setText(customer.getPhoneNumber());
        addressText.setText(customer.getAddress());
        zipText.setText(customer.getZipcode());
        countryCombo.getSelectionModel().select(customer.getCountry());
        divCombo.getSelectionModel().select(customer.getDivision());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
