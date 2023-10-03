package com.roguesoft.socialmedia.auth.application.api;

import com.roguesoft.socialmedia.auth.application.dto.keypair.KeyPairDTO;
import com.roguesoft.socialmedia.auth.domain.usecase.GetRegistryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Qualifier("getUserKeyPairUseCase")
    private final GetRegistryUseCase<String, KeyPairDTO> getUserKeyPairUseCase;

    @GetMapping(value = "/{id}/keys", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyPairDTO> getPublicKey(@PathVariable(name = "id") final String userId){
        return ResponseEntity.ok(getUserKeyPairUseCase.execute(userId));
    }

}
