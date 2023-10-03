package com.roguesoft.socialmedia.auth.domain.usecase.impl.login;

import com.roguesoft.socialmedia.auth.application.dto.login.LoginDTO;
import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import com.roguesoft.socialmedia.auth.domain.entity.User;
import com.roguesoft.socialmedia.auth.domain.entity.credential.Credential;
import com.roguesoft.socialmedia.auth.domain.ports.DatabasePort;
import com.roguesoft.socialmedia.auth.domain.ports.HttpClientPort;
import com.roguesoft.socialmedia.auth.domain.ports.credential.CredentialDatabasePort;
import com.roguesoft.socialmedia.auth.domain.usecase.DoLoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class UserLoginUseCase implements DoLoginUseCase {

    private final DatabasePort<KeyPair> keyPairDatabasePort;
    private final CredentialDatabasePort credentialDatabasePort;
    private final HttpClientPort<User> userClientPort;

    private static final String TYPE = "PASSWORD";

    @Override
    public String execute(final LoginDTO request) {
        KeyPair keyPair = keyPairDatabasePort.findById(request.getKeyId());

        String username = keyPair.decrypt("RSA", request.getUsername());
        String password = keyPair.decrypt("RSA", request.getPassword());

        User user = userClientPort.getByUsername(username);
        Credential credential = credentialDatabasePort.findByUserIdAndType(user.getId(), TYPE);

        String savedPassword = keyPair.decrypt("RSA", credential.getSecretValue());

        if(!password.equals(savedPassword)) {
            throw new RuntimeException("Invalid Login");
        }

        return null;
    }
}
