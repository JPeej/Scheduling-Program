package Main;

import DAO.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.sql.SQLException;
import java.time.Month;
import java.util.Arrays;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Login.fxml")));
        primaryStage.setTitle(("Login"));
        primaryStage.setScene(new Scene(root, 1150, 600));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException, IOException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
