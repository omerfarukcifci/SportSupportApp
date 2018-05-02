package com.support.sport.sportsupport.ViewPackage;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.AsyncTask;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ApiClient;
import com.support.sport.sportsupport.Controller.ApiInterface;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.ViewPackage.Menu.CustomerNavigationMenu;
import com.support.sport.sportsupport.ViewPackage.Menu.PaymentScreen;

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
    final Context context = this;
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
                int spaceController = 0;
                spaceController = controlBlank(name);
                spaceController += controlBlank(surname);
                spaceController += controlBlank(username);
                spaceController += controlBlank(birtdate);
                spaceController += controlBlank(mail);
                spaceController += controlBlank(password);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                ProfileController cr = new ProfileController();


                if (spaceController == 0) {

                cr.addMember(0,0,usernameStr,passwordStr,"active","standard",mailStr,nameStr,surnameStr);
             //   Toast.makeText(SignUpScreen.this,"Registration Completed!",Toast.LENGTH_LONG).show();
            //    Toast.makeText(SignUpScreen.this,"Succesfully Updated!",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SignUpScreen.this, CustomerNavigationMenu.class);
                  startActivity(intent);
                } else {
                    spaceController = 0;
                }
             //   showDialog();


            }
        });

    }







    public int controlBlank(EditText edt){

        String strUserName = edt.getText().toString();

        if(strUserName.equals("alisahin")){
            edt.setError("This username is already taken.");
            return 1;
        }

        if(TextUtils.isEmpty(strUserName)) {
            edt.setError("This field cannot be empty.");
            return 1;
        }
        return 0;
    }


    public void showDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Successfully Deleted!");
        //alertDialog.setMessage("Message");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

    /*    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });*/
        alertDialog.show();

        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
      //  Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        layoutParams.weight = 10;
        btnPositive.setLayoutParams(layoutParams);
     //   btnNegative.setLayoutParams(layoutParams);
        alertDialog.show();
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            return null;
        }
    }
}

