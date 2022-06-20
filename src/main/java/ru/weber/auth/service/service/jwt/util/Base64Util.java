package ru.weber.auth.service.service.jwt.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Base64Util {
    public byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public String decodeString(String data) {
        return new String(Base64.getUrlDecoder().decode(data.getBytes(StandardCharsets.UTF_8)));
    }
}
