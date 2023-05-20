package helloworld.api.config;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TimeHandler {

    private static ZoneId zoneId = ZoneId.systemDefault();
    private static Clock clock = Clock.systemDefaultZone();

    public static LocalDateTime now() {
        return LocalDateTime.now(getClock());
    }

    public static void useFixedClockAt(LocalDateTime date) {
        clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId);
    }

    public static void useSystemDefaultZoneClock() {
        clock = Clock.systemDefaultZone();
    }

    public static Clock getClock() {
        return clock;
    }
}
