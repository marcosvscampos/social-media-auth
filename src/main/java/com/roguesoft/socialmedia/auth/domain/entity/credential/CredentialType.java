package com.roguesoft.socialmedia.auth.domain.entity.credential;

import lombok.Getter;

@Getter
public enum CredentialType {

    PASSWORD(new PasswordCredential()), PIN(new PinCredential());

    private Credential credential;

    CredentialType(final Credential credential) {
        this.credential = credential;
    }

    public void setCredential(final Credential credential){
        this.credential = credential;
    }
}
