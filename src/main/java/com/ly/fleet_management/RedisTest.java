package com.ly.fleet_management;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().set("testKey", "Hello, Redis!");
        String value = (String) redisTemplate.opsForValue().get("testKey");
        System.out.println("Value from Redis: " + value);
    }
}
