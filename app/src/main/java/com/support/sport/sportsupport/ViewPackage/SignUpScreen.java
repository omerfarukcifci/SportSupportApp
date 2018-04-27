package com.support.sport.sportsupport.ViewPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ApiClient;
import com.support.sport.sportsupport.Controller.ApiInterface;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.ViewPackage.Menu.CustomerNavigationMenu;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpScreen extends AppCompatActivity {


    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText name,surname,username,birtdate,mail,password;
    private View mProgressView;
    private View mLoginFormView;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Set up the login form.

        name = findViewById(R.id.signup_name);
        surname = findViewById(R.id.signup_surname);
        username = findViewById(R.id.signup_userName);
        birtdate = findViewById(R.id.signup_birthDate);
        mail = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        signUp = findViewById(R.id.sign_up_button);


        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                String surnameStr = surname.getText().toString();
                String usernameStr = username.getText().toString();
                String birtdateStr = birtdate.getText().toString();
                String mailStr = mail.getText().toString();
                String passwordStr = password.getText().toString();

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                ProfileController cr = new ProfileController();
                cr.addMember(0,0,usernameStr,passwordStr,"active","standard",mailStr,nameStr,surnameStr);
                Toast.makeText(SignUpScreen.this,"Registration Completed!",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SignUpScreen.this,CustomerNavigationMenu.class);
                startActivity(intent);
            }
        });
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }
    }
}

