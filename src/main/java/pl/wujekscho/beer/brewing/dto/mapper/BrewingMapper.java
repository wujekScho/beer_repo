package pl.wujekscho.beer.brewing.dto.mapper;

import org.mapstruct.Mapper;
import pl.wujekscho.beer.brewing.dto.BrewingDto;
import pl.wujekscho.beer.brewing.entity.Brewing;


@Mapper(componentModel = "cdi")
public interface BrewingMapper {
    BrewingDto toDto(Brewing entity);

    Brewing toEntity(BrewingDto dto);
}
