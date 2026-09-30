package com.example.gesstudynotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.gesstudynotes.models.PastPaper;
import java.util.ArrayList;
import java.util.List;

public class PastPaperDao {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public PastPaperDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addPastPaper(PastPaper paper) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PAPER_SUBJECT, paper.getSubject());
        values.put(DatabaseHelper.COLUMN_PAPER_YEAR, paper.getYear());
        values.put(DatabaseHelper.COLUMN_PAPER_EXAM_TYPE, paper.getExamType());
        values.put(DatabaseHelper.COLUMN_PAPER_DOWNLOAD_URL, paper.getDownloadUrl());
        values.put(DatabaseHelper.COLUMN_PAPER_FILE_PATH, paper.getFilePath());
        values.put(DatabaseHelper.COLUMN_PAPER_IS_DOWNLOADED, paper.isDownloaded() ? 1 : 0);

        return database.insert(DatabaseHelper.TABLE_PAST_PAPERS, null, values);
    }

    public PastPaper getPaperById(int paperId) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PAST_PAPERS,
                null,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(paperId)},
                null,
                null,
                null
        );

        PastPaper paper = null;
        if (cursor.moveToFirst()) {
            paper = cursorToPaper(cursor);
        }
        cursor.close();
        return paper;
    }

    public List<PastPaper> getAllPastPapers() {
        database = dbHelper.getReadableDatabase();
        List<PastPaper> papers = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PAST_PAPERS,
                null,
                null,
                null,
                null,
                null,
                DatabaseHelper.COLUMN_PAPER_YEAR + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                papers.add(cursorToPaper(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return papers;
    }

    public List<PastPaper> getPapersBySubject(String subject) {
        database = dbHelper.getReadableDatabase();
        List<PastPaper> papers = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PAST_PAPERS,
                null,
                DatabaseHelper.COLUMN_PAPER_SUBJECT + "=?",
                new String[]{subject},
                null,
                null,
                DatabaseHelper.COLUMN_PAPER_YEAR + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                papers.add(cursorToPaper(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return papers;
    }

    public List<PastPaper> getPapersByYear(int year) {
        database = dbHelper.getReadableDatabase();
        List<PastPaper> papers = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PAST_PAPERS,
                null,
                DatabaseHelper.COLUMN_PAPER_YEAR + "=?",
                new String[]{String.valueOf(year)},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                papers.add(cursorToPaper(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return papers;
    }

    public boolean updatePastPaper(PastPaper paper) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PAPER_SUBJECT, paper.getSubject());
        values.put(DatabaseHelper.COLUMN_PAPER_YEAR, paper.getYear());
        values.put(DatabaseHelper.COLUMN_PAPER_EXAM_TYPE, paper.getExamType());
        values.put(DatabaseHelper.COLUMN_PAPER_DOWNLOAD_URL, paper.getDownloadUrl());
        values.put(DatabaseHelper.COLUMN_PAPER_FILE_PATH, paper.getFilePath());
        values.put(DatabaseHelper.COLUMN_PAPER_IS_DOWNLOADED, paper.isDownloaded() ? 1 : 0);

        return database.update(
                DatabaseHelper.TABLE_PAST_PAPERS,
                values,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(paper.getId())}
        ) > 0;
    }

    public boolean deletePastPaper(int paperId) {
        database = dbHelper.getWritableDatabase();
        return database.delete(
                DatabaseHelper.TABLE_PAST_PAPERS,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(paperId)}
        ) > 0;
    }

    private PastPaper cursorToPaper(Cursor cursor) {
        PastPaper paper = new PastPaper();
        paper.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
        paper.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAPER_SUBJECT)));
        paper.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAPER_YEAR)));
        paper.setExamType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAPER_EXAM_TYPE)));
        paper.setDownloadUrl(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAPER_DOWNLOAD_URL)));
        paper.setFilePath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAPER_FILE_PATH)));
        paper.setDownloaded(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAPER_IS_DOWNLOADED)) == 1);
        paper.setCreatedAt(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CREATED_AT)));
        return paper;
    }
}
