package model.translator.web.infra;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisManager {

    private JedisPool client = null;

    public RedisManager(){
        this.client = new JedisPool("localhost", 6379);
    }

    public void publishOnQueue(String data, String queue){
        JedisPool pool = this.client;
        try(Jedis jedis = pool.getResource()){
            jedis.rpush(queue, data);
        }
    }


    public void setData(String data, String key){
        JedisPool pool = this.client;
        try(Jedis jedis = pool.getResource()){
            jedis.set(key, data);
        }
    }

    public String getData(String key){
        JedisPool pool = this.client;
        try(Jedis jedis = pool.getResource()){
            return jedis.get(key);
        }
    }

}
