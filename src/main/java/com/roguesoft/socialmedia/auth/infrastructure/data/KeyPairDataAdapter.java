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
        KeyPairModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No KeyPair found for ID: " + id));
        return dataMapper.toEntity(model);
    }

    @Override
    public KeyPair findByUserId(String userId) {
        KeyPairModel model = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No KeyPair found for USER: " + userId));
        return dataMapper.toEntity(model);
    }
}
