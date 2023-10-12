package com.example.Newdemo.Job;

import java.io.Serializable;

public class Job implements Serializable {
    private String id;
    private String task;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}

