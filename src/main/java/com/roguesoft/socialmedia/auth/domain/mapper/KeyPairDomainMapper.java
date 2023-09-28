package com.roguesoft.socialmedia.auth.domain.mapper;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import com.roguesoft.socialmedia.auth.application.dto.keypair.KeyPairDTO;
import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class KeyPairDomainMapper extends DomainMapper<KeyPairDTO, KeyPair> {

    public KeyPairDomainMapper(final ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public KeyPair toEntity(KeyPairDTO request) {
        return super.getMapper().map(request, KeyPair.class);
    }

    @Override
    public KeyPairDTO toResponse(KeyPair KeyPair) {
        return null;
    }

    @Override
    public ResponseDTO toResponseURI(String contextPath, String id) {
        return ResponseDTO.builder()
                .uri(contextPath + "/keys/" + id)
                .build();
    }
}
