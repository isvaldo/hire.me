package com.shortener.repository;

import com.shortener.entities.Shortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by isvaldo on 26/03/16.
 */
@Repository
public class ShortenerRepository {

    @Autowired
    private RedisTemplate<String, Shortener> redisTemplate;

    public void save(final Shortener shortener){
        redisTemplate.opsForValue().set(shortener.getId(), shortener);
    }


    public Shortener findById(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<Shortener> findAll(){

        return redisTemplate.keys("*").stream()
                .map(this::findById)
                .collect(Collectors.toList());
    }

    public Integer countAll(){
        return redisTemplate.keys("*").size();
    }

}
