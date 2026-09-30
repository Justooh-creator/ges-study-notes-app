package com.example.gesstudynotes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ges_study_notes.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_NOTES = "notes";
    public static final String TABLE_PAST_PAPERS = "past_papers";
    public static final String TABLE_STUDY_PROGRESS = "study_progress";
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String TABLE_QUIZ_ANSWERS = "quiz_answers";

    // Common Columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    // Users Table Columns
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_CLASS = "student_class";
    public static final String COLUMN_USER_SCHOOL = "school_name";

    // Notes Table Columns
    public static final String COLUMN_NOTE_TITLE = "title";
    public static final String COLUMN_NOTE_SUBJECT = "subject";
    public static final String COLUMN_NOTE_TOPIC = "topic";
    public static final String COLUMN_NOTE_CONTENT = "content";
    public static final String COLUMN_NOTE_AUTHOR = "author";
    public static final String COLUMN_NOTE_FILE_PATH = "file_path";
    public static final String COLUMN_NOTE_IS_DOWNLOADED = "is_downloaded";

    // Past Papers Table Columns
    public static final String COLUMN_PAPER_SUBJECT = "subject";
    public static final String COLUMN_PAPER_YEAR = "year";
    public static final String COLUMN_PAPER_EXAM_TYPE = "exam_type";
    public static final String COLUMN_PAPER_FILE_PATH = "file_path";
    public static final String COLUMN_PAPER_DOWNLOAD_URL = "download_url";
    public static final String COLUMN_PAPER_IS_DOWNLOADED = "is_downloaded";

    // Study Progress Table Columns
    public static final String COLUMN_PROGRESS_USER_ID = "user_id";
    public static final String COLUMN_PROGRESS_SUBJECT = "subject";
    public static final String COLUMN_PROGRESS_TOPIC = "topic";
    public static final String COLUMN_PROGRESS_STATUS = "status";
    public static final String COLUMN_PROGRESS_PERCENT = "progress_percent";
    public static final String COLUMN_PROGRESS_LAST_STUDIED = "last_studied";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users Table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT NOT NULL, " +
                COLUMN_USER_EMAIL + " TEXT UNIQUE NOT NULL, " +
                COLUMN_USER_PHONE + " TEXT, " +
                COLUMN_USER_CLASS + " TEXT, " +
                COLUMN_USER_SCHOOL + " TEXT, " +
                COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                COLUMN_UPDATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        db.execSQL(createUsersTable);

        // Create Notes Table
        String createNotesTable = "CREATE TABLE " + TABLE_NOTES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTE_TITLE + " TEXT NOT NULL, " +
                COLUMN_NOTE_SUBJECT + " TEXT NOT NULL, " +
                COLUMN_NOTE_TOPIC + " TEXT NOT NULL, " +
                COLUMN_NOTE_CONTENT + " TEXT, " +
                COLUMN_NOTE_AUTHOR + " TEXT, " +
                COLUMN_NOTE_FILE_PATH + " TEXT, " +
                COLUMN_NOTE_IS_DOWNLOADED + " INTEGER DEFAULT 0, " +
                COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                COLUMN_UPDATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        db.execSQL(createNotesTable);

        // Create Past Papers Table
        String createPapersTable = "CREATE TABLE " + TABLE_PAST_PAPERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAPER_SUBJECT + " TEXT NOT NULL, " +
                COLUMN_PAPER_YEAR + " INTEGER NOT NULL, " +
                COLUMN_PAPER_EXAM_TYPE + " TEXT, " +
                COLUMN_PAPER_FILE_PATH + " TEXT, " +
                COLUMN_PAPER_DOWNLOAD_URL + " TEXT, " +
                COLUMN_PAPER_IS_DOWNLOADED + " INTEGER DEFAULT 0, " +
                COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        db.execSQL(createPapersTable);

        // Create Study Progress Table
        String createProgressTable = "CREATE TABLE " + TABLE_STUDY_PROGRESS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROGRESS_USER_ID + " INTEGER NOT NULL, " +
                COLUMN_PROGRESS_SUBJECT + " TEXT NOT NULL, " +
                COLUMN_PROGRESS_TOPIC + " TEXT NOT NULL, " +
                COLUMN_PROGRESS_STATUS + " TEXT DEFAULT 'not_started', " +
                COLUMN_PROGRESS_PERCENT + " INTEGER DEFAULT 0, " +
                COLUMN_PROGRESS_LAST_STUDIED + " TIMESTAMP, " +
                COLUMN_CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        db.execSQL(createProgressTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAST_PAPERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDY_PROGRESS);

        // Recreate tables
        onCreate(db);
    }
}
