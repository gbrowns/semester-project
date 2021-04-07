package com.boomer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //variables
    TextView newUserBtn;
    Button loginBtn;

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get elements by id
        username = findViewById(R.id.uname);
        password = findViewById(R.id.passcode);

        //set click events
        newUserBtn = findViewById(R.id.newUserBtn);
        newUserBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,SingupActivity.class);
            startActivity(intent);
        });

        //set click events
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> {
            //grab text input
            String gEmail = username.getText().toString();
            String gPassword = password.getText().toString();

            loginWithEmail(gEmail, gPassword);

        });
    }

    public void loginWithEmail(String email ,String password){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(it->{
            if (it.isSuccessful()){
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                String error = it.getException().getLocalizedMessage();
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}