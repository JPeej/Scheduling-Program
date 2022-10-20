package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController{

    Nav nav = new Nav();

    /**Event handler to for user authentication and navigation to Customer Menu.
     * Challenges credentials provided to those on database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSubmitLogin(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.customerMenuLoc, Nav.customerMenuTitle);
    }
}
