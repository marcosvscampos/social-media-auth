package com.roguesoft.socialmedia.auth.infrastructure.repository.mongo;

import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;
import com.roguesoft.socialmedia.auth.infrastructure.repository.KeyPairRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoKeyPairRepository extends KeyPairRepository, MongoRepository<KeyPairModel, String> {
}
