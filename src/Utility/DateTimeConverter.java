package Utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

    private static String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    public static ZonedDateTime dateTimeToClient(String stringDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        LocalDateTime localDateTime = LocalDateTime.parse(stringDateTime, formatter);
        ZoneId utcZone = ZoneOffset.UTC;
        ZonedDateTime dateTimeDB = ZonedDateTime.of(localDateTime, utcZone);
        ZoneId clientZoneID = ZoneId.systemDefault();
        ZonedDateTime dateTimeClient = dateTimeDB.withZoneSameInstant(clientZoneID);
        return dateTimeClient;
    }
}
