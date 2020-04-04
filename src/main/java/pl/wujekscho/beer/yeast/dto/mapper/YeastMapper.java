package pl.wujekscho.beer.yeast.dto.mapper;

import org.mapstruct.Mapper;
import pl.wujekscho.beer.mapper.GenericMapper;
import pl.wujekscho.beer.yeast.dto.YeastDto;
import pl.wujekscho.beer.yeast.entity.Yeast;

@Mapper(componentModel = "cdi")
public interface YeastMapper extends GenericMapper<Yeast, YeastDto> {
}
