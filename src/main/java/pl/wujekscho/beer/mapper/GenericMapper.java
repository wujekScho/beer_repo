package pl.wujekscho.beer.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;

public interface GenericMapper<E, D> {
    @Named("toDto")
    D toDto(E entity);

    @Named("toEntity")
    E toEntity(D dto);

    @IterableMapping(qualifiedByName = "toDto")
    List<D> toDtoList(List<E> entities);

    @IterableMapping(qualifiedByName = "toEntity")
    List<E> toEntityList(List<D> entities);
}

