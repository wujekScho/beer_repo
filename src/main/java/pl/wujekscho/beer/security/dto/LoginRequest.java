package pl.wujekscho.beer.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    @NotBlank
    @Email
    private String login;
    @NotNull
    @NotBlank
    private String password;
}
