package com.example.Newdemo.JobController;

import com.example.Newdemo.Job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class JobController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String QUEUE_NAME = "jobQueue";

    @PostMapping("/jobs")
    public void addJob(@RequestBody Job job) {
        redisTemplate.opsForList().leftPush(QUEUE_NAME, job);
    }

    @GetMapping("/jobs")
    public Job getJob() {
        return (Job) redisTemplate.opsForList().rightPop(QUEUE_NAME);
    }
}
