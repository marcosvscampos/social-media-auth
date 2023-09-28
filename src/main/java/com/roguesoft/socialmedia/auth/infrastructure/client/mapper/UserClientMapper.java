package com.roguesoft.socialmedia.auth.infrastructure.client.mapper;

import com.roguesoft.socialmedia.auth.domain.entity.User;
import com.roguesoft.socialmedia.auth.infrastructure.client.dto.UserClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserClientMapper extends ClientMapper<UserClientDTO, User> {

    public UserClientMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public User toEntity(UserClientDTO dto) {
        return super.getMapper().map(dto, User.class);
    }

}
