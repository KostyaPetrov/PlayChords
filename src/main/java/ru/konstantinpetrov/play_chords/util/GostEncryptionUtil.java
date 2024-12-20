package ru.konstantinpetrov.play_chords.util;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class GostEncryptionUtil {

    private static final String ALGORITHM = "GOST28147";
    private static final String MODE_PADDING = "GOST28147/ECB/PKCS5Padding";
    // Можно использовать разные режимы и схемы дополнения, но GOST обычно поддерживает ECB.
    // Рекомендуется рассмотреть использование более надежных режимов (например, CBC) и IV.

    // Ключ должен быть 256 бит (32 байта).
    private byte[] keyBytes;

    public GostEncryptionUtil(byte[] keyBytes) {
        this.keyBytes = keyBytes;
    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(MODE_PADDING, "BC");
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
        return java.util.Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(MODE_PADDING, "BC");
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = java.util.Base64.getDecoder().decode(encryptedData);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8");
    }
}
