package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Nav {

    public static String addAppointmentLoc = "/View/AddAppointment.fxml";
    public static String addCustomerLoc = "/View/AddCustomer.fxml";
    public static String appointmentMenuLoc = "/View/AppointmentMenu.fxml";
    public static String customerMenuLoc = "/View/CustomerMenu.fxml";
    public static String loginLoc = "/View/Login.fxml";
    public static String modifyAppointmentLoc = "/View/ModifyAppointment.fxml";
    public static String reportMenuLoc = "/View/ReportMenu.fxml";

    public static String addAppointmentTitle = "Add Appointment";
    public static String addCustomerTitle = "Add Customer";
    public static String appointmentMenuTitle = "Appointment Menu";
    public static String customerMenuTitle = "Customer Menu";
    public static String loginTitle = "Login";
    public static String modifyAppointmentTitle = "Modify Appointment";
    public static String reportMenuTitle = "Report Menu";

    Stage stage;
    Parent scene;

    public void navigate(ActionEvent event, String fxmlLocation, String title) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(fxmlLocation));
        stage.setTitle(title);
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
