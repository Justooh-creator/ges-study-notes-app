package com.example.gesstudynotes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.adapters.NotesAdapter;
import com.example.gesstudynotes.models.Note;
import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;
    private List<Note> notesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        initViews(view);
        setupRecyclerView();
        loadNotes();
        return view;
    }

    private void initViews(View view) {
        notesRecyclerView = view.findViewById(R.id.notesRecyclerView);
    }

    private void setupRecyclerView() {
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(notesList, note -> onNoteClick(note));
        notesRecyclerView.setAdapter(notesAdapter);
    }

    private void loadNotes() {
        // Load notes from database
        notesList.add(new Note("Quadratic Equations", "Mathematics", "Algebra", "Content...", "Teacher A"));
        notesList.add(new Note("Photosynthesis", "Integrated Science", "Biology", "Content...", "Teacher B"));
        notesAdapter.notifyDataSetChanged();
    }

    private void onNoteClick(Note note) {
        // Handle note click
    }
}
