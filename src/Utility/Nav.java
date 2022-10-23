package Utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**Manage navigation between menus.
 * Static strings available for Controller package. */
public class Nav {

    public static String addAppointmentLoc = "/View/AddAppointment.fxml";
    public static String addCustomerLoc = "/View/AddCustomer.fxml";
    public static String appointmentMenuLoc = "/View/AppointmentMenu.fxml";
    public static String customerMenuLoc = "/View/CustomerMenu.fxml";
    public static String loginLoc = "/View/Login.fxml";
    public static String modifyAppointmentLoc = "/View/ModifyAppointment.fxml";
    public static String modifyCustomerLoc = "/View/ModifyCustomer.fxml";
    public static String reportMenuLoc = "/View/ReportMenu.fxml";

    public static String addAppointmentTitle = "Add Appointment";
    public static String addCustomerTitle = "Add Customer";
    public static String appointmentMenuTitle = "Appointment Menu";
    public static String customerMenuTitle = "Customer Menu";
    public static String loginTitle = "Login";
    public static String modifyAppointmentTitle = "Modify Appointment";
    public static String modifyCustomerTitle = "Modify Customer";
    public static String reportMenuTitle = "Report Menu";

    Stage stage;
    Parent scene;

    /**
     * Navigate to requested menu.
     * Upon activating event handler via button, handles navigation to menu.
     *
     * @param event        ActionEvent instantiated via event handler tied to button.
     * @param fxmlLocation String of fxml file location of desired menu.
     * @param title        String of title.
     * @throws IOException if stage or scene can't be set.
     */
    public void navigate(ActionEvent event, String fxmlLocation, String title) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(fxmlLocation));
        stage.setTitle(title);
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Navigate to Customer Menu.
     * Multiple menus need this method, DRY.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    public void toCustomersMenu(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, Nav.customerMenuLoc, Nav.customerMenuTitle);
    }

    /**Navigate to Appointment Menu.
     * Multiple menus need this method, DRY.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    public void toAppointmentsMenu(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, Nav.appointmentMenuLoc, Nav.appointmentMenuTitle);
    }

    /**Navigate to Report Menu.
     * Multiple menus need this method, DRY.
     * @param actionEvent ActionEvent instantiated via event handler tied to button.*/
    public void toReportsMenu(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, Nav.reportMenuLoc, Nav.reportMenuTitle);
    }
}
