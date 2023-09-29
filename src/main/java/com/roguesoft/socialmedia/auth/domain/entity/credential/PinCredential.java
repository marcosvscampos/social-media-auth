package com.roguesoft.socialmedia.auth.domain.entity.credential;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PinCredential extends Credential {

    private String pinNumber;

    public PinCredential(){
        super();
        this.setType();
    }

    @Override
    public void setSecretValue(String value) {
        this.pinNumber = value;
    }

    @Override
    public String getSecretValue() {
        return this.pinNumber;
    }

    @Override
    public void setType() {
        super.setCredentialType(CredentialType.PIN);
    }
}
