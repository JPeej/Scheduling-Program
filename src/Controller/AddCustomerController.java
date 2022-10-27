package Controller;

import DAO.CustomerDAO;
import DAO.CustomerDAOImp;
import DAO.JDBC;
import Model.Customer;
import Utility.DateTimeConverter;
import Utility.Locations;
import Utility.MyAlerts;
import Utility.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML private ComboBox<String> countryCombo;
    @FXML private ComboBox<String> divCombo;
    @FXML private TextField nameText;
    @FXML private TextField zipText;
    @FXML private TextField phoneText;
    @FXML private TextField addressText;
    Nav nav = new Nav();
    CustomerDAO customerDAO = new CustomerDAOImp();

    /**Event handler to Customer Menu.
     * Saves data and updates database.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveCustomer(ActionEvent actionEvent) throws IOException, SQLException {
        int division = customerDAO.getDivId(divCombo.getValue());
        String country = countryCombo.getValue();
        String name = nameText.getText();
        String address = addressText.getText();
        String zip = zipText.getText();
        String phone = phoneText.getText();
        String createBy = JDBC.user;
        String lastUpdateBy = JDBC.user;
        Timestamp createDateTime = DateTimeConverter.dateTimeToDB(ZonedDateTime.now().toString());
        Timestamp lastUpdateDateTime = DateTimeConverter.dateTimeToDB(ZonedDateTime.now().toString());
        Customer newCustomer = new Customer(division, name, address, zip, phone, createDateTime, createBy,
                lastUpdateDateTime, lastUpdateBy);
        int rowsAffected = customerDAO.insert(newCustomer);

        if (rowsAffected > 0) {
            nav.toCustomersMenu(actionEvent);
            MyAlerts.alertInfo("New customer saved.");
        } else {
            MyAlerts.alertError("New customer did not save.\nCheck values and try again.");
        }

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
    public void onActionCountryCombo(ActionEvent actionEvent) {
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
            default: divCombo.setPromptText("Select country first");
        }
    }

    public boolean validateValues(int divID, String country, String name, String address, String zip, String phone) {
        ArrayList<Boolean> validationBooleans = new ArrayList<>();
        validationBooleans.add(validateDivID(divID));
        validationBooleans.add(validateCountry(country));
        validationBooleans.add(validateName(name));
        validationBooleans.add(validateAddress(address));
        validationBooleans.add(validateZip(zip));
        validationBooleans.add(validatePhone(phone));
        return !validationBooleans.contains(false);
    }

    public boolean validateDivID(int divID) {

        return true;
    }

    public boolean validateCountry(String country) {
        return true;
    }

    public boolean validateName(String name) {
        return true;
    }
    public boolean validateAddress(String address) {
        return true;
    }

    public boolean validateZip(String zip) {
        return true;
    }

    public boolean validatePhone(String phone) {

    }

    /**Called upon screen load.
     * Loads country combo box with all countries.   */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       countryCombo.setItems(Locations.countries);
    }
}
