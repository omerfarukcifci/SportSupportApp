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

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.TrainerManagementController;
import com.support.sport.sportsupport.ViewPackage.Adapter.UserAddScreen;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TrainerAddScreen extends AppCompatActivity {
    EditText trainerName, trainerSurname, trainerUsername, trainerPassword;
    Button createTrainerBTN;
    final Context context = this;


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event){
        if (event.isRetrofitCompleted){
            Toast.makeText(this,"Trainer is succesfully created!",Toast.LENGTH_SHORT).show();
            Key.allTrainers.add(0,Key.newTrainer);
            Key.trainerSetChanged = true;
            finish();
        }else{
            Toast.makeText(this,"Create process failed!",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_add_screen);

        trainerName = findViewById(R.id.trainer_name_M);
        trainerSurname = findViewById(R.id.trainer_surname_M);
        trainerUsername = findViewById(R.id.trainer_username_M);
        trainerPassword = findViewById(R.id.trainer_password_M);

        createTrainerBTN = findViewById(R.id.createTrainer_button);

        createTrainerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int spaceController = 0;
                spaceController = controlBlank(trainerName);
                spaceController += controlBlank(trainerSurname);
                spaceController += controlBlank(trainerUsername);
                spaceController += controlBlank(trainerPassword);

                if(spaceController == 0){

                    final String traName = trainerName.getText().toString();
                    final   String traSurname = trainerSurname.getText().toString();
                    final   String traUsername = trainerUsername.getText().toString();
                    final   String traPassword = trainerPassword.getText().toString();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Are you sure?");
                    alertDialogBuilder
                            .setMessage("Do you want to create this trainer?")
                            .setCancelable(false)
                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TrainerManagementController tmController = new TrainerManagementController();
                                    tmController.createTrainer(traName,traSurname,traUsername,traPassword, Key.cManager.getBranchId());
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
