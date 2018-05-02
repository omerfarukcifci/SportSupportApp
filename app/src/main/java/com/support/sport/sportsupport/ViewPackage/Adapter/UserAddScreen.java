package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.ViewPackage.Management.CourseAddScreen;
import com.support.sport.sportsupport.ViewPackage.R;

public class UserAddScreen extends AppCompatActivity {
    private EditText name,surname,username,birtdate,mail,password;
    private Button addNewUser;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_screen);

        name = findViewById(R.id.name_M);
        surname = findViewById(R.id.surname_M);
        username = findViewById(R.id.username_M);
        birtdate = findViewById(R.id.birthdate_M);
        mail = findViewById(R.id.email_M);
        password = findViewById(R.id.password_MM);
        addNewUser = findViewById(R.id.add_new_user_button);


        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spaceController = 0;
                spaceController = controlBlank(name);
                spaceController += controlBlank(surname);
                spaceController += controlBlank(username);
                spaceController += controlBlank(birtdate);
                spaceController += controlBlank(mail);
                spaceController += controlBlank(password);

                String nameStr = name.getText().toString();
                String surnameStr = surname.getText().toString();
                String usernameStr = username.getText().toString();
                String birtdateStr = birtdate.getText().toString();
                String mailStr = mail.getText().toString();
                String passwordStr = password.getText().toString();

                if(spaceController == 0){



                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                    alertDialogBuilder.setTitle("Are you sure?");


                    alertDialogBuilder
                            .setMessage("Do you want to create this user?")
                            .setCancelable(false)
                            //       .setIcon(R.mipmap.ic_launcher_round)

                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(UserAddScreen.this, "The User Successfully Created" , Toast.LENGTH_LONG).show();


                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();



                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    spaceController = 0;

                    alertDialog.show();

                }else{

                    spaceController =0;
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
