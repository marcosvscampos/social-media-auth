package com.roguesoft.socialmedia.auth.infrastructure.data;

import com.roguesoft.socialmedia.auth.domain.entity.credential.Credential;
import com.roguesoft.socialmedia.auth.domain.ports.DatabasePort;
import com.roguesoft.socialmedia.auth.infrastructure.mapper.DataMapper;
import com.roguesoft.socialmedia.auth.infrastructure.model.CredentialModel;
import com.roguesoft.socialmedia.auth.infrastructure.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CredentialDataAdapter implements DatabasePort<Credential> {

    @Qualifier("mongoCredentialRepository")
    private final CredentialRepository repository;

    @Qualifier("credentialMapper")
    private final DataMapper<Credential, CredentialModel> dataMapper;

    @Override
    public String create(Credential credential) {
        CredentialModel savedModel = repository.save(dataMapper.toModel(credential));
        return savedModel.getId();
    }

    @Override
    public Credential findById(String id) {
        return null;
    }
}
