package com.roguesoft.socialmedia.auth.domain.mapper;

import com.roguesoft.socialmedia.auth.application.dto.ResponseDTO;
import com.roguesoft.socialmedia.auth.application.dto.credential.CredentialDTO;
import com.roguesoft.socialmedia.auth.domain.entity.credential.Credential;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CredentialDomainMapper extends DomainMapper<CredentialDTO, Credential> {

    public CredentialDomainMapper(final ModelMapper mapper){
        super(mapper);
    }

    @Override
    public Credential toEntity(CredentialDTO request) {
        Credential credential = request.getType().getCredential();
        credential.setUsername(request.getUsername());
        credential.setUserId(request.getUserId());
        credential.setType();
        credential.setId();
        return credential;
    }

    @Override
    public CredentialDTO toResponse(Credential credential) {
        return null;
    }

    @Override
    public ResponseDTO toResponseURI(String contextPath, String id) {
        return ResponseDTO.builder()
                  .uri(contextPath + "/credentials/" + id)
                  .build();
    }
}
