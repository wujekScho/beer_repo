package pl.wujekscho.beer.time.zone.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.wujekscho.beer.time.zone.dto.TimeZoneDto;
import pl.wujekscho.beer.time.zone.entity.TimeZone;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TimeZoneMapper {
    @Named("toDto")
    @Mapping(source = "tzdbId", target = "region", qualifiedByName = "tzbIdRegionTransformation")
    TimeZoneDto toDto(TimeZone entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<TimeZoneDto> toDtoList(List<TimeZone> entities);

    @Named("tzbIdRegionTransformation")
    default String tzbIdRegionTransformation(String tzdbId) {
        return tzdbId.substring(tzdbId.indexOf("/")).replaceAll("[^a-zA-Z]", " ").trim();
    }
}
