package com.taxivaale.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupLoginActivity  extends AppCompatActivity {

    private EditText edtUserNameS, edtPasswordS, edtUserNameL, edtEdtPasswordL;
    private Button btnSignup, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_login_activity);

        edtUserNameS = findViewById(R.id.edtUserNameS);
        edtPasswordS = findViewById(R.id.edtPasswordS);
        edtUserNameL =findViewById(R.id.edtUserNameL);
        edtEdtPasswordL = findViewById(R.id.edtPasswordL);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameS.getText().toString());
                appUser.setPassword(edtPasswordS.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignupLoginActivity.this, appUser.get("username") + "is signed up successfully", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }

                        else {
                            FancyToast.makeText(SignupLoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameL.getText().toString(), edtEdtPasswordL.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(SignupLoginActivity.this, user.get("username") + "is loged in successfully", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }

                        else {
                            FancyToast.makeText(SignupLoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });
    }
}
