package com.roguesoft.socialmedia.auth.infrastructure.mapper;

import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import com.roguesoft.socialmedia.auth.infrastructure.mapper.formatter.KeyParser;
import com.roguesoft.socialmedia.auth.infrastructure.mapper.formatter.PublicKeyParser;
import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.security.PublicKey;
import java.util.Objects;

@Component
@Primary
public class KeyPairMapper extends DataMapper<KeyPair, KeyPairModel> {

    private final KeyParser parser;

    public KeyPairMapper(final ModelMapper mapper) {
        super(mapper);
        parser = new PublicKeyParser();
    }

    @Override
    public KeyPairModel toModel(final KeyPair entity){
        return super.getMapper().map(entity, KeyPairModel.class);
    }

    @Override
    public KeyPair toEntity(final KeyPairModel model){
        KeyPair entity = super.getMapper().map(model, KeyPair.class);
        entity.setPublicKey(this.convertPublicKey(model.getPublicKey()));
        return entity;
    }

    private PublicKey convertPublicKey(final String value){
        if(!ObjectUtils.isEmpty(value)){
            return (PublicKey) parser.parse(value);
        }
        return null;
    }
}
