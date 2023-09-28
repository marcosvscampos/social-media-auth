package com.roguesoft.socialmedia.auth.infrastructure.repository;

import com.roguesoft.socialmedia.auth.infrastructure.model.KeyPairModel;

public interface KeyPairRepository {

    KeyPairModel save(final KeyPairModel model);

}