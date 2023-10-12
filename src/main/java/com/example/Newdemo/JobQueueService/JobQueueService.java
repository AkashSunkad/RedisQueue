package com.example.Newdemo.JobQueueService;

import com.example.Newdemo.Job.Job;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JobQueueService {

    private static final String QUEUE_NAME = "jobQueue";

    @Autowired
    private JedisPool jedisPool;

    public Job enqueueJob(Job job) {
        try (Jedis jedis = jedisPool.getResource()) {
            String jobJson = new Gson().toJson(job);
            jedis.lpush(QUEUE_NAME, jobJson);
        }
        return job;
    }

    public Job dequeueJob() {
        try (Jedis jedis = jedisPool.getResource()) {
            String jobJson = jedis.rpop(QUEUE_NAME);
            return new Gson().fromJson(jobJson, Job.class);
        }
    }
}

