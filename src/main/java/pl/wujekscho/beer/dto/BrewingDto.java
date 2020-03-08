package pl.wujekscho.beer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wujekscho.beer.validation.BrewingNameConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrewingDto {
    private Long id;
    @NotNull
    @BrewingNameConstraint
    private String name;
    private String style;
    @NotNull
    @Positive
    private Double gravity;
    @NotNull
    @Positive
    private Double volume;
}
