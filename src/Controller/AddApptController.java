package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddApptController {

    Nav nav = new Nav();

    @FXML
    public void onActionSaveAppt(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }
}
