package com.roguesoft.socialmedia.auth.infrastructure.mapper;

import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class KeyPairMapper extends DataMapper<KeyPair, KeyPairModel> {

    public KeyPairMapper(final ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public KeyPairModel toModel(final KeyPair entity){
        return super.getMapper().map(entity, KeyPairModel.class);
    }

    @Override
    public KeyPair toEntity(final KeyPairModel model){
        return super.getMapper().map(model, KeyPair.class);
    }
}
