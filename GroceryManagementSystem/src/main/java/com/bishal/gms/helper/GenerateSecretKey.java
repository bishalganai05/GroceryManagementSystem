package com.bishal.gms.helper;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class GenerateSecretKey {
    public static void main(String[] args) {
        @SuppressWarnings("deprecation")
		SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String base64UrlEncodedKey = Encoders.BASE64URL.encode(key.getEncoded());
        System.out.println(base64UrlEncodedKey);
    }
}