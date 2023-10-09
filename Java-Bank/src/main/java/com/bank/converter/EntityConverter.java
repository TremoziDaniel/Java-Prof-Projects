package com.bank.converter;

// raw type
public interface EntityConverter<E, T> {

    public T toDto(E entity);

    public E toEntity(T entityDto);
}