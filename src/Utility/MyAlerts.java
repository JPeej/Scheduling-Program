package Utility;

import javafx.scene.control.Alert;

/**Manages alerts within program, DRY. */
public class MyAlerts {

    /**Displays error alert message box.
     * Error will have custom message.
     * @param prompt String custom message to display. */
    public static void alertError(String prompt) {
        Alert alert = new Alert(Alert.AlertType.ERROR, prompt);
        alert.showAndWait();
    }

    /**Displays info alert message box.
     * Info will have custom message.
     * @param prompt String custom message to display. */
    public static void alertInfo(String prompt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, prompt);
        alert.showAndWait();
    }
}
