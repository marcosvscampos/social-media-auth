package com.roguesoft.socialmedia.auth.application.api;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import com.roguesoft.socialmedia.auth.application.dto.keypair.KeyPairDTO;
import com.roguesoft.socialmedia.auth.domain.usecase.CreateRegistryUseCase;
import com.roguesoft.socialmedia.auth.domain.usecase.GetRegistryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keys")
@RequiredArgsConstructor
public class KeyPairController {

    private final CreateRegistryUseCase<KeyPairDTO, ResponseDTO> createUserKeyPairUseCase;

    @Qualifier("getKeyPairByIdUseCase")
    private final GetRegistryUseCase<String, KeyPairDTO> getKeyPairUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createKeyPair(@RequestBody final KeyPairDTO request){
        ResponseDTO response = createUserKeyPairUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{keyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyPairDTO> getPublicKey(@PathVariable(name = "keyId") final String keyId){
        return ResponseEntity.ok(getKeyPairUseCase.execute(keyId));
    }

}
