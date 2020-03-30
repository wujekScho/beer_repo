package pl.wujekscho.beer.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wujekscho.beer.email.entity.EmailType;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailEvent {
    @NotNull
    public LocalDateTime timestamp;
    @NotNull
    public String creator;
    @NotNull
    public EmailType type;
    @NotNull
    public String recipient;
    public String token;
}
