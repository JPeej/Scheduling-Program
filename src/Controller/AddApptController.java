package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class AddApptController {

    Nav nav = new Nav();

    /**Event handler to Appointment Menu.
     * Saves data and updates database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionSaveAppt(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    /**Event handler to Appointment Menu.
     * Discards any changes, makes no changes to database.
     * See Nav.toAppointmentsMenu.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }
}
