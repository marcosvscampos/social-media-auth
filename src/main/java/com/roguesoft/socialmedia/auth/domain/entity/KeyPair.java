package com.roguesoft.socialmedia.auth.domain.entity;

import com.roguesoft.socialmedia.auth.infrastructure.crypto.HashCreator;
import com.roguesoft.socialmedia.auth.infrastructure.crypto.MD5HashCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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

    private PublicKey publicKey;

    private PrivateKey privateKey;

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

            this.privateKey = pair.getPrivate();
            this.publicKey = pair.getPublic();

            this.userId = userId;
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException("No algorithm for type: " + algorithm);
        }
    }

    public String encrypt(final String algorithm, final String value) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);

            byte[] plainTextBytes = value.getBytes();

            byte[] encryptedBytes = cipher.doFinal(plainTextBytes);

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error during value encryption: ", e);
        }
    }

    public String decrypt(final String algorithm, String encryptedValue) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, this.privateKey);

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedValue);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error during value decryption: ", e);
        }
    }

    private void generateId(final HashCreator hashCreator){
        this.id = hashCreator.create(this.userId);
    }

    public String toString(){
        return "[ID: " + this.getId() + " | " + "USER_ID " + this.getUserId() + " | " + "PUBLIC_KEY " + this.getPublicKey() + " ]";
    }

}
