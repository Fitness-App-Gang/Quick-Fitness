package com.example.fitnessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfrimPassword);
        btnSignup = findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                signUpUser(username, password, confirmPassword);

//
//                if( TextUtils.isEmpty(etUsername.getText())){
//                    etUsername.setError( "Name is required!" );
//                }else if( TextUtils.isEmpty(etPassword.getText())){
//                    etPassword.setError( "Password is required!" );
//                }else if( TextUtils.isEmpty(etConfirmPassword.getText())){
//                    etConfirmPassword.setError( "Confirm password is required!" );
//                }else if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
//                    Toast.makeText(SignUpActivity.this, "Passwords are not the same!", Toast.LENGTH_LONG).show();
//                }else{
//                    ParseUser user = new ParseUser();
//                    user.setUsername(etUsername.getText().toString().trim());
//                    user.setPassword(etPassword.getText().toString());
//                    user.signUpInBackground(new SignUpCallback() {
//                        @Override
//                        public void done(ParseException e) {
//                            if (e == null) {
//                                Toast.makeText(SignUpActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(SignUpActivity.this, MuscleFragment.class);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                ParseUser.logOut();
//                                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                }
            }
        });
    }

    private void signUpUser(String username, String password, String confirmPassword) {
        Log.i(TAG, "Attempting to sign up user:  " + username);
        if(TextUtils.isEmpty(username)){
            etUsername.setError( "username is required!" );
        }else if(TextUtils.isEmpty(password)){
            etPassword.setError( "password is required!" );
        }else if(TextUtils.isEmpty(confirmPassword)){
            etConfirmPassword.setError( "Confirm password is required!" );
        }else if(!password.equals(confirmPassword)){
            Toast.makeText(SignUpActivity.this, "Passwords are not the same!", Toast.LENGTH_LONG).show();
        }else{
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e != null){
                        Log.i(TAG, "Issue with sign up!", e);
                        Toast.makeText(SignUpActivity.this, "Error with sign up!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(SignUpActivity.this, "Successful sign up!", Toast.LENGTH_SHORT).show();
                    goMainActivity();
                }
            });
        }
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
//        finish();
    }


}
