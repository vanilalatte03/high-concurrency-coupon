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
        // 1. 데이터 저장하기 (key: "test", value: "hello")
        redisTemplate.opsForValue().set("test", "hello");

        // 2. 데이터 조회하기
        String value = redisTemplate.opsForValue().get("test");

        // 3. 출력해서 확인
        System.out.println("Redis에서 가져온 값: " + value);

        // 검증 (값이 hello여야 성공)
        if (!"hello".equals(value)) {
            throw new RuntimeException("Redis 연결 실패!");
        }
    }
}
