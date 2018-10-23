package com.iris.redis.service;

import com.iris.redis.dao.UserDAO;
import com.iris.redis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl{

    @Autowired
    UserDAO userdao;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Autowired
    RedisService redisService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    public User findByName(String name) {
        String key="name_"+name;
        ValueOperations <Object, Object> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user= (User) operations.get(key);
            LOGGER.info("UserServiceImpl.findByName() : 从缓存中获取了用户 >> " + user.toString());
            return user;

        }
        //从db获取
        User user=userdao.findByName(name);

        // 插入缓存  设置的失效时间是 10 s
        operations.set(key, user, 10, TimeUnit.MINUTES);
        LOGGER.info("UserServiceImpl.findByName() : 用户插入缓存 >> " + user.toString());
        return user;
    }


    public User save(User user){
        return userdao.save(user);
    }
}
