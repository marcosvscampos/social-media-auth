package com.roguesoft.socialmedia.auth.domain.usecase.impl.keypair;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import com.roguesoft.socialmedia.auth.application.dto.keypair.KeyPairDTO;
import com.roguesoft.socialmedia.auth.domain.entity.User;
import com.roguesoft.socialmedia.auth.domain.mapper.DomainMapper;
import com.roguesoft.socialmedia.auth.domain.ports.DatabasePort;
import com.roguesoft.socialmedia.auth.domain.ports.HttpClientPort;
import com.roguesoft.socialmedia.auth.domain.usecase.CreateRegistryUseCase;
import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateKeyPairForUserUseCase implements CreateRegistryUseCase<KeyPairDTO, ResponseDTO> {

    private final DatabasePort<KeyPair> keyPairDatabasePort;
    private final HttpClientPort<User> userClientPort;

    private final DomainMapper<KeyPairDTO, KeyPair> domainMapper;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${app.config.keypair.algorithmType:DSA}")
    private String algorithmType;

    @Value("${app.config.keypair.keySize:2048}")
    private String keySize;

    @Override
    public ResponseDTO execute(KeyPairDTO request) {
        String userId = request.getUserId();

        User userFromIntegration = userClientPort.getById(userId);

        String keyId = keyPairDatabasePort.create(this.prepare(userFromIntegration.getId()));
        return domainMapper.toResponseURI(contextPath, keyId);
    }

    private KeyPair prepare(final String userId){
        KeyPair kp = new KeyPair();
        kp.buildKeyPair(userId, algorithmType, Integer.parseInt(keySize));
        return kp;
    }
}
