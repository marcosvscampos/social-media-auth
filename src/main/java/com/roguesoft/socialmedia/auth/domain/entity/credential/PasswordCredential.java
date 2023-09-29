package com.roguesoft.socialmedia.auth.domain.entity.credential;

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
    public void setSecretValue(final String value) {
        this.password = value;
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
