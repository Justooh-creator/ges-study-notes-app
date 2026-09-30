package com.example.gesstudynotes.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.example.gesstudynotes.database.NoteDao;
import com.example.gesstudynotes.models.Note;
import java.util.List;

public class SyncService extends Service {

    private static final String TAG = "SyncService";
    private NoteDao noteDao;
    private boolean isSyncing = false;

    @Override
    public void onCreate() {
        super.onCreate();
        noteDao = new NoteDao(this);
        Log.d(TAG, "Sync Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Sync Service Started");
        syncNotes();
        return START_STICKY;
    }

    private void syncNotes() {
        if (isSyncing) {
            Log.d(TAG, "Sync already in progress");
            return;
        }

        isSyncing = true;
        new Thread(() -> {
            try {
                // Fetch all notes from local database
                List<Note> notes = noteDao.getAllNotes();
                Log.d(TAG, "Syncing " + notes.size() + " notes");

                // In real app, sync with backend server here
                // POST to /api/notes endpoint

                Thread.sleep(2000); // Simulate sync delay
                Log.d(TAG, "Sync completed successfully");
            } catch (InterruptedException e) {
                Log.e(TAG, "Sync interrupted: " + e.getMessage());
            } finally {
                isSyncing = false;
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Sync Service Destroyed");
    }
}
