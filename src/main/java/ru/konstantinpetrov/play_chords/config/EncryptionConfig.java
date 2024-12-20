package ru.konstantinpetrov.play_chords.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.konstantinpetrov.play_chords.util.GostEncryptionUtil;

import java.util.Base64;

@Configuration
public class EncryptionConfig {
    @Value("${encryption.key.gost}")
    private String gostKeyBase64;

    @Bean
    public GostEncryptionUtil gostEncryptionUtil() {
        byte[] keyBytes = Base64.getDecoder().decode(gostKeyBase64);
        return new GostEncryptionUtil(keyBytes);
    }
}
