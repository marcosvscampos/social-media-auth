package com.roguesoft.socialmedia.auth.infrastructure.repository;

import com.roguesoft.socialmedia.auth.infrastructure.model.CredentialModel;

public interface CredentialRepository {

    CredentialModel save(final CredentialModel model);

}
