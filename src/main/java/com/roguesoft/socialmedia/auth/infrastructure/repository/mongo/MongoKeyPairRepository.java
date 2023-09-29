package com.roguesoft.socialmedia.auth.infrastructure.repository.mongo;

import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;
import com.roguesoft.socialmedia.auth.infrastructure.repository.KeyPairRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoKeyPairRepository extends KeyPairRepository, MongoRepository<KeyPairModel, String> {

    Optional<KeyPairModel> findByUserId(final String userId);

}
