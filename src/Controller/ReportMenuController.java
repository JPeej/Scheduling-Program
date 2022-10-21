package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**Controller for ReportMenu. */
public class ReportMenuController {

    Nav nav = new Nav();

    /**Event handler to Appointment Menu.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionApptMenu(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    /**Event handler to Customer Menu.
     * See Nav.toCustomersMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCustomersMenu(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    /**Event handler to Reports Menu.
     * See Nav.toReportsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionReportsMenu(ActionEvent actionEvent) throws IOException {
        nav.toReportsMenu(actionEvent);
    }

    /**Event handler to exit program.
     * Closes program and connection to database.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
