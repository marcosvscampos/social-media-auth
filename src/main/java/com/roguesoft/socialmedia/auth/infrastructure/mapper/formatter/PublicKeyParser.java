package com.roguesoft.socialmedia.auth.infrastructure.mapper.formatter;

import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class PublicKeyParser implements KeyParser {

    @Override
    public PublicKey parse(String keyStr) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(keyStr);

            String algorithm = "RSA";

            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException("Error during public-key parsing", e);
        }
    }
}
