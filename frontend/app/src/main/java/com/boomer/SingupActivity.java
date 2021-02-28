package com.boomer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SingupActivity extends AppCompatActivity {

    //variables
    TextView haveAccountBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        //add click listener
        haveAccountBtn = (TextView)findViewById(R.id.haveAccountBtn);
        haveAccountBtn.setOnClickListener(v -> {

            Intent intent = new Intent(SingupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }
}