package com.roguesoft.socialmedia.auth.domain.mapper;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import com.roguesoft.socialmedia.auth.application.dto.keypair.KeyPairDTO;
import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Base64;
import java.util.Objects;

@Component
public class KeyPairDomainMapper extends DomainMapper<KeyPairDTO, KeyPair> {

    public KeyPairDomainMapper(final ModelMapper mapper) {
        super(mapper);

        Converter<PublicKey, String> convertPublicKeyToBase64Str = ctx -> Objects.nonNull(ctx.getSource()) ?
                Base64.getEncoder().encodeToString(ctx.getSource().getEncoded()) : null;

        TypeMap<KeyPair, KeyPairDTO> typeMapToResponse = super.getMapper().createTypeMap(KeyPair.class, KeyPairDTO.class);
        typeMapToResponse.addMappings(mappings -> mappings.using(convertPublicKeyToBase64Str).map(KeyPair::getPublicKey, KeyPairDTO::setPublicKey));
    }

    @Override
    public KeyPair toEntity(KeyPairDTO request) {
        return super.getMapper().map(request, KeyPair.class);
    }

    @Override
    public KeyPairDTO toResponse(KeyPair keyPair) {
        KeyPairDTO response = super.getMapper().map(keyPair, KeyPairDTO.class);

        return response;
    }

    @Override
    public ResponseDTO toResponseURI(String contextPath, String id) {
        return ResponseDTO.builder()
                .uri(contextPath + "/keys/" + id)
                .build();
    }
}
