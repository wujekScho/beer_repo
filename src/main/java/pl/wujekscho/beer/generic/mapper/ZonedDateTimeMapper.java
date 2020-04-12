package pl.wujekscho.beer.generic.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Mapper(componentModel = "cdi")
public interface ZonedDateTimeMapper {

    ZoneId SERVER_ZONE_ID = ZoneId.of("Europe/Warsaw");

    default ZonedDateTime fromLocalDateTime(LocalDateTime localDateTime, @Context ZoneId zoneId) {
        return localDateTime != null ? localDateTime.atZone(SERVER_ZONE_ID).withZoneSameInstant(zoneId) : null;
    }

    default LocalDateTime fromZonedDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime != null ? zonedDateTime.withZoneSameInstant(SERVER_ZONE_ID).toLocalDateTime() : null;
    }

    default ZonedDateTime fromDate(Date date, @Context ZoneId zoneId) {
        return date != null ? ZonedDateTime.ofInstant(date.toInstant(), zoneId) : null;
    }
}