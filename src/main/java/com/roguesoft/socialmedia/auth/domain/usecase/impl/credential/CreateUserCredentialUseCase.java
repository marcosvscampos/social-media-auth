package com.roguesoft.socialmedia.auth.domain.usecase.impl.credential;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import com.roguesoft.socialmedia.auth.application.dto.credential.CredentialDTO;
import com.roguesoft.socialmedia.auth.domain.entity.User;
import com.roguesoft.socialmedia.auth.domain.entity.credential.Credential;
import com.roguesoft.socialmedia.auth.domain.mapper.DomainMapper;
import com.roguesoft.socialmedia.auth.domain.ports.DatabasePort;
import com.roguesoft.socialmedia.auth.domain.ports.HttpClientPort;
import com.roguesoft.socialmedia.auth.domain.usecase.CreateRegistryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserCredentialUseCase implements CreateRegistryUseCase<CredentialDTO, ResponseDTO> {

    private final DatabasePort<Credential> credentialDatabasePort;
    private final HttpClientPort<User> userClientPort;

    private final DomainMapper<CredentialDTO, Credential> domainMapper;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public ResponseDTO execute(final CredentialDTO request) {
        userClientPort.getById(request.getUserId());
        Credential entity = domainMapper.toEntity(request);

        String credentialId = credentialDatabasePort.create(entity);
        return domainMapper.toResponseURI(contextPath, credentialId);
    }
}
