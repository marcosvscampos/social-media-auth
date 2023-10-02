package com.roguesoft.socialmedia.auth.domain.usecase;

public interface GetRegistryUseCase<String, T> {

    T execute(final String id);

}