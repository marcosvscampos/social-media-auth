package com.roguesoft.socialmedia.auth.application.dto.credential;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.roguesoft.socialmedia.auth.domain.entity.credential.CredentialType;
import com.roguesoft.socialmedia.auth.domain.entity.credential.PasswordCredential;
import com.roguesoft.socialmedia.auth.domain.entity.credential.PinCredential;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialDTO implements Serializable {

    @JsonProperty("user_id")
    private String userId;

    private String username;

    @JsonProperty("secret_value")
    private String secretValue;

    @JsonProperty("credential_type")
    private CredentialType type;

    public CredentialType getType(){
        switch (this.type) {
            case PASSWORD -> this.type.setCredential(new PasswordCredential());
            case PIN -> this.type.setCredential(new PinCredential());
            default ->
                    throw new UnsupportedOperationException("There is no implementation available for credential: " + this.type);
        }
        return this.type;
    }

}
