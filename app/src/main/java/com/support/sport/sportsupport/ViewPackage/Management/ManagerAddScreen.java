package com.support.sport.sportsupport.ViewPackage.Management;

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

import com.support.sport.sportsupport.ViewPackage.R;

public class ManagerAddScreen extends AppCompatActivity {
    EditText managerName,managerSurname,managerUsername,ManagerBranchID,managerPassword;
    Button createManager;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_add_screen);

        managerName = findViewById(R.id.manager_name_M);
        managerSurname = findViewById(R.id.manager_surname_M);
        managerUsername = findViewById(R.id.manager_username_M);
        ManagerBranchID = findViewById(R.id.manager_branchID_M);
        managerPassword = findViewById(R.id.manager_password_M);

        createManager = findViewById(R.id.create_manager_button);

        createManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spaceController = 0;
                spaceController += controlBlank(managerName);
                spaceController += controlBlank(managerSurname);
                spaceController += controlBlank(managerUsername);
                spaceController += controlBlank(ManagerBranchID);
                spaceController += controlBlank(managerPassword);


                if(spaceController == 0){



                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // alert dialog başlığını tanımlıyoruz.
                    alertDialogBuilder.setTitle("Are you sure?");

                    // alert dialog özelliklerini oluşturuyoruz.
                    alertDialogBuilder
                            .setMessage("Do you want to create this manager?")
                            .setCancelable(false)
                            //       .setIcon(R.mipmap.ic_launcher_round)

                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(ManagerAddScreen.this, "The Manager Successfully Created" , Toast.LENGTH_LONG).show();

                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();



                                }
                            });

                    // alert dialog nesnesini oluşturuyoruz
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    spaceController = 0;
                    // alerti gösteriyoruz
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
