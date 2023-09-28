package com.roguesoft.socialmedia.auth.infrastructure.client.feign;

import com.roguesoft.socialmedia.auth.infrastructure.client.dto.UserClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "social-media", url = "${social-media.api.host}")
public interface UserFeignClient {

    @GetMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserClientDTO getUserById(@PathVariable(name = "id") String userId);

}
