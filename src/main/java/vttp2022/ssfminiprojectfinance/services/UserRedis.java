package vttp2022.ssfminiprojectfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp2022.ssfminiprojectfinance.models.User;

@Service
public class UserRedis implements UserRepo {
    
    @Autowired
    @Qualifier("redislab")
    RedisTemplate<String, User> redisTemplate;

    @Override
    public int save(final User userinfo) {
        redisTemplate.opsForValue().set(userinfo.getUsername(), userinfo);
        User result = (User) redisTemplate.opsForValue().get(userinfo.getUsername());
        if (result != null)
            return 0;
        return 1;
    }

    @Override
    public User findByUsername(final String username) {
        User result = (User) redisTemplate.opsForValue().get(username);
        return result;
    }
}
