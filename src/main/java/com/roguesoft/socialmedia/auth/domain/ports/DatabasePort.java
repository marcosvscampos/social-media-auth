package com.roguesoft.socialmedia.auth.domain.ports;

public interface DatabasePort<T> {

    String create(T t);

    T findById(String id);

}
