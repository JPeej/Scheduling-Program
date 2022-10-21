package Controller;

import DAO.UserDAO;
import DAO.UserDAOImp;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController{

    @FXML private TextField userNameLogin;
    @FXML private PasswordField passwordLogin;
    Nav nav = new Nav();

    public String getUserNameLogin() {
        return userNameLogin.getText();
    }

    public String getPasswordLogin() {
        return passwordLogin.getText();
    }

    /**Event handler to for user authentication and navigation to Customer Menu.
     * Challenges credentials provided to those on database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSubmitLogin(ActionEvent actionEvent) throws IOException, SQLException {
        String userNameLogin = getUserNameLogin();
        String passwordLogin = getPasswordLogin();
        UserDAO userDAO = new UserDAOImp();

        User loginUser = userDAO.get(userNameLogin, passwordLogin);

        if (loginUser != null) {
            nav.navigate(actionEvent, Nav.customerMenuLoc, Nav.customerMenuTitle);
        } else {
            System.out.println("NOPE");
        }

    }
}
