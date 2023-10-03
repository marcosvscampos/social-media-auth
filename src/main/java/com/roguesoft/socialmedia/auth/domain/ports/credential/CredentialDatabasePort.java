package com.roguesoft.socialmedia.auth.domain.ports.credential;

import com.roguesoft.socialmedia.auth.domain.entity.credential.Credential;

public interface CredentialDatabasePort {

    Credential findByUserIdAndType(final String username, final String type);

}
