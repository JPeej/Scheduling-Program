package Controller;

import DAO.JDBC;
import DAO.UserDAO;
import DAO.UserDAOImp;
import Model.User;
import Utility.MyAlerts;
import Utility.Nav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

/**Controller for Login menu. */
public class LoginController implements Initializable {

    @FXML private Label location;
    @FXML private Label header;
    @FXML private Button submit;
    @FXML private Label incorrectLoginText;
    @FXML private TextField userNameLogin;
    @FXML private PasswordField passwordLogin;
    Nav nav = new Nav();
    UserDAO userDAO = new UserDAOImp();

    /**Event handler for login submit button.
     * Challenges credentials provided to those on database.
     * Navigates to Customer Menu upon authentication.
     * See userAuthentication.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSubmitLogin(ActionEvent actionEvent) {
        try {
            User loginUser = userAuthentication();
            if (loginUser != null) {
                logActivity("Success");
                JDBC.user = getUserNameLogin();
                JDBC.userID = userDAO.getUserID(JDBC.user);
                checkUserAppoints(JDBC.userID);
                nav.navigate(actionEvent, Nav.customerMenuLoc, Nav.customerMenuTitle);
            } else {
                logActivity("Fail");
                ResourceBundle rb = ResourceBundle.getBundle("Utility/MessageBundle", Locale.getDefault());
                incorrectLoginText.setText(rb.getString("incorrectLoginText"));
            }
        } catch (IOException e) {
            MyAlerts.alertError("Navigation failed, contact IT.");
        } catch (SQLException e) {
            MyAlerts.alertError("User authentication SQL query failed, contact IT.");
        }
    }

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
    public User userAuthentication() {
        try {
            UserDAO userDAO = new UserDAOImp();
            String userNameLogin = getUserNameLogin();
            String passwordLogin = getPasswordLogin();
            return userDAO.authenticateUser(userNameLogin, passwordLogin);
        } catch (SQLException e) {
            MyAlerts.alertError("User authentication SQL query failed, contact IT.");
        } return null;
    }

    /**Queries database to check for user appointments within the next fifteen minutes.
     * @param userID id of user to check*/
    public void checkUserAppoints(int userID) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp fifteenFromNow = Timestamp.valueOf(LocalDateTime.now().plusMinutes(15));
        try {
            HashMap<String, Timestamp> inBetween = new HashMap<>();
            HashMap<String, Timestamp> toCheck = userDAO.getUserAppointments(userID);

            for(Map.Entry<String, Timestamp> checkTime : toCheck.entrySet()) {
                if (checkTime.getValue().before(fifteenFromNow) && checkTime.getValue().after(now)) {
                    inBetween.put(checkTime.getKey(), checkTime.getValue());
                }
            }

            if (!inBetween.isEmpty()) {
                for (Map.Entry<String, Timestamp> toPrompt : inBetween.entrySet()) {
                    MyAlerts.alertInfo("You have the following appointment within 15 minutes.\n" +
                            "Appointment ID: " + toPrompt.getKey() + "\n" +
                            "Appointment Start: " + toPrompt.getValue());
                }
            } else MyAlerts.alertInfo("You have no immediate appointments.");

        } catch (SQLException e) {
            MyAlerts.alertError("User appointment data could not be processed.");
        }
    }

    /**Logs any login attempt to login_activity.txt.
     * DateTime is in UTC.
     * @param logResult whether or not the login attempt was successful. */
    public void logActivity(String logResult) {
        try {
            String user = getUserNameLogin();
            Instant dateTimeUTC = Instant.now();
            String log = "\n" + user + " | " + dateTimeUTC + " | " + logResult;
            FileWriter fw = new FileWriter("login_activity.txt", true);
            fw.write(log);
            fw.close();
        } catch (IOException e) {
            MyAlerts.alertError("Login activity failed to log, contact IT");
        }
    }

    /**Sets appropriate language to the Login menu.
     * Uses key:value pairs in MessageBundles.
     * If user's language is French, then call frenchRelocate.
     * @param resourceBundle created in initialize method. */
    public void setLanguage(ResourceBundle resourceBundle){
        String userLocation = TimeZone.getDefault().getID();
        location.setText(resourceBundle.getString("location") + userLocation);
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
