package com.example.lx.solarfragment.bean;

import java.io.Serializable;
import java.sql.Date;

public class Task implements Serializable{
    private int taskId;
    private int userId;
    private String taskName;

    public Task(String taskName, int dateTime, String taskState) {
        this.taskName = taskName;
        this.dateTime = dateTime;
        this.taskState = taskState;
    }

    private int dateTime;
    private String taskState;
    private int taskScore;
    public Task(String taskName, int dateTime) {
        this.taskName = taskName;
        this.dateTime = dateTime;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", taskName='" + taskName + '\'' +
                ", taskState='" + taskState + '\'' +
                ", dateTime=" + dateTime +
                ", taskScore=" + taskScore +
                '}';
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public int getDateTime() {
        return dateTime;
    }

    public void setDateTime(int dateTime) {
        this.dateTime = dateTime;
    }

    public int getTaskScore() {
        return taskScore;
    }

    public void setTaskScore(int taskScore) {
        this.taskScore = taskScore;
    }
}
