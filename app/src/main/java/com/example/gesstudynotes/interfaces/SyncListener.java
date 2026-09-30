package com.example.gesstudynotes.interfaces;

public interface SyncListener {
    void onSyncStart();
    void onSyncProgress(int progress);
    void onSyncComplete();
    void onSyncError(String error);
}
