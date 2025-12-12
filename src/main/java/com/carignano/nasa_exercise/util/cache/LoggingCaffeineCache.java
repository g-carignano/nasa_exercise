package com.carignano.nasa_exercise.util.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.concurrent.Callable;

@Slf4j
public class LoggingCaffeineCache implements Cache {

    private final CaffeineCache delegate;

    public LoggingCaffeineCache(CaffeineCache delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public Object getNativeCache() {
        return delegate.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper value = delegate.get(key);
        logAccess(key, value != null);
        return value;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        T value = delegate.get(key, type);
        logAccess(key, value != null);
        return value;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        boolean exists = delegate.get(key) != null;
        T value;
        try {
            value = delegate.get(key, valueLoader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logAccess(key, exists);
        return value;
    }

    @Override
    public void put(Object key, Object value) {
        delegate.put(key, value);
        logConsole("PUT", key);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper existing = delegate.putIfAbsent(key, value);
        if (existing == null) {
            logConsole("PUT", key);
        } else {
            logConsole("ALREADY PRESENT", key);
        }
        return existing;
    }

    @Override
    public void evict(Object key) {
        delegate.evict(key);
        logConsole("EVICT", key);
    }

    @Override
    public void clear() {
        delegate.clear();
        log.info("Cache CLEARED cache={}", getName());
    }

    private void logAccess(Object key, boolean hit) {
        String status = hit ? "HIT" : "MISS";
        log.info("Cache {} key = {} in cache = {}", status, key, getName());
    }

    private void logConsole(String action, Object key) {
        log.info("Cache {} key={} in cache={}", action, key, getName());
    }
}
