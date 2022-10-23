package Main;


import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle(("Login"));
        primaryStage.setScene(new Scene(root, 240, 270));
        primaryStage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
