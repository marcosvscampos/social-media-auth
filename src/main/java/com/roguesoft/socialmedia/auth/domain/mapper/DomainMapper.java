package com.roguesoft.socialmedia.auth.domain.mapper;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Getter
@RequiredArgsConstructor
public abstract class DomainMapper<R, E> {

    private final ModelMapper mapper;

    public abstract E toEntity(R r);

    public abstract R toResponse(E e);

    public abstract ResponseDTO toResponseURI(String contextPath, String id);

}
