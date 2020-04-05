package pl.wujekscho.beer.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.wujekscho.beer.security.entity.Role;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    public Long id;
    public String login;
    @JsonIgnore
    public String password;
    @JsonIgnore
    public Set<Role> roles;
    @JsonIgnore
    public boolean activated;
}
