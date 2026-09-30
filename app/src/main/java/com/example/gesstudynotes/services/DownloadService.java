package com.example.gesstudynotes.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadService extends Service {

    private static final String TAG = "DownloadService";
    private boolean isDownloading = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Download Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String downloadUrl = intent.getStringExtra("downloadUrl");
            String fileName = intent.getStringExtra("fileName");
            Log.d(TAG, "Download started: " + fileName);
            downloadFile(downloadUrl, fileName);
        }
        return START_STICKY;
    }

    private void downloadFile(String urlString, String fileName) {
        if (isDownloading) {
            Log.d(TAG, "Download already in progress");
            return;
        }

        isDownloading = true;
        new Thread(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    File downloadsDir = getExternalFilesDir("notes");
                    File file = new File(downloadsDir, fileName);
                    FileOutputStream outputStream = new FileOutputStream(file);

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    outputStream.close();
                    inputStream.close();
                    Log.d(TAG, "Download completed: " + fileName);
                } else {
                    Log.e(TAG, "Download failed: HTTP " + connection.getResponseCode());
                }
                connection.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "Download error: " + e.getMessage());
            } finally {
                isDownloading = false;
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
        Log.d(TAG, "Download Service Destroyed");
    }
}
