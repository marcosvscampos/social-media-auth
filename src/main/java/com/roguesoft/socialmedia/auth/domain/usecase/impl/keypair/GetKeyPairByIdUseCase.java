package com.roguesoft.socialmedia.auth.domain.usecase.impl.keypair;

import com.roguesoft.socialmedia.auth.application.dto.keypair.KeyPairDTO;
import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import com.roguesoft.socialmedia.auth.domain.mapper.DomainMapper;
import com.roguesoft.socialmedia.auth.domain.ports.DatabasePort;
import com.roguesoft.socialmedia.auth.domain.usecase.GetRegistryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GetKeyPairByIdUseCase implements GetRegistryUseCase<String, KeyPairDTO> {

    private final DatabasePort<KeyPair> databasePort;
    private final DomainMapper<KeyPairDTO, KeyPair> domainMapper;

    @Override
    public KeyPairDTO execute(final String keyId) {
        return domainMapper.toResponse(databasePort.findById(keyId));
    }
}
