package com.roguesoft.socialmedia.auth.domain.entity;

import com.roguesoft.socialmedia.auth.infrastructure.crypto.HashCreator;
import com.roguesoft.socialmedia.auth.infrastructure.crypto.MD5HashCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyPair {

    private String id;

    private String userId;

    private String publicKey;

    private String privateKey;

    public String getId(){
        if(Objects.isNull(this.id)){
            this.generateId(new MD5HashCreator());
        }
        return this.id;
    }

    public void buildKeyPair(final String userId, final String algorithm, final int keySize){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
            keyPairGenerator.initialize(keySize);

            java.security.KeyPair pair = keyPairGenerator.generateKeyPair();
            PrivateKey privKey = pair.getPrivate();
            PublicKey pubKey = pair.getPublic();

            this.privateKey = Base64.getEncoder().encodeToString(privKey.getEncoded());
            this.publicKey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
            this.userId = userId;
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException("No algorithm for type: " + algorithm);
        }
    }

    private void generateId(final HashCreator hashCreator){
        this.id = hashCreator.create(this.userId);
    }

    public String toString(){
        return "[ID: " + this.getId() + " | " + "USER_ID " + this.getUserId() + " | " + "PUBLIC_KEY " + this.getPublicKey() + " ]";
    }

}
