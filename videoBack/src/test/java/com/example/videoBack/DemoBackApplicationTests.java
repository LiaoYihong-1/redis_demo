package com.example.videoBack;

import com.example.videoBack.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoBackApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void contextLoads() throws JsonProcessingException {
        List<String> l = new ArrayList();
        l.add("as");
        l.add("2");
        redisTemplate.opsForHash().put("test","permissions",mapper.writeValueAsString(l));
        String read = (String) redisTemplate.opsForHash().get("test","permissions");
        List<String> result = mapper.readValue(read,ArrayList.class);
        System.out.println(result);
    }

}
