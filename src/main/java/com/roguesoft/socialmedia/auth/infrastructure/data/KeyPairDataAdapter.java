package com.roguesoft.socialmedia.auth.infrastructure.data;


import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import com.roguesoft.socialmedia.auth.domain.ports.DatabasePort;
import com.roguesoft.socialmedia.auth.infrastructure.mapper.DataMapper;
import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;
import com.roguesoft.socialmedia.auth.infrastructure.repository.KeyPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeyPairDataAdapter implements DatabasePort<KeyPair> {

    @Qualifier("mongoKeyPairRepository")
    private final KeyPairRepository repository;

    private final DataMapper<KeyPair, KeyPairModel> dataMapper;

    @Override
    public String create(KeyPair keyPair) {
        KeyPairModel savedModel = repository.save(dataMapper.toModel(keyPair));
        return savedModel.getId();
    }

    @Override
    public KeyPair findById(String id) {
        return null;
    }
}
