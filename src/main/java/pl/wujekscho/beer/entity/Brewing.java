package pl.wujekscho.beer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
public class Brewing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public String name;
    @NotNull
    @Positive
    public Double gravity;
    @NotNull
    @Positive
    public Double volume;
}