package com.roguesoft.socialmedia.auth.domain.entity.credential;

import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import com.roguesoft.socialmedia.auth.infrastructure.crypto.HashCreator;
import com.roguesoft.socialmedia.auth.infrastructure.crypto.MD5HashCreator;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public abstract class Credential {

    private String id;

    private String userId;

    private CredentialType credentialType;

    public String getId(){
        if(Objects.isNull(this.id)) {
            this.generateId(new MD5HashCreator());
        }
        return this.id;
    }

    public void setId(){
        this.generateId(new MD5HashCreator());
    }

    public abstract void setSecretValue(final KeyPair keyPair, final String value);

    public abstract String getSecretValue();

    public abstract void setType();

    private void generateId(final HashCreator hashCreator){
        this.id = hashCreator.create(this.userId + ":" + this.credentialType.name());
    }

}
