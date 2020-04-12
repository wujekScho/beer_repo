package pl.wujekscho.beer.brewing.dto.mapper;

import org.mapstruct.*;
import pl.wujekscho.beer.brewing.dto.BrewingDto;
import pl.wujekscho.beer.brewing.dto.BrewingRequest;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.generic.exception.NoDBResultException;
import pl.wujekscho.beer.generic.mapper.ZonedDateTimeMapper;
import pl.wujekscho.beer.security.dto.mapper.UserMapper;
import pl.wujekscho.beer.yeast.dto.mapper.YeastMapper;
import pl.wujekscho.beer.yeast.entity.Yeast;

import java.time.ZoneId;
import java.util.List;


@Mapper(componentModel = "cdi", uses = {YeastMapper.class, UserMapper.class, ZonedDateTimeMapper.class})
public interface BrewingMapper {

    @Named("fromRequestToEntity")
    @Mapping(source = "yeastId", target = "yeast", qualifiedByName = "fromLongToYeast")
    Brewing fromRequestToEntity(BrewingRequest dto);

    @IterableMapping(qualifiedByName = "fromRequestToEntity")
    List<Brewing> fromRequestToEntityList(List<BrewingRequest> entities);

    @Named("fromLongToYeast")
    default Yeast fromLongToYeast(Long yeastId) {
        Yeast byId = Yeast.findById(yeastId);
        if (byId == null) {
            throw new NoDBResultException("No yeast found of given id.");
        }
        return byId;
    }

    @Named("toZonedDto")
    BrewingDto toDto(Brewing entity, @Context ZoneId zoneId);

    @IterableMapping(qualifiedByName = "toZonedDto")
    List<BrewingDto> toDtoList(List<Brewing> entities, @Context ZoneId zoneId);
}
