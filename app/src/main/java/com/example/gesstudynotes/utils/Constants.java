package com.example.gesstudynotes.utils;

public class Constants {
    // API Base URL
    public static final String API_BASE_URL = "https://api.gesstudynotes.com/api/";

    // SharedPreferences Keys
    public static final String PREF_USER_ID = "user_id";
    public static final String PREF_USER_NAME = "user_name";
    public static final String PREF_USER_EMAIL = "user_email";
    public static final String PREF_USER_CLASS = "user_class";
    public static final String PREF_IS_LOGGED_IN = "is_logged_in";
    public static final String PREF_AUTH_TOKEN = "auth_token";

    // File Paths
    public static final String NOTES_DIRECTORY = "notes";
    public static final String PAPERS_DIRECTORY = "past_papers";
    public static final String CACHE_DIRECTORY = "cache";

    // GES Subjects
    public static final String[] GES_SUBJECTS = {
            "English Language",
            "Mathematics",
            "Integrated Science",
            "Social Studies",
            "Religious and Moral Education",
            "Ghanaian Language",
            "Career Technology",
            "Computing",
            "Creative Arts and Design",
            "French"
    };

    // Class Levels
    public static final String[] CLASSES = {
            "Basic 6",
            "Basic 7",
            "Basic 8",
            "Basic 9"
    };

    // Notification Constants
    public static final int NOTIFICATION_ID_DOWNLOAD = 1001;
    public static final int NOTIFICATION_ID_SYNC = 1002;
    public static final String NOTIFICATION_CHANNEL_ID = "ges_study_notes_channel";

    // Time Constants (in milliseconds)
    public static final long SYNC_INTERVAL = 60 * 60 * 1000; // 1 hour
    public static final long CACHE_DURATION = 24 * 60 * 60 * 1000; // 24 hours
}
