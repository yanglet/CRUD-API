package com.example.crud_kotlin.application.common

import com.example.crud_kotlin.application.common.log.Log
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Service
class DistributedLockService(
    private val redissonClient: RedissonClient
) : Log {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun <T> doDistributedLock(lockName: String, function: () -> T) {
        val lock = redissonClient.getLock(lockName)

        try {
            val available = lock.tryLock(5, 3, TimeUnit.SECONDS)

            if (available) {
                function.invoke()
            }
        } catch (e: InterruptedException) {
            log.error("InterruptedException 발생!", e)
            throw RuntimeException("InterruptedException")
        } finally {
            lock.unlock()
        }
    }
}