package pl.wujekscho.beer.dto.mapper;

import org.mapstruct.Mapper;
import pl.wujekscho.beer.dto.BrewingDto;
import pl.wujekscho.beer.entity.Brewing;


@Mapper(componentModel = "cdi")
public interface BrewingMapper {
    BrewingDto toDto(Brewing entity);

    Brewing toEntity(BrewingDto dto);
}
