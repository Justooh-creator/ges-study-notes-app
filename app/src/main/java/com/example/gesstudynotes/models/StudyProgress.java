package com.example.gesstudynotes.models;

public class StudyProgress {
    public static final String STATUS_NOT_STARTED = "not_started";
    public static final String STATUS_IN_PROGRESS = "in_progress";
    public static final String STATUS_COMPLETED = "completed";
    public static final String STATUS_REVIEWED = "reviewed";

    private int id;
    private int userId;
    private String subject;
    private String topic;
    private String status;
    private int progressPercent;
    private long lastStudied;
    private long createdAt;

    public StudyProgress() {
    }

    public StudyProgress(int userId, String subject, String topic) {
        this.userId = userId;
        this.subject = subject;
        this.topic = topic;
        this.status = STATUS_NOT_STARTED;
        this.progressPercent = 0;
        this.createdAt = System.currentTimeMillis();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getProgressPercent() { return progressPercent; }
    public void setProgressPercent(int progressPercent) { this.progressPercent = progressPercent; }

    public long getLastStudied() { return lastStudied; }
    public void setLastStudied(long lastStudied) { this.lastStudied = lastStudied; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
