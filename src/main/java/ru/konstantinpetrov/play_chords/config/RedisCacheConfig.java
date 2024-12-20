package ru.konstantinpetrov.play_chords.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
public class RedisCacheConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Пример с LettuceConnectionFactory (по умолчанию Spring Boot Starter Data Redis использует Lettuce)
        LettuceConnectionFactory factory = new LettuceConnectionFactory("localhost", 6379);
        return factory;
    }
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // Настроим конфигурацию кэша (опции: префиксы, время жизни и т.д.)
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60)) // время жизни кэша 60 минут
                .prefixCacheNameWith("myapp:")    // префикс для ключей в Redis
                .disableCachingNullValues();      // не кэшировать null

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)
                .build();
    }
}
