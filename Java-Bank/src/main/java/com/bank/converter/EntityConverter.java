package com.bank.converter;

// raw type
public interface EntityConverter<E, T> {

    T toDto(E entity);

    E toEntity(T entityDto);
}
