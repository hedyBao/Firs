package com.tomas.hellodemo.RedisUtils;

import com.tomas.hellodemo.entity.Permission;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisCacheForPermission {

    private Logger logger = LoggerFactory.getLogger(RedisCacheForPermission.class);


    @Autowired
    private JedisPool jedisPool;



    public Permission get(String key){
        Jedis jedis = jedisPool.getResource();
        try {


            String resultRedis = (String)jedis.get(key);
            Gson gsonV = new Gson();

            Permission result = gsonV.fromJson(resultRedis, Permission.class);

            return  result;

        }catch (Exception e){
            logger.error("get redis fail", e);

        }finally {
            jedis.close();
        }
        return null;
    }

    public void set(String key,Permission value){
        Jedis jedis = jedisPool.getResource();
        try{
            Gson gsonV= new Gson();
            jedis.set(key,gsonV.toJson(value));


        }catch (Exception e){
            logger.error("set redis fail", e);


        }finally {
            jedis.close();
        }

    }

    public Object del(String key){
        Jedis jedis = jedisPool.getResource();
        try{
            return jedis.del(key);

        }catch (Exception e ){
            logger.error("del redis fail", e);

        }finally {
            jedis.close();
        }
        return null;

    }
}
