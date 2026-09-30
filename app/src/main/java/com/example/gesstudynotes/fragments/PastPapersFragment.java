package com.example.gesstudynotes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.adapters.PastPapersAdapter;
import com.example.gesstudynotes.models.PastPaper;
import java.util.ArrayList;
import java.util.List;

public class PastPapersFragment extends Fragment {

    private RecyclerView papersRecyclerView;
    private PastPapersAdapter papersAdapter;
    private List<PastPaper> papersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_papers, container, false);
        initViews(view);
        setupRecyclerView();
        loadPapers();
        return view;
    }

    private void initViews(View view) {
        papersRecyclerView = view.findViewById(R.id.papersRecyclerView);
    }

    private void setupRecyclerView() {
        papersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        papersList = new ArrayList<>();
        papersAdapter = new PastPapersAdapter(papersList, paper -> onPaperClick(paper));
        papersRecyclerView.setAdapter(papersAdapter);
    }

    private void loadPapers() {
        // Load papers from database
        papersList.add(new PastPaper("Mathematics", 2023, "BECE", "https://example.com/math2023.pdf"));
        papersList.add(new PastPaper("English Language", 2023, "BECE", "https://example.com/english2023.pdf"));
        papersAdapter.notifyDataSetChanged();
    }

    private void onPaperClick(PastPaper paper) {
        // Handle paper click
    }
}
