package com.roguesoft.socialmedia.auth.infrastructure.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Getter
@RequiredArgsConstructor
public abstract class DataMapper<E, M> {

    private final ModelMapper mapper;

    public abstract M toModel(E e);

    public abstract E toEntity(M m);
}
