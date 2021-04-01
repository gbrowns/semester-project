package com.boomer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingupActivity extends AppCompatActivity {

    private static final String TAG = "Signup Activity started";
    //variables
    TextView haveAccountBtn; //button
    Button regBtn; //button
    EditText regName,regEmail, regPhoneNo, regPassword; //input fields

    //initialize firebase rootNodes
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        Log.d(TAG, "onCreate: SignupActivity started successfully!");
        try {
            //hooks to xml elements
            haveAccountBtn = findViewById(R.id.haveAccountBtn);
            regBtn = findViewById(R.id.signupBtn);

            regName = findViewById(R.id.fullName);
            regEmail = findViewById(R.id.emailAddress);
            regPhoneNo = findViewById(R.id.phoneNumber);
            regPassword = findViewById(R.id.passcode);

            //save data to firebase on user sign up
            regBtn.setOnClickListener(v -> {

                //rootNode get database instance
                rootNode = FirebaseDatabase.getInstance("https://boomer-instruments-app-default-rtdb.firebaseio.com/");
                reference = rootNode.getReference("users"); //set reference location to users

                //get values passed to the fields
                String name = regName.getText().toString();
                String email = regEmail.getText().toString();
                String phoneNo = regPhoneNo.getText().toString();
                String password = regPassword.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, email, phoneNo, password); //object instance of userHelperClass

                reference.child(phoneNo).setValue(helperClass); //uniquely
            }); //signupButton method ends

            //switch to login screen
            haveAccountBtn.setOnClickListener(v -> {

                Intent intent = new Intent(SingupActivity.this, LoginActivity.class);
                startActivity(intent);
            });//switch to login method ends

        }catch (Exception e){
            Log.e(TAG, "onCreate: ", e.getCause());
        }
    }
}