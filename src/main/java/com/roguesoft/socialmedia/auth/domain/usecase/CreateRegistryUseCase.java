package com.roguesoft.socialmedia.auth.domain.usecase;

public interface CreateRegistryUseCase<T, X> {

    X execute(T t);

}
