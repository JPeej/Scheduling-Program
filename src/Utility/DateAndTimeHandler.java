package Utility;

import Model.Appointment;
import java.sql.Timestamp;
import java.time.*;

/**Handles time zone conversion for database to and or from client exchange.*/
public class DateAndTimeHandler {

    /**Converts office business hours of 0800-2200 EST to user default time zone.
     * Makes certain user can not schedule an appointment outside of normal business hours.
     * Populates combo boxes with adjusted times. */
    public static void appointmentTimes() {
        LocalTime zonedStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0))
                .atZone(ZoneId.of("US/Eastern"))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalTime();
        LocalTime zonedEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0))
                .atZone(ZoneId.of("US/Eastern"))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalTime();
        while (zonedStart.isBefore(zonedEnd)) {
            Appointment.times.add(zonedStart);
            zonedStart = zonedStart.plusMinutes(30);
        } Appointment.times.add(zonedEnd);
    }
}
