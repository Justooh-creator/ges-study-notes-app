package com.example.gesstudynotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gesstudynotes.R;
import com.example.gesstudynotes.utils.SharedPreferencesHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton, signupToggle;
    private TextView signupPrompt;
    private SharedPreferencesHelper prefsHelper;
    private boolean isSignUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefsHelper = new SharedPreferencesHelper(this);
        initViews();
        setupListeners();
    }

    private void initViews() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        signupToggle = findViewById(R.id.signupToggle);
        signupPrompt = findViewById(R.id.signupPrompt);
    }

    private void setupListeners() {
        loginButton.setOnClickListener(v -> handleLogin());
        signupToggle.setOnClickListener(v -> toggleSignUpMode());
        signupPrompt.setOnClickListener(v -> toggleSignUpMode());
    }

    private void handleLogin() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mock login - In real app, connect to backend
        prefsHelper.saveUserLogin(1, email.split("@")[0], email, "mock_token_123");
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void toggleSignUpMode() {
        isSignUp = !isSignUp;
        if (isSignUp) {
            loginButton.setText("Create Account");
            signupPrompt.setText("Already have an account? Sign In");
        } else {
            loginButton.setText("Sign In");
            signupPrompt.setText("Don't have an account? Sign Up");
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
