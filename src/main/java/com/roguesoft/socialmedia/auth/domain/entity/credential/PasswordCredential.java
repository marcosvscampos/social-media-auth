package com.roguesoft.socialmedia.auth.domain.entity.credential;

import com.roguesoft.socialmedia.auth.domain.entity.KeyPair;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PasswordCredential extends Credential {

    private String password;

    public PasswordCredential(){
        super();
        super.setCredentialType(CredentialType.PASSWORD);
    }

    @Override
    public void setSecretValue(final KeyPair keyPair, final String value) {
        this.password = value;
        //this.password = keyPair.encrypt("RSA", value);
    }

    @Override
    public String getSecretValue() {
        return this.password;
    }

    @Override
    public void setType() {
        super.setCredentialType(CredentialType.PASSWORD);
    }

}
