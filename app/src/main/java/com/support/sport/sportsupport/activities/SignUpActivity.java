package com.support.sport.sportsupport.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.activities.R;
import com.support.sport.sportsupport.services.ApiClient;
import com.support.sport.sportsupport.services.ApiInterface;
import com.support.sport.sportsupport.services.CrudOPs;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpActivity extends AppCompatActivity {


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
                CrudOPs cr = new CrudOPs();
                cr.addMember(apiService,0,0,usernameStr,passwordStr,"active","standard",mailStr,nameStr,surnameStr);
                Toast.makeText(SignUpActivity.this,"Member Created",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SignUpActivity.this,MenuActivity.class);
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

