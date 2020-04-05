package pl.wujekscho.beer.brewing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wujekscho.beer.brewing.validation.BrewingName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrewingRequest {
    @NotBlank
    @BrewingName
    private String name;
    @NotBlank
    private String style;
    @NotNull
    @Positive
    private Double gravity;
    @NotNull
    @Positive
    private Double volume;
    @NotNull
    @Positive
    private Long yeastId;
}
