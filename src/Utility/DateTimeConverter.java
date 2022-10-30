package Utility;

import Model.Appointment;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**Handles time zone conversion for database to and or from client exchange.*/
public class DateTimeConverter {

    private static final String dateTimeClientFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final ZoneId userZone = ZoneOffset.systemDefault();
    private static final ZoneId utcZone = ZoneOffset.UTC;

    /**Converts time from to UTC to user's system default.
     * @param  dbDateTime Timestamp queried from database.
     * @return clientDateTime formatted string for customer menu view. */
    public static String dateTimeToClient(Timestamp dbDateTime) {
        LocalDateTime dbLocalDateTime = dbDateTime.toLocalDateTime();
        ZonedDateTime dbZonedDateTime = ZonedDateTime.of(dbLocalDateTime, utcZone);
        ZonedDateTime clientZonedDatetime = dbZonedDateTime.withZoneSameInstant(userZone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeClientFormat);
        String  clientDateTime = clientZonedDatetime.format(dateTimeFormatter);
        return clientDateTime;
    }

    /**Converts time from to user's system default to UTC.
     * @param  clientDateTime String from Customer object.
     * @return dbTimestamp Timestamp for database date columns. */
    public static Timestamp dateTimeToDB(String clientDateTime) {
        clientDateTime = stringFormatter(clientDateTime);
        LocalDateTime ldt = LocalDateTime.parse(clientDateTime);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, userZone);
        ZoneId utcZone = ZoneOffset.UTC;
        ZonedDateTime dateTimeDB = zdt.withZoneSameInstant(utcZone);
        Timestamp dbTimestamp = Timestamp.valueOf(dateTimeDB.toLocalDateTime());
        return dbTimestamp;
    }

    public static void appointmentTimes(LocalTime inputTime) {
        LocalTime zonedStart = LocalDateTime.of(LocalDate.now(), inputTime)
                .atZone(ZoneId.of("SystemV/EST5"))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalTime();
        LocalTime zonedEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 00))
                .atZone(ZoneId.of("SystemV/EST5"))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalTime();
        while (zonedStart.isBefore(zonedEnd)) {
            Appointment.times.add(zonedStart);
            zonedStart = zonedStart.plusMinutes(30);
        } Appointment.times.add(zonedEnd);
    }

    /**Formats String of a Customer's datetime.
     * Removes all spaces and returns substring needed for LocalDateTime.parse().
     * @param dateTime String
     * @return formatted dateTime.*/
    public static String stringFormatter(String dateTime) {
        dateTime = dateTime.replaceAll("\\s", "");
        return dateTime.substring(0, 19);
    }
}
