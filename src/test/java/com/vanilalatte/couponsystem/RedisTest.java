package com.vanilalatte.couponsystem;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void redisConnectionTest() {
        redisTemplate.opsForValue().set("test", "hello");

        String value = redisTemplate.opsForValue().get("test");

        System.out.println("Redis에서 가져온 값: " + value);

        if (!"hello".equals(value)) {
            throw new RuntimeException("Redis 연결 실패!");
        }
    }
}
