package Controller;

import Model.Customer;
import Utility.Locations;
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

    @FXML private ComboBox<String> countryCombo;
    @FXML private ComboBox<String> divCombo;
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

    /**Switch method for division combo.
     * Reacts to country selection. */
    public void onActionCountryCombo() {
        String countrySelection = countryCombo.getValue();
        switch(countrySelection) {
            case "U.S":
                divCombo.setItems(Locations.usDivList);
                break;
            case "UK":
                divCombo.setItems(Locations.ukDivList);
                break;
            case "Canada":
                divCombo.setItems(Locations.canadaList);
                break;
            default:
                divCombo.setPromptText("Select country first");
        }
    }

    /**Populates Modify Customer menu with customer data.
     * @param customer selected in Customer menu*/
    public void sendCustomer(Customer customer) {
        idText.setText(String.valueOf(customer.getCustomerID()));
        nameText.setText(customer.getName());
        phoneText.setText(customer.getPhoneNumber());
        addressText.setText(customer.getAddress());
        zipText.setText(customer.getZipcode());
        countryCombo.getSelectionModel().select(customer.getCountry());
        divCombo.getSelectionModel().select(customer.getDivision());
        onActionCountryCombo();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {countryCombo.setItems(Locations.countries);}
}
