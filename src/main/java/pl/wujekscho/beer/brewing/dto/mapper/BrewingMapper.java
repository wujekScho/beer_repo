package pl.wujekscho.beer.brewing.dto.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.wujekscho.beer.brewing.dto.BrewingDto;
import pl.wujekscho.beer.brewing.dto.BrewingRequest;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.exception.NoDBResultException;
import pl.wujekscho.beer.mapper.GenericMapper;
import pl.wujekscho.beer.security.dto.mapper.UserMapper;
import pl.wujekscho.beer.yeast.dto.mapper.YeastMapper;
import pl.wujekscho.beer.yeast.entity.Yeast;

import java.util.List;


@Mapper(componentModel = "cdi", uses = {YeastMapper.class, UserMapper.class})
public interface BrewingMapper extends GenericMapper<Brewing, BrewingDto> {

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
}
