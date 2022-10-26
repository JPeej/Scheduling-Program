package Main;

import DAO.*;
import Model.Customer;
import Utility.DateTimeConverter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        primaryStage.setTitle(("Login"));
        primaryStage.setScene(new Scene(root, 240, 270));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException, IOException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();

    }
}
