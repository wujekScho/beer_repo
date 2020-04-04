package pl.wujekscho.beer.brewing.dto.mapper;

import org.mapstruct.Mapper;
import pl.wujekscho.beer.brewing.dto.BrewingDto;
import pl.wujekscho.beer.brewing.entity.Brewing;
import pl.wujekscho.beer.mapper.GenericMapper;
import pl.wujekscho.beer.yeast.dto.mapper.YeastMapper;


@Mapper(componentModel = "cdi", uses = YeastMapper.class)
public interface BrewingMapper extends GenericMapper<Brewing, BrewingDto> {
}
