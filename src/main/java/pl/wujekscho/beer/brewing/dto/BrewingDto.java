package pl.wujekscho.beer.brewing.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wujekscho.beer.brewing.validation.BrewingName;
import pl.wujekscho.beer.utils.LocalDateTimeSerializer;
import pl.wujekscho.beer.yeast.dto.YeastDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrewingDto {
    private Long id;
    @NotNull
    @BrewingName
    private String name;
    private String style;
    @NotNull
    @Positive
    private Double gravity;
    @NotNull
    @Positive
    private Double volume;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;
    private YeastDto yeast;
}
