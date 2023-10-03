package com.roguesoft.socialmedia.auth.infrastructure.client.feign;

import com.roguesoft.socialmedia.auth.infrastructure.client.dto.UserClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "social-media", url = "${social-media.api.host}")
public interface UserFeignClient {

    @GetMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserClientDTO getUserById(@PathVariable(name = "id") String userId);

    @GetMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserClientDTO> getUserByFilters(@RequestParam("username") String username);

    default UserClientDTO getUserByUsername(final String username){
        List<UserClientDTO> users = this.getUserByFilters(username);
        if(!users.isEmpty()){
            return users.get(0);
        }
        throw new RuntimeException("No users found for username: " + username);
    }

}
