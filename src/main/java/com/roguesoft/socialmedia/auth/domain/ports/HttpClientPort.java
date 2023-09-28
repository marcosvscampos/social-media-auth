package com.roguesoft.socialmedia.auth.domain.ports;

public interface HttpClientPort<T> {

    T getById(final String id);

}
