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

/**Controller for Login view. */
public class LoginController {

    @FXML private TextField userNameLogin;
    @FXML private PasswordField passwordLogin;
    Nav nav = new Nav();

    /**Get user name entered in login menu.
     * Parse String from @FXML Textfield.
     * @return user name String. */
    public String getUserNameLogin() {
        return userNameLogin.getText();
    }

    /**Get password entered in login menu.
     * Parse String from @FXML PasswordField.
     * @return password String. */
    public String getPasswordLogin() {
        return passwordLogin.getText();
    }

    /**Challenges provided login credentials against those on database.
     * See UserDAOImp class.
     * See getUserNameLogin and getPasswordLogin methods here.
     * @return loginUser User if correct credentials provided and null if not. */
    public User userAuthentication() throws SQLException {
        UserDAO userDAO = new UserDAOImp();
        String userNameLogin = getUserNameLogin();
        String passwordLogin = getPasswordLogin();
        User loginUser = userDAO.get(userNameLogin, passwordLogin);
        return loginUser;
    }

    /**Event handler for login submit button.
     * Challenges credentials provided to those on database.
     * Navigates to Customer Menu upon authentication.
     * See userAuthentication.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSubmitLogin(ActionEvent actionEvent) throws IOException, SQLException {
        User loginUser = userAuthentication();
        if (loginUser != null) {
            nav.navigate(actionEvent, Nav.customerMenuLoc, Nav.customerMenuTitle);
        } else {
            //TODO alert user to failed login
        }
        //TODO export login meta data
    }
}
