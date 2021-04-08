package com.boomer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingupActivity extends AppCompatActivity {

    private static final String TAG = "Signup Activity started";
    //variables
    TextView haveAccountBtn; //button
    Button regBtn; //button
    EditText regName,regEmail, regPhoneNo, regPassword; //input fields
    ProgressBar signupProgressBar;

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
            signupProgressBar = findViewById(R.id.signupProgressBar);

            regName = findViewById(R.id.fullName);
            regEmail = findViewById(R.id.emailAddress);
            regPhoneNo = findViewById(R.id.phoneNumber);
            regPassword = findViewById(R.id.passcode);

            rootNode = FirebaseDatabase.getInstance("https://boomer-instruments-app-default-rtdb.firebaseio.com/");
            //save data to firebase on user sign up
            regBtn.setOnClickListener(v -> {

                //rootNode get database instance
                reference = rootNode.getReference("users"); //set reference location to users


                //validate user inputs
                if(!validateName() | !validateEmail() | !validatePhoneNo() | !validatePassword()){
                    return;
                }


                //get values passed to the fields
                String name = regName.getText().toString();
                String email = regEmail.getText().toString();
                String phoneNo = regPhoneNo.getText().toString();
                String password = regPassword.getText().toString();

                //UserHelperClass helperClass = new UserHelperClass(name, email, phoneNo, password); //object instance of userHelperClass

                signupProgressBar.setVisibility(View.VISIBLE); //SET PROGRESS BAR
                //reference.child(phoneNo).setValue(helperClass); //uniquely sets values to DB
                    signUpWithEmail(email, password, phoneNo, name);


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


    private Boolean validateName(){
        String val = regName.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()){
            regName.setError("Field cannot be empty");
            regName.requestFocus();
            return false;
        }else if(val.length() >= 15){
            regName.setError("Username too long");
            return false;
        }else if(!val.matches(noWhiteSpace)){
            regName.setError("No white spaces");
            return false;
        }
        else{
            regName.setError(null);
            regName.clearFocus();
            return true;
        }
    } //validate username input
    private Boolean validateEmail(){
        String val = regEmail.getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            regEmail.requestFocus();
            return false;
        }else if(!val.matches(emailPattern)){
            regEmail.setError(("Invalid email address"));
            return false;
        }
        else{
            regEmail.setError(null);
            regEmail.clearFocus();
            return true;
        }
    } //validate email input
    private Boolean validatePhoneNo(){
        String val = regPhoneNo.getText().toString();

        if (val.isEmpty()){
            regPhoneNo.setError("Field cannot be empty");
            regPhoneNo.requestFocus();
            return false;
        }
        else{
            regPhoneNo.setError(null);
            regPhoneNo.clearFocus();
            return true;
        }
    } //validate phone number input
    private Boolean validatePassword(){

        String val = regPassword.getText().toString();
        String  passwordVal = "(?=.*[a-zA-Z])" + "(?=\\S+$)" + ".{4,}" + "$";

        if (val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            regPassword.requestFocus();
            return false;
        }else if(!val.matches(passwordVal)){
            regPassword.setError("Password too weak");
            return false;
        }
        else{
            regPassword.setError(null);
            regPassword.clearFocus();
            return true;
        }
    } //validate password input


    private void signUpWithEmail(String email, String password, String phoneNo, String name) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(it ->{

            signupProgressBar.setVisibility(View.INVISIBLE); //set progressbar invisible
            if (it.isSuccessful()){
                if (auth.getCurrentUser().getUid() != null){
                    String uid = auth.getCurrentUser().getUid();
                    rootNode.getReference("users/"+ uid).setValue(new UserHelperClass(phoneNo, name)).addOnCompleteListener(task->{
                        if (task.isSuccessful()){
                            //signup complete
                            //clear input fields
                            regName.setText("");
                            regEmail.setText("");
                            regPhoneNo.setText("");
                            regPassword.setText("");

                            //switch to the homepage activity
                            Intent intent = new Intent(SingupActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            String error = task.getException().getLocalizedMessage();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else{
                String error = it.getException().getLocalizedMessage();
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        });

    }
    /*
    private void validateData(){
        String name = regName.getText().toString();
        String email = regEmail.getText().toString();
        if (name.length() < 1){
            //empty name
            return;
        }
        if (email.length() < 1 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //email invalid
            return;
        }
    }

     */
}