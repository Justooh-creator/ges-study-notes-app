package com.example.gesstudynotes.models;

public class PastPaper {
    private int id;
    private String subject;
    private int year;
    private String examType;  // e.g., "BECE", "Mock", etc.
    private String filePath;
    private String downloadUrl;
    private boolean isDownloaded;
    private long createdAt;

    public PastPaper() {
    }

    public PastPaper(String subject, int year, String examType, String downloadUrl) {
        this.subject = subject;
        this.year = year;
        this.examType = examType;
        this.downloadUrl = downloadUrl;
        this.isDownloaded = false;
        this.createdAt = System.currentTimeMillis();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }

    public boolean isDownloaded() { return isDownloaded; }
    public void setDownloaded(boolean downloaded) { isDownloaded = downloaded; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
