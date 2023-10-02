package com.roguesoft.socialmedia.auth.application.api;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import com.roguesoft.socialmedia.auth.application.dto.credential.CredentialDTO;
import com.roguesoft.socialmedia.auth.domain.usecase.CreateRegistryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credentials")
@RequiredArgsConstructor
public class CredentialController {

    private final CreateRegistryUseCase<CredentialDTO, ResponseDTO> createUserCredentialUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createUserCredential(@RequestBody final CredentialDTO request){
        ResponseDTO response = createUserCredentialUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
