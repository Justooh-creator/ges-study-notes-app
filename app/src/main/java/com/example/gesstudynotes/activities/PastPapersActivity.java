package com.example.gesstudynotes.activities;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.adapters.PastPapersAdapter;
import com.example.gesstudynotes.models.PastPaper;
import com.example.gesstudynotes.utils.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PastPapersActivity extends AppCompatActivity {

    private Spinner subjectSpinner, yearSpinner, examTypeSpinner;
    private Button filterButton;
    private RecyclerView papersRecyclerView;
    private PastPapersAdapter papersAdapter;
    private List<PastPaper> papersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_papers);

        initViews();
        setupRecyclerView();
        setupSpinners();
        setupListeners();
        loadAllPapers();
    }

    private void initViews() {
        subjectSpinner = findViewById(R.id.subjectSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        examTypeSpinner = findViewById(R.id.examTypeSpinner);
        filterButton = findViewById(R.id.filterButton);
        papersRecyclerView = findViewById(R.id.papersRecyclerView);
    }

    private void setupRecyclerView() {
        papersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        papersList = new ArrayList<>();
        papersAdapter = new PastPapersAdapter(papersList, paper -> onPaperClick(paper));
        papersRecyclerView.setAdapter(papersAdapter);
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

        // Year Spinner (2015-2023)
        List<String> years = new ArrayList<>();
        years.add("All Years");
        for (int i = 2023; i >= 2015; i--) {
            years.add(String.valueOf(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                years
        );
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        // Exam Type Spinner
        List<String> examTypes = Arrays.asList("BECE", "Mock Exam", "Mid-Term", "End of Term");
        ArrayAdapter<String> examTypeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                examTypes
        );
        examTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        examTypeSpinner.setAdapter(examTypeAdapter);
    }

    private void setupListeners() {
        filterButton.setOnClickListener(v -> handleFilter());
    }

    private void handleFilter() {
        String subject = subjectSpinner.getSelectedItem().toString();
        String year = yearSpinner.getSelectedItem().toString();
        String examType = examTypeSpinner.getSelectedItem().toString();

        // Filter papers based on selection
        Toast.makeText(this, "Filtering: " + subject + " (" + year + ")", Toast.LENGTH_SHORT).show();
    }

    private void loadAllPapers() {
        papersList.clear();
        // Mock data - In real app, fetch from database/server
        papersList.add(new PastPaper("Mathematics", 2023, "BECE", "https://example.com/math2023.pdf"));
        papersList.add(new PastPaper("English Language", 2023, "BECE", "https://example.com/english2023.pdf"));
        papersList.add(new PastPaper("Integrated Science", 2022, "BECE", "https://example.com/science2022.pdf"));
        papersList.add(new PastPaper("Social Studies", 2022, "BECE", "https://example.com/social2022.pdf"));
        papersAdapter.notifyDataSetChanged();
    }

    private void onPaperClick(PastPaper paper) {
        Toast.makeText(this, paper.getSubject() + " (" + paper.getYear() + ")", Toast.LENGTH_SHORT).show();
    }
}
