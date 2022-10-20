package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Nav nav = new Nav();
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onActionSubmitLogin(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.customerMenuLoc, Nav.customerMenuTitle);
    }
}