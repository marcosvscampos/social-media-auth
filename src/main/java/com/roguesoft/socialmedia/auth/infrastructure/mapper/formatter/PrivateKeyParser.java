package com.roguesoft.socialmedia.auth.infrastructure.mapper.formatter;

import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
public class PrivateKeyParser implements KeyParser {

    @Override
    public PrivateKey parse(String keyStr){
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(keyStr);

            String algorithm = "RSA";

            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

            return keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException("Error during private-key parsing", e);
        }
    }

}
