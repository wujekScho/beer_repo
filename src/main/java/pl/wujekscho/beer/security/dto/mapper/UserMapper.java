package pl.wujekscho.beer.security.dto.mapper;

import org.mapstruct.Mapper;
import pl.wujekscho.beer.mapper.GenericMapper;
import pl.wujekscho.beer.security.dto.UserDto;
import pl.wujekscho.beer.security.entity.User;

@Mapper(componentModel = "cdi")
public interface UserMapper extends GenericMapper<User, UserDto> {
}