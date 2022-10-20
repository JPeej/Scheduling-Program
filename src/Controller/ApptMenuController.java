package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ApptMenuController {

    Nav nav = new Nav();

    @FXML
    public void onActionCustomerMenu(ActionEvent actionEvent) throws IOException {
        nav.toCustomersMenu(actionEvent);
    }

    @FXML
    public void onActionApptMenu(ActionEvent actionEvent) throws IOException {
        nav.toAppointmentsMenu(actionEvent);
    }

    @FXML
    public void onActionReportsMenu(ActionEvent actionEvent) throws IOException {
        nav.toReportsMenu(actionEvent);
    }


    @FXML
    public void onActionAddAppt(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.addAppointmentLoc, Nav.addAppointmentTitle);
    }

    @FXML
    public void onActionModifyAppt(ActionEvent actionEvent) throws IOException {
        nav.navigate(actionEvent, Nav.modifyAppointmentLoc, Nav.modifyAppointmentTitle);
    }

    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
