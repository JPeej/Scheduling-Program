package Controller;

import DAO.JDBC;
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
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML private ComboBox<String> countryCombo;
    @FXML private ComboBox<String> divCombo;
    @FXML private TextField nameText;
    @FXML private TextField zipText;
    @FXML private TextField phoneText;
    @FXML private TextField addressText;
    Nav nav = new Nav();

    /**Event handler to Customer Menu.
     * Saves data and updates database.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveCustomer(ActionEvent actionEvent) throws IOException {
        String name = nameText.getText();
        String address = addressText.getText();
        String zip = zipText.getText();
        String phone = phoneText.getText();
        String country = countryCombo.getValue();
        String division = divCombo.getValue();
        String createBy = JDBC.user;
        String lastUpdateBy = JDBC.user;
        ZonedDateTime createDateTime = ZonedDateTime.now();
        ZonedDateTime lastUpdateDateTime = ZonedDateTime.now();

        Customer newCustomer = new Customer(name, address, zip, phone, createDateTime, createBy, lastUpdateDateTime, lastUpdateBy, country, division);
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
        String countrySelection = (String)countryCombo.getValue();
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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       countryCombo.setItems(Locations.countries);
    }
}
