package Main;

import DAO.JDBC;
import DAO.UserDAO;
import DAO.UserDAOImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle(("Login"));
        primaryStage.setScene(new Scene(root, 240, 270));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();

    }
}
