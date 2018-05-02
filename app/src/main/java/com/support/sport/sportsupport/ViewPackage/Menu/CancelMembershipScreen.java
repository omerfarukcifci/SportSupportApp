package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Model.Member;


import com.support.sport.sportsupport.ViewPackage.R;

public class CancelMembershipScreen extends AppCompatActivity {

    private EditText username,password;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_membership_screen);
        username = findViewById(R.id.editTextUpdate_oldusername);
        password = findViewById(R.id.editTextUpdate_oldpassword);
        cancel = findViewById(R.id.buttonDelete);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int blankController = 0;

                blankController = controlBlank(username);
                blankController += controlBlank(password);
                ProfileController cr = new ProfileController();
                if(blankController == 0){


                   // Member temp = cr.readOne(username.getText().toString(), password.getText().toString());
                //    if (temp != null) {
                        Toast.makeText(CancelMembershipScreen.this, "Successfully cancel membership", Toast.LENGTH_LONG).show();
                  //      Toast.makeText(SignInActivity.this, "Hello " + temp.getName() + " :)", Toast.LENGTH_LONG).show();
                  //      Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                  //      startActivity(intent);
                ///    } else {
                ///        Toast.makeText(CancelMembershipScreen.this, "Password or Username invalid!", Toast.LENGTH_LONG).show();
                //    }

                }else{

                    blankController =0;
                }

            }
        });

    }


    public int controlBlank(EditText edt){

        String strUserName = edt.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            edt.setError("This field cannot be empty.");
            return 1;
        }
        return 0;
    }
}
