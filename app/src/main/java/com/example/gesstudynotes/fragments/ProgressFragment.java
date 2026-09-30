package com.example.gesstudynotes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.gesstudynotes.R;

public class ProgressFragment extends Fragment {

    private ProgressBar overallProgressBar;
    private TextView progressText, topicsCompletedText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        initViews(view);
        loadProgress();
        return view;
    }

    private void initViews(View view) {
        overallProgressBar = view.findViewById(R.id.overallProgressBar);
        progressText = view.findViewById(R.id.progressText);
        topicsCompletedText = view.findViewById(R.id.topicsCompletedText);
    }

    private void loadProgress() {
        // Load progress data from database
        int progress = 45; // Mock value
        overallProgressBar.setProgress(progress);
        progressText.setText(progress + "% Complete");
        topicsCompletedText.setText("22 topics completed out of 50");
    }
}
