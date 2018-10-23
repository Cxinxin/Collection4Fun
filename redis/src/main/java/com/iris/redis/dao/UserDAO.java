package com.iris.redis.dao;

import com.iris.redis.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = "users")
public interface UserDAO extends JpaRepository<User,Integer>{

    @Cacheable(key = "#p0")
    User findByName(String name);

    @Override
    @CachePut(key = "#p0.name")
    User save(User user);

}
