package com.roguesoft.socialmedia.auth.infrastructure.crypto;

public interface HashCreator {

    String create(final String input);

}
