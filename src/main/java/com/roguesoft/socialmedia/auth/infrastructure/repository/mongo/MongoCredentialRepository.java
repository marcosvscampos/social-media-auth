package com.roguesoft.socialmedia.auth.infrastructure.repository.mongo;

import com.roguesoft.socialmedia.auth.infrastructure.model.CredentialModel;
import com.roguesoft.socialmedia.auth.infrastructure.repository.CredentialRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCredentialRepository extends CredentialRepository, MongoRepository<CredentialModel, String> {
}
