package pl.wujekscho.beer.brewing.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wujekscho.beer.security.dto.UserDto;
import pl.wujekscho.beer.utils.ZonedDateTimeSerializer;
import pl.wujekscho.beer.yeast.dto.YeastDto;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrewingDto {
    private Long id;
    private String name;
    private String style;
    private Double gravity;
    private Double volume;
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime created;
    private YeastDto yeast;
    private UserDto user;
}
