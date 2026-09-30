package com.example.gesstudynotes.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.adapters.NotesAdapter;
import com.example.gesstudynotes.models.Note;
import com.example.gesstudynotes.database.NoteDao;
import com.example.gesstudynotes.utils.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesListActivity extends AppCompatActivity {

    private EditText searchInput;
    private Spinner subjectSpinner, topicSpinner;
    private Button searchButton;
    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;
    private NoteDao noteDao;
    private List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        noteDao = new NoteDao(this);
        initViews();
        setupRecyclerView();
        setupSpinners();
        setupListeners();
        loadAllNotes();
    }

    private void initViews() {
        searchInput = findViewById(R.id.searchInput);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        topicSpinner = findViewById(R.id.topicSpinner);
        searchButton = findViewById(R.id.searchButton);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
    }

    private void setupRecyclerView() {
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesList, note -> onNoteClick(note));
        notesRecyclerView.setAdapter(notesAdapter);
    }

    private void setupSpinners() {
        // Subject Spinner
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Arrays.asList(Constants.GES_SUBJECTS)
        );
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(subjectAdapter);

        // Topic Spinner - Will be populated based on selected subject
        setupTopicSpinner();
    }

    private void setupTopicSpinner() {
        List<String> topics = Arrays.asList(
                "Topic 1", "Topic 2", "Topic 3", "Topic 4", "Topic 5"
        );
        ArrayAdapter<String> topicAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                topics
        );
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(topicAdapter);
    }

    private void setupListeners() {
        searchButton.setOnClickListener(v -> handleSearch());
    }

    private void handleSearch() {
        String subject = subjectSpinner.getSelectedItem().toString();
        String searchQuery = searchInput.getText().toString().trim();

        if (searchQuery.isEmpty() && subject.equals(Constants.GES_SUBJECTS[0])) {
            loadAllNotes();
        } else {
            searchNotes(subject, searchQuery);
        }
    }

    private void loadAllNotes() {
        notesList.clear();
        // Mock data - In real app, fetch from database
        notesList.add(new Note("Quadratic Equations", "Mathematics", "Algebra", "Content here...", "Teacher A"));
        notesList.add(new Note("Photosynthesis", "Integrated Science", "Biology", "Content here...", "Teacher B"));
        notesList.add(new Note("Colonial Period", "Social Studies", "History", "Content here...", "Teacher C"));
        notesAdapter.notifyDataSetChanged();
    }

    private void searchNotes(String subject, String query) {
        // Mock search - In real app, query database
        Toast.makeText(this, "Searching for " + query + " in " + subject, Toast.LENGTH_SHORT).show();
    }

    private void onNoteClick(Note note) {
        // Open note detail
        Toast.makeText(this, "Clicked: " + note.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
