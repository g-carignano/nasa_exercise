package com.carignano.nasa_exercise.config;

import com.carignano.nasa_exercise.util.cache.LoggingCaffeineCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager delegate = new CaffeineCacheManager();
        delegate.setCaffeine(caffeine);

        return new CacheManager() {
            @Override
            public Cache getCache(String name) {
                Cache cache = delegate.getCache(name);
                if (cache == null) return null;
                return new LoggingCaffeineCache((CaffeineCache) cache);
            }

            @Override
            public Collection<String> getCacheNames() {
                return delegate.getCacheNames();
            }
        };
    }

}
