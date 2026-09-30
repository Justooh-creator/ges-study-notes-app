package com.example.gesstudynotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gesstudynotes.activities.LoginActivity;
import com.example.gesstudynotes.activities.HomeActivity;
import com.example.gesstudynotes.utils.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity {

    private SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefsHelper = new SharedPreferencesHelper(this);

        // Check if user is logged in
        if (prefsHelper.isUserLoggedIn()) {
            // Go to home
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            // Go to login
            startActivity(new Intent(this, LoginActivity.class));
        }

        // Close splash screen
        finish();
    }
}
