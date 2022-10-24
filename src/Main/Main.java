package Main;


import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        primaryStage.setTitle(("Login"));
        primaryStage.setScene(new Scene(root, 240, 270));
        primaryStage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();

        String dtString = "2022-10-19 16:13:09";
        String format = "yyyy-MM-dd HH:mm:ss";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(dtString, formatter);
        ZoneId utc = ZoneOffset.UTC;
        ZonedDateTime dateTimeDB = ZonedDateTime.of(localDateTime, utc);
        System.out.println(dateTimeDB);
        ZoneId clientZoneID = ZoneId.systemDefault();
        ZonedDateTime dateTimeClient = dateTimeDB.withZoneSameInstant(clientZoneID);
        System.out.println(dateTimeClient);

        //launch(args);
        JDBC.closeConnection();
    }
}
