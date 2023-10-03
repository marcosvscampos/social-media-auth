package com.roguesoft.socialmedia.auth.infrastructure.client;

import com.roguesoft.socialmedia.auth.domain.entity.User;
import com.roguesoft.socialmedia.auth.domain.ports.HttpClientPort;
import com.roguesoft.socialmedia.auth.infrastructure.client.dto.UserClientDTO;
import com.roguesoft.socialmedia.auth.infrastructure.client.feign.UserFeignClient;
import com.roguesoft.socialmedia.auth.infrastructure.client.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHttpClientAdapter implements HttpClientPort<User> {

    private final UserFeignClient userClient;
    private final ClientMapper<UserClientDTO, User> userClientMapper;

    @Override
    public User getById(String id) {
        return userClientMapper.toEntity(userClient.getUserById(id));
    }

    @Override
    public User getByUsername(String username) {
        return userClientMapper.toEntity(userClient.getUserByUsername(username));
    }
}
