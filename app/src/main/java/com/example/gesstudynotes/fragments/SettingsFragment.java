package com.example.gesstudynotes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.gesstudynotes.R;

public class SettingsFragment extends Fragment {

    private Button logoutButton, clearCacheButton, aboutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initViews(view);
        setupListeners();
        return view;
    }

    private void initViews(View view) {
        logoutButton = view.findViewById(R.id.logoutButton);
        clearCacheButton = view.findViewById(R.id.clearCacheButton);
        aboutButton = view.findViewById(R.id.aboutButton);
    }

    private void setupListeners() {
        logoutButton.setOnClickListener(v -> handleLogout());
        clearCacheButton.setOnClickListener(v -> handleClearCache());
        aboutButton.setOnClickListener(v -> showAbout());
    }

    private void handleLogout() {
        Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
    }

    private void handleClearCache() {
        Toast.makeText(getContext(), "Cache cleared", Toast.LENGTH_SHORT).show();
    }

    private void showAbout() {
        Toast.makeText(getContext(), "GES Study Notes v1.0.0", Toast.LENGTH_SHORT).show();
    }
}
