package com.boomer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    //variables
    TextView newUserBtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set click events
        newUserBtn = findViewById(R.id.newUserBtn);
        newUserBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,SingupActivity.class);
            startActivity(intent);
        });

        //set click events
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}