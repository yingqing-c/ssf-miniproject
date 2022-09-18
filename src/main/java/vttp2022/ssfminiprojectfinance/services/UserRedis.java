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
        redisTemplate.opsForValue().set(userinfo.getEmail(), userinfo);
        User result = (User) redisTemplate.opsForValue().get(userinfo.getEmail());
        if (result != null)
            return 0;
        return 1;
    }

    @Override
    public User findByEmail(final String email) {
        User result = (User) redisTemplate.opsForValue().get(email);
        return result;
    }
}
