package com.example.gesstudynotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.gesstudynotes.models.Note;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public NoteDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addNote(Note note) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOTE_TITLE, note.getTitle());
        values.put(DatabaseHelper.COLUMN_NOTE_SUBJECT, note.getSubject());
        values.put(DatabaseHelper.COLUMN_NOTE_TOPIC, note.getTopic());
        values.put(DatabaseHelper.COLUMN_NOTE_CONTENT, note.getContent());
        values.put(DatabaseHelper.COLUMN_NOTE_AUTHOR, note.getAuthor());
        values.put(DatabaseHelper.COLUMN_NOTE_FILE_PATH, note.getFilePath());
        values.put(DatabaseHelper.COLUMN_NOTE_IS_DOWNLOADED, note.isDownloaded() ? 1 : 0);

        return database.insert(DatabaseHelper.TABLE_NOTES, null, values);
    }

    public Note getNoteById(int noteId) {
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_NOTES,
                null,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(noteId)},
                null,
                null,
                null
        );

        Note note = null;
        if (cursor.moveToFirst()) {
            note = cursorToNote(cursor);
        }
        cursor.close();
        return note;
    }

    public List<Note> getAllNotes() {
        database = dbHelper.getReadableDatabase();
        List<Note> notes = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_NOTES,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                notes.add(cursorToNote(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    public List<Note> getNotesBySubject(String subject) {
        database = dbHelper.getReadableDatabase();
        List<Note> notes = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_NOTES,
                null,
                DatabaseHelper.COLUMN_NOTE_SUBJECT + "=?",
                new String[]{subject},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                notes.add(cursorToNote(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    public List<Note> searchNotes(String query) {
        database = dbHelper.getReadableDatabase();
        List<Note> notes = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_NOTES,
                null,
                DatabaseHelper.COLUMN_NOTE_TITLE + " LIKE ? OR " +
                DatabaseHelper.COLUMN_NOTE_CONTENT + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                notes.add(cursorToNote(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    public boolean updateNote(Note note) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOTE_TITLE, note.getTitle());
        values.put(DatabaseHelper.COLUMN_NOTE_SUBJECT, note.getSubject());
        values.put(DatabaseHelper.COLUMN_NOTE_TOPIC, note.getTopic());
        values.put(DatabaseHelper.COLUMN_NOTE_CONTENT, note.getContent());
        values.put(DatabaseHelper.COLUMN_NOTE_AUTHOR, note.getAuthor());
        values.put(DatabaseHelper.COLUMN_NOTE_IS_DOWNLOADED, note.isDownloaded() ? 1 : 0);
        values.put(DatabaseHelper.COLUMN_UPDATED_AT, System.currentTimeMillis());

        return database.update(
                DatabaseHelper.TABLE_NOTES,
                values,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(note.getId())}
        ) > 0;
    }

    public boolean deleteNote(int noteId) {
        database = dbHelper.getWritableDatabase();
        return database.delete(
                DatabaseHelper.TABLE_NOTES,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(noteId)}
        ) > 0;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE_TITLE)));
        note.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE_SUBJECT)));
        note.setTopic(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE_TOPIC)));
        note.setContent(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE_CONTENT)));
        note.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE_AUTHOR)));
        note.setFilePath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE_FILE_PATH)));
        note.setDownloaded(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE_IS_DOWNLOADED)) == 1);
        note.setCreatedAt(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CREATED_AT)));
        note.setUpdatedAt(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_UPDATED_AT)));
        return note;
    }
}
