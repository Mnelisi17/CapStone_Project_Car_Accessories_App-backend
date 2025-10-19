package za.co.cput.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;

    private static final String SECRET_KEY = "MySecretKey12345"; // 16 bytes for AES-128

    private final SecretKey secretKey;

    public EncryptionService() {
        this.secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    }

    public String encrypt(String plainText) {
        try {
            if (plainText == null || plainText.isEmpty()) {
                return plainText;
            }

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);

            byte[] iv = new byte[GCM_IV_LENGTH];
            new SecureRandom().nextBytes(iv);

            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

            byte[] encryptedData = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            byte[] encryptedWithIv = new byte[GCM_IV_LENGTH + encryptedData.length];
            System.arraycopy(iv, 0, encryptedWithIv, 0, GCM_IV_LENGTH);
            System.arraycopy(encryptedData, 0, encryptedWithIv, GCM_IV_LENGTH, encryptedData.length);

            return Base64.getEncoder().encodeToString(encryptedWithIv);

        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    public String decrypt(String encryptedText) {
        try {
            if (encryptedText == null || encryptedText.isEmpty()) {
                return encryptedText;
            }

            byte[] encryptedWithIv = Base64.getDecoder().decode(encryptedText);

            byte[] iv = new byte[GCM_IV_LENGTH];
            System.arraycopy(encryptedWithIv, 0, iv, 0, GCM_IV_LENGTH);

            byte[] encryptedData = new byte[encryptedWithIv.length - GCM_IV_LENGTH];
            System.arraycopy(encryptedWithIv, GCM_IV_LENGTH, encryptedData, 0, encryptedData.length);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

            byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }

    public String encryptSensitiveField(String value) {
        if (value == null) {
            return null;
        }
        return encrypt(value);
    }

    public String decryptSensitiveField(String encryptedValue) {
        if (encryptedValue == null) {
            return null;
        }
        return decrypt(encryptedValue);
    }

    public static String generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(256); // AES-256
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }
}