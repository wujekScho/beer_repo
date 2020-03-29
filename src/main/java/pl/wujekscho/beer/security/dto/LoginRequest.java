package pl.wujekscho.beer.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    @Email
    private String login;
    @NotBlank
    private String password;
}
