package Utility;

import Model.Appointment;
import java.sql.Timestamp;
import java.time.*;

/**Handles time zone conversion for database to and or from client exchange.*/
public class DateAndTimeHandler {

    private static final ZoneId userZone = ZoneOffset.systemDefault();
    private static final ZoneId utcZone = ZoneOffset.UTC;

    /**Converts time from to UTC to user's system default.
     * @param  dbTimestamp Timestamp queried from database.
     * @return clientTimestamp adjusted to user default time zone */
    public static Timestamp timestampToClient(Timestamp dbTimestamp) {
        LocalDateTime dbLocalDateTime = dbTimestamp.toLocalDateTime();
        ZonedDateTime dbZonedDateTime = ZonedDateTime.of(dbLocalDateTime, utcZone);
        ZonedDateTime clientZonedDatetime = dbZonedDateTime.withZoneSameInstant(userZone);
        LocalDateTime clientLocalDateTime = clientZonedDatetime.toLocalDateTime();
        Timestamp clientTimestamp = Timestamp.valueOf(clientLocalDateTime);
        return clientTimestamp;
    }

    /**Converts time from to user's system default to UTC.
     * @param  clientTimestamp String from Customer object.
     * @return dbTimestamp Timestamp for database date columns. */
    public static Timestamp timestampToDB(Timestamp clientTimestamp) {
        LocalDateTime clientLocalDateTime = clientTimestamp.toLocalDateTime();
        ZonedDateTime clientZonedDateTime = ZonedDateTime.of(clientLocalDateTime, userZone);
        ZonedDateTime dbZonedDateTime = clientZonedDateTime.withZoneSameInstant(utcZone);
        LocalDateTime dbLocalDateTime = dbZonedDateTime.toLocalDateTime();
        Timestamp dbTimestamp = Timestamp.valueOf(dbLocalDateTime);
        return dbTimestamp;
    }

    public static void appointmentTimes() {
        LocalTime zonedStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(8,00))
                .atZone(ZoneId.of("US/Eastern"))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalTime();
        LocalTime zonedEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 00))
                .atZone(ZoneId.of("US/Eastern"))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalTime();
        while (zonedStart.isBefore(zonedEnd)) {
            Appointment.times.add(zonedStart);
            zonedStart = zonedStart.plusMinutes(30);
        } Appointment.times.add(zonedEnd);
    }
}
