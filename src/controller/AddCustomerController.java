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

/**Controller for Add Customer menu. */
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
    public void onActionSaveCustomer(ActionEvent actionEvent){
        try {
            int division = customerDAO.getDivId(divCombo.getValue());
            String name = nameText.getText();
            String address = addressText.getText();
            String zip = zipText.getText();
            String phone = phoneText.getText();
            String createBy = JDBC.user;
            String lastUpdateBy = JDBC.user;
            Timestamp createDateTime = Timestamp.valueOf(LocalDateTime.now());
            Timestamp lastUpdateDateTime = Timestamp.valueOf(LocalDateTime.now());
            if (checkBlanks(name, address, zip, phone, division)) {
                Customer newCustomer = new Customer(division, name, address, zip, phone, createDateTime, createBy,
                        lastUpdateDateTime, lastUpdateBy);
                int rowsAffected = customerDAO.insert(newCustomer);
                if (rowsAffected > 0) {
                    nav.toCustomersMenu(actionEvent);
                    MyAlerts.alertInfo("New customer saved.");
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
            default: divCombo.setPromptText("Select country first");
        }
    }

    /**Check if any fields were left blank by user.
     * @param name name textfield value
     * @param address addess textfield value
     * @param zip zip textfield value
     * @param phone phone textfield value
     * @param div div textfield value
     * @return boolean*/
    public boolean checkBlanks(String name, String address, String zip, String phone, int div) {
        return !(name.isBlank() | address.isBlank() | zip.isBlank() | phone.isBlank() | String.valueOf(div).isBlank());
    }

    /**Called upon screen load.
     * Loads country combo box with all countries.   */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       countryCombo.setItems(Locations.countries);
    }
}
