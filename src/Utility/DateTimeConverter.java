package Utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

    private static String dateTimeClientFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    private static ZoneId userZone = ZoneOffset.systemDefault();
    private static ZoneId utcZone = ZoneOffset.UTC;

    public static String dateTimeToClient(Timestamp dateTimeFromDB) {
        LocalDateTime dbLocalDateTime = dateTimeFromDB.toLocalDateTime();
        ZonedDateTime dbZonedDateTime = ZonedDateTime.of(dbLocalDateTime, utcZone);
        ZonedDateTime dateTimeClient = dbZonedDateTime.withZoneSameInstant(userZone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeClientFormat);
        return dateTimeClient.format(dateTimeFormatter);
    }

    public static Timestamp dateTimeToDB(String dateTimeClient) {
        dateTimeClient = stringFormatter(dateTimeClient);
        LocalDateTime ldt = LocalDateTime.parse(dateTimeClient);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, userZone);
        ZoneId utcZone = ZoneOffset.UTC;
        ZonedDateTime dateTimeDB = zdt.withZoneSameInstant(utcZone);
        Timestamp timestampDB = Timestamp.valueOf(dateTimeDB.toLocalDateTime());
        return timestampDB;
    }

    public static String stringFormatter(String dateTime) {
        dateTime = dateTime.replaceAll("\\s", "");
        return dateTime.substring(0, 19);
    }
}
