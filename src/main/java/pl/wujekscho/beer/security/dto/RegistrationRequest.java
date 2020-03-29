package pl.wujekscho.beer.security.dto;

import lombok.Getter;
import lombok.Setter;
import pl.wujekscho.beer.security.validation.Login;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RegistrationRequest {
    @Email
    @Login
    private String login;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password must contain at least 8 characters, one letter, one number and one special character.")
    private String password;
}
