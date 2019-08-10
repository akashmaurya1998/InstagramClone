package com.taxivaale.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupActivity extends AppCompatActivity{

    private EditText edtEmail, edtUserName, edtPassword;
    private Button btnSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.titleTextSignup);
        setContentView(R.layout.activity_signup);

        edtEmail =findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);



        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        if (ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(SignupActivity.this, SocialMediaActivity.class);
            startActivity(intent);
        }
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().toString().equals("") || edtUserName.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(SignupActivity.this, "Every Field is compulsory", Toast.LENGTH_SHORT, FancyToast.INFO, false).show();
                }
                else {


                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
                    progressDialog.setMessage("Creating User " + edtUserName.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignupActivity.this, appUser.get("username") + " is signed up succesfully", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                Intent intent = new Intent(SignupActivity.this, SocialMediaActivity.class);
                                startActivity(intent);
                            } else {
                                FancyToast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void rootLayoutTapped(View view){
    try {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }
    catch (Exception e){
        e.printStackTrace();
    }



    }
}
