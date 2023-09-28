package com.roguesoft.socialmedia.auth.infrastructure.crypto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashCreator implements HashCreator {

    public String create(final String input) {
        try {
            String hashText;
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute message digest of the input
            byte[] messageDigest = md.digest(input.getBytes());

            hashText = convertToHex(messageDigest);

            return hashText;
        } catch (NoSuchAlgorithmException nsex) {
            throw new RuntimeException("No such algorithm MD5");
        }
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

}
