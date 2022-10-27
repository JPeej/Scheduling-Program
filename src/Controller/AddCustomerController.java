package Controller;

import DAO.CustomerDAO;
import DAO.CustomerDAOImp;
import DAO.JDBC;
import Model.Customer;
import Utility.DateTimeConverter;
import Utility.Locations;
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
        int division = customerDAO.getID(divCombo.getValue());
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
        customerDAO.insert(newCustomer);
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

    /**Called upon screen load.
     * Loads country combo box with all countries.   */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       countryCombo.setItems(Locations.countries);
    }
}
