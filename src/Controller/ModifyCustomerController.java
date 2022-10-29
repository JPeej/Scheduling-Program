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
    CustomerDAO customerDAO = new CustomerDAOImp();

    /**Event handler to Customer Menu.
     * Saves data and updates database.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveCustomer(ActionEvent actionEvent) {
        try {
            int customerID = Integer.parseInt(idText.getText());
            int divID = customerDAO.getDivId(divCombo.getValue());
            String name = nameText.getText();
            String address = addressText.getText();
            String zip = zipText.getText();
            String phone = phoneText.getText();
            String lastUpdateBy = JDBC.user;
            Timestamp lastUpdate = DateTimeConverter.dateTimeToDB(ZonedDateTime.now().toString());
            if (checkBlanks(name, address, zip, phone, divID)) {
                Customer modifiedCustomer = new Customer(customerID, divID, name, address, zip, phone, lastUpdateBy,
                        lastUpdate);
                int rowsAffected = customerDAO.update(modifiedCustomer);
                if (rowsAffected > 0) {
                    nav.toCustomersMenu(actionEvent);
                    MyAlerts.alertInfo("Customer modified.");
                }
            } else MyAlerts.alertError("Please fill all fields and choices.");
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed.\nPlease restart program. " +
                    "Report to IT if problem continues.");
        } catch (SQLException | NullPointerException e) {
            MyAlerts.alertError("Please select/enter a value for every field.");
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

    public boolean checkBlanks(String name, String address, String zip, String phone, int div) {
        if(name.isBlank() | address.isBlank() | zip.isBlank() | phone.isBlank() | String.valueOf(div).isBlank()){
            return false;
        } else return true;
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
