package com.example.gesstudynotes.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String studentClass;  // e.g., "B6", "B7", etc.
    private String schoolName;
    private String profilePhotoPath;
    private long createdAt;
    private long updatedAt;

    public User() {
    }

    public User(String name, String email, String phoneNumber, String studentClass, String schoolName) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studentClass = studentClass;
        this.schoolName = schoolName;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getStudentClass() { return studentClass; }
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getProfilePhotoPath() { return profilePhotoPath; }
    public void setProfilePhotoPath(String profilePhotoPath) { this.profilePhotoPath = profilePhotoPath; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }
}
