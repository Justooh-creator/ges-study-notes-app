package com.example.gesstudynotes.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.models.Note;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView titleText, authorText, subjectText, topicText, contentText;
    private Button downloadButton, markStudiedButton, shareButton;
    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        initViews();
        setupListeners();
        loadNoteDetails();
    }

    private void initViews() {
        titleText = findViewById(R.id.titleText);
        authorText = findViewById(R.id.authorText);
        subjectText = findViewById(R.id.subjectText);
        topicText = findViewById(R.id.topicText);
        contentText = findViewById(R.id.contentText);
        downloadButton = findViewById(R.id.downloadButton);
        markStudiedButton = findViewById(R.id.markStudiedButton);
        shareButton = findViewById(R.id.shareButton);
    }

    private void setupListeners() {
        downloadButton.setOnClickListener(v -> handleDownload());
        markStudiedButton.setOnClickListener(v -> handleMarkStudied());
        shareButton.setOnClickListener(v -> handleShare());
    }

    private void loadNoteDetails() {
        // Mock data - In real app, get from intent or database
        currentNote = new Note(
                "Quadratic Equations",
                "Mathematics",
                "Algebra",
                "A quadratic equation is a polynomial equation of the second degree. " +
                "The general form is ax² + bx + c = 0 where a ≠ 0. " +
                "Solutions can be found using the quadratic formula, factoring, or completing the square.",
                "Teacher A"
        );

        titleText.setText(currentNote.getTitle());
        authorText.setText("By: " + currentNote.getAuthor());
        subjectText.setText("Subject: " + currentNote.getSubject());
        topicText.setText("Topic: " + currentNote.getTopic());
        contentText.setText(currentNote.getContent());
    }

    private void handleDownload() {
        if (currentNote.isDownloaded()) {
            Toast.makeText(this, "Note already downloaded", Toast.LENGTH_SHORT).show();
        } else {
            currentNote.setDownloaded(true);
            downloadButton.setText("Downloaded");
            downloadButton.setEnabled(false);
            Toast.makeText(this, "Note downloaded successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleMarkStudied() {
        Toast.makeText(this, "Marked as studied", Toast.LENGTH_SHORT).show();
    }

    private void handleShare() {
        Toast.makeText(this, "Share note with classmates", Toast.LENGTH_SHORT).show();
    }
}
