package pl.wujekscho.beer.yeast.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YeastDto {
    public Long id;
    public String name;
    @JsonIgnore
    public String addedBy;
    @JsonIgnore
    public boolean pending;
}
