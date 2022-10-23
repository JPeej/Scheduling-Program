package Controller;

import DAO.UserDAO;
import DAO.UserDAOImp;
import Model.User;
import Utility.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**Controller for Login view. */
public class LoginController implements Initializable {

    @FXML private Label header;
    @FXML private Button submit;
    @FXML private Label incorrectLoginText;
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
        return userDAO.get(userNameLogin, passwordLogin);
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
            ResourceBundle rb = ResourceBundle.getBundle("Utility/MessageBundle", Locale.getDefault());
            incorrectLoginText.setText(rb.getString("incorrectLoginText"));
        }
        //TODO export login meta data
    }

    /**Sets appropriate language to the Login menu.
     * Uses key:value pairs in MessageBundles.
     * If user's language is French, then call frenchRelocate.
     * @param resourceBundle created in initialize method, detects MessageBundles and user location / language. */
    public void setLanguage(ResourceBundle resourceBundle){
        header.setText(resourceBundle.getString("header"));
        userNameLogin.setText(resourceBundle.getString("userNameLogin"));
        passwordLogin.setText(resourceBundle.getString("passwordLogin"));
        submit.setText(resourceBundle.getString("submit"));
        if (Locale.getDefault() == Locale.FRENCH) {
            frenchRelocate();
        }
    }

    /**Changes label location to fit French translations. */
    public void frenchRelocate() {
        header.setLayoutX(87);
        incorrectLoginText.setLayoutX(5);
    }

    /**Initial method called upon screen load.
     * Detects MessageBundles and user location / language.
     * Sets appropriate language. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResourceBundle rb = ResourceBundle.getBundle("Utility/MessageBundle", Locale.getDefault());
        setLanguage(rb);
    }
}
