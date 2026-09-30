package com.example.gesstudynotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.adapters.HomePagerAdapter;
import com.example.gesstudynotes.utils.SharedPreferencesHelper;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private Button logoutButton;
    private TextView welcomeText;
    private SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prefsHelper = new SharedPreferencesHelper(this);
        initViews();
        setupViewPager();
        setupListeners();
        updateWelcomeText();
    }

    private void initViews() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        logoutButton = findViewById(R.id.logoutButton);
        welcomeText = findViewById(R.id.welcomeText);
    }

    private void setupViewPager() {
        HomePagerAdapter adapter = new HomePagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Notes");
                    break;
                case 1:
                    tab.setText("Past Papers");
                    break;
                case 2:
                    tab.setText("Progress");
                    break;
                case 3:
                    tab.setText("Settings");
                    break;
            }
        }).attach();
    }

    private void setupListeners() {
        logoutButton.setOnClickListener(v -> handleLogout());
    }

    private void updateWelcomeText() {
        String userName = prefsHelper.getUserName();
        welcomeText.setText("Welcome, " + userName + "!");
    }

    private void handleLogout() {
        prefsHelper.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
