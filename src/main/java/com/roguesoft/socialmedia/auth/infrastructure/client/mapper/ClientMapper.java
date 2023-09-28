package com.roguesoft.socialmedia.auth.infrastructure.client.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


@Getter
@RequiredArgsConstructor
public abstract class ClientMapper<R, E> {

    private final ModelMapper mapper;

    public abstract E toEntity(R r);

}
