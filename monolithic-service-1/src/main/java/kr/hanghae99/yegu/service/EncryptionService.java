package kr.hanghae99.yegu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class EncryptionService {

    private final AesBytesEncryptor encryptor;

    public String encryptString(String plainString) {
        byte[] encryptBytes = encryptor.encrypt(plainString.getBytes(StandardCharsets.UTF_8));
        return byteArrayToString(encryptBytes);
    }

    public String decryptString(String encryptedString) {
        byte[] decryptBytes = stringToByteArray(encryptedString);
        return new String(encryptor.decrypt(decryptBytes), StandardCharsets.UTF_8);
    }

    public String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b);
            sb.append(" ");
        }
        return sb.toString();
    }

    public byte[] stringToByteArray(String byteString) {
        String[] split = byteString.split("\\s");
        ByteBuffer buffer = ByteBuffer.allocate(split.length);
        for (String s : split) {
            buffer.put((byte) Integer.parseInt(s));
        }
        return buffer.array();
    }
}
