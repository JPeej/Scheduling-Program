package Controller;

import DAO.CustomerDAO;
import DAO.CustomerDAOImp;
import DAO.JDBC;
import Model.Customer;
import Utility.DateAndTimeHandler;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**Controller for Modify Customer menu. */
public class ModifyCustomerController extends AddCustomerController implements Initializable {

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
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            if (checkBlanks(name, address, zip, phone, divID)) {
                Customer modifiedCustomer = new Customer(customerID, divID, name, address, zip, phone, lastUpdateBy,
                        lastUpdate);
                int rowsAffected = customerDAO.update(modifiedCustomer);
                if (rowsAffected > 0) {
                    nav.toCustomersMenu(actionEvent);
                    MyAlerts.alertInfo("Customer updated.");
                }
            } else MyAlerts.alertError("Please fill all fields and choices.");
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed.\nPlease restart program. " +
                    "Report to IT if problem continues.");
        } catch (SQLException | NullPointerException e) {
            MyAlerts.alertError("Please select/enter a value for every field.");
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

    /**Initial method called upon screen load.
     * Populates country combo with countries in database. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.setItems(Locations.countries);
    }

}
