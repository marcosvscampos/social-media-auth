package com.roguesoft.socialmedia.auth.infrastructure.repository;

import com.roguesoft.socialmedia.auth.infrastructure.model.CredentialModel;

import java.util.Optional;

public interface CredentialRepository {

    CredentialModel save(final CredentialModel model);

    Optional<CredentialModel> findByUserIdAndType(final String userId, final String type);

}
