package com.example.gesstudynotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.gesstudynotes.models.StudyProgress;
import java.util.ArrayList;
import java.util.List;

public class ProgressDao {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public ProgressDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addProgress(StudyProgress progress) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PROGRESS_USER_ID, progress.getUserId());
        values.put(DatabaseHelper.COLUMN_PROGRESS_SUBJECT, progress.getSubject());
        values.put(DatabaseHelper.COLUMN_PROGRESS_TOPIC, progress.getTopic());
        values.put(DatabaseHelper.COLUMN_PROGRESS_STATUS, progress.getStatus());
        values.put(DatabaseHelper.COLUMN_PROGRESS_PERCENT, progress.getProgressPercent());
        values.put(DatabaseHelper.COLUMN_PROGRESS_LAST_STUDIED, progress.getLastStudied());

        return database.insert(DatabaseHelper.TABLE_STUDY_PROGRESS, null, values);
    }

    public StudyProgress getProgressById(int progressId) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_STUDY_PROGRESS,
                null,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(progressId)},
                null,
                null,
                null
        );

        StudyProgress progress = null;
        if (cursor.moveToFirst()) {
            progress = cursorToProgress(cursor);
        }
        cursor.close();
        return progress;
    }

    public List<StudyProgress> getUserProgress(int userId) {
        database = dbHelper.getReadableDatabase();
        List<StudyProgress> progressList = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_STUDY_PROGRESS,
                null,
                DatabaseHelper.COLUMN_PROGRESS_USER_ID + "=?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                DatabaseHelper.COLUMN_PROGRESS_LAST_STUDIED + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                progressList.add(cursorToProgress(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return progressList;
    }

    public List<StudyProgress> getProgressBySubject(int userId, String subject) {
        database = dbHelper.getReadableDatabase();
        List<StudyProgress> progressList = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_STUDY_PROGRESS,
                null,
                DatabaseHelper.COLUMN_PROGRESS_USER_ID + "=? AND " + DatabaseHelper.COLUMN_PROGRESS_SUBJECT + "=?",
                new String[]{String.valueOf(userId), subject},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                progressList.add(cursorToProgress(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return progressList;
    }

    public int getOverallProgress(int userId) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT AVG(" + DatabaseHelper.COLUMN_PROGRESS_PERCENT + ") as avg FROM " +
                DatabaseHelper.TABLE_STUDY_PROGRESS + " WHERE " +
                DatabaseHelper.COLUMN_PROGRESS_USER_ID + "=?",
                new String[]{String.valueOf(userId)}
        );

        int average = 0;
        if (cursor.moveToFirst()) {
            average = cursor.getInt(cursor.getColumnIndexOrThrow("avg"));
        }
        cursor.close();
        return average;
    }

    public boolean updateProgress(StudyProgress progress) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PROGRESS_STATUS, progress.getStatus());
        values.put(DatabaseHelper.COLUMN_PROGRESS_PERCENT, progress.getProgressPercent());
        values.put(DatabaseHelper.COLUMN_PROGRESS_LAST_STUDIED, System.currentTimeMillis());

        return database.update(
                DatabaseHelper.TABLE_STUDY_PROGRESS,
                values,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(progress.getId())}
        ) > 0;
    }

    public boolean deleteProgress(int progressId) {
        database = dbHelper.getWritableDatabase();
        return database.delete(
                DatabaseHelper.TABLE_STUDY_PROGRESS,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(progressId)}
        ) > 0;
    }

    private StudyProgress cursorToProgress(Cursor cursor) {
        StudyProgress progress = new StudyProgress();
        progress.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
        progress.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PROGRESS_USER_ID)));
        progress.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PROGRESS_SUBJECT)));
        progress.setTopic(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PROGRESS_TOPIC)));
        progress.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PROGRESS_STATUS)));
        progress.setProgressPercent(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PROGRESS_PERCENT)));
        progress.setLastStudied(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PROGRESS_LAST_STUDIED)));
        progress.setCreatedAt(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CREATED_AT)));
        return progress;
    }
}
