package com.support.sport.sportsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signIn_button = (Button) findViewById(R.id.button_signin);
        Button signUp_button = (Button) findViewById(R.id.button_signup);

        signIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignIn = new Intent(MainActivity.this , SignInActivity.class);
                startActivity(intentSignIn);
            }
        });
        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(MainActivity.this , SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });


    }
}
