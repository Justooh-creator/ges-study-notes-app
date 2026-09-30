package com.example.gesstudynotes.models;

import java.util.List;

public class Subject {
    private String name;
    private String code;
    private String description;
    private int topicsCount;
    private int notesCount;
    private int papersCount;
    private List<String> topics;

    public Subject() {
    }

    public Subject(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTopicsCount() { return topicsCount; }
    public void setTopicsCount(int topicsCount) { this.topicsCount = topicsCount; }

    public int getNotesCount() { return notesCount; }
    public void setNotesCount(int notesCount) { this.notesCount = notesCount; }

    public int getPapersCount() { return papersCount; }
    public void setPapersCount(int papersCount) { this.papersCount = papersCount; }

    public List<String> getTopics() { return topics; }
    public void setTopics(List<String> topics) { this.topics = topics; }
}
