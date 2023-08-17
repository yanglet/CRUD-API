package com.example.crud_java.application.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistributedLockService {
    private final RedissonClient redissonClient;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doDistributedLock(DistributedLockStrategy strategy, String name) {
        RLock lock = redissonClient.getLock(name);

        try {
            boolean available = lock.tryLock(5, 3, TimeUnit.SECONDS);

            if (available) {
                strategy.doLock();
            }

        } catch (InterruptedException e) {
            log.error("InterruptedException 발생!", e);
            throw new RuntimeException("InterruptedException!");
        } finally {
            lock.unlock();
        }
    }
}
