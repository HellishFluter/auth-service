package ru.weber.auth.service.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.weber.auth.service.exception.JwtInitializationException;
import ru.weber.auth.service.service.jwt.util.Base64Util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Component
@RequiredArgsConstructor
public class JwtKeyProvider {

    private final Base64Util base64Util;


    public PublicKey readKey(String keyString) {
        try {
            return publicKeyGenerator(KeyFactory.getInstance("RSA"),publicKeySpec(keyString));
        } catch(NoSuchAlgorithmException e) {
            throw new JwtInitializationException(e);
        }
    }

    private EncodedKeySpec publicKeySpec(String data) {
        return new X509EncodedKeySpec(base64Util.decode(data));
    }

    private PublicKey publicKeyGenerator(KeyFactory kf, EncodedKeySpec spec) {
        try {
            return kf.generatePublic(spec);
        } catch(InvalidKeySpecException e) {
            throw new JwtInitializationException(e);
        }
    }
}
