package pl.wujekscho.beer.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Time {
    public static final ZoneId UTC = ZoneId.of("UTC");

    public static LocalDateTime nowAtUtc() {
        return LocalDateTime.now(UTC);
    }
}
