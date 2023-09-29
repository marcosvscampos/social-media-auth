package com.roguesoft.socialmedia.auth.infrastructure.repository;

import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;

import java.util.Optional;

public interface KeyPairRepository {

    KeyPairModel save(final KeyPairModel model);

    Optional<KeyPairModel> findByUserId(final String userId);

}