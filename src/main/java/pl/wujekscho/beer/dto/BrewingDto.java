package pl.wujekscho.beer.dto;

import lombok.Data;
import pl.wujekscho.beer.validation.BrewingNameConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class BrewingDto {
    public Long id;
    @NotNull
    @BrewingNameConstraint
    public String name;
    public String style;
    @NotNull
    @Positive
    public Double gravity;
    @NotNull
    @Positive
    public Double volume;
}
