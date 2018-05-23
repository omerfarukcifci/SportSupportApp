package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.MyProfile;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Model.Member;


import com.support.sport.sportsupport.ViewPackage.Management.FragmentManagementPanel;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import com.support.sport.sportsupport.ViewPackage.SignInScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CancelMembershipScreen extends AppCompatActivity {

    private EditText username,password;
    private Button cancel;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            Toast.makeText(this, "Membership cancelled successfully ! ",Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(this, "Error ! Profile hasn't cancelled. Try again.",Toast.LENGTH_LONG).show();
        }

    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_membership_screen);
        username = findViewById(R.id.editTextUpdate_oldusername);
        password = findViewById(R.id.editTextUpdate_oldpassword);
        cancel = findViewById(R.id.buttonDelete);

        if (Key.cMember.getStatue().equals("inactive")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CancelMembershipScreen.this);
            alertDialogBuilder.setTitle("Cancel Membership");
            alertDialogBuilder
                    .setMessage("You are not a member. This page will be active once you become a member.")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int blankController = 0;

                blankController = controlBlank(username);
                blankController += controlBlank(password);
                ProfileController cr = new ProfileController();
                if(blankController == 0){

                    Member m = Key.cMember;
                    final MyProfile controller = new MyProfile();
                    if(username.getText().toString().compareTo(m.getUsername())==0 && password.getText().toString().compareTo(m.getPassword())==0){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CancelMembershipScreen.this);
                        alertDialogBuilder.setTitle("Cancel Membership");
                        alertDialogBuilder
                                .setMessage("Are you sure you want to cancel your membership?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        controller.cancelMembership(username.getText().toString(),"2018-08-08");
                                        Key.updatedProfile = true;
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }else{
                        Toast.makeText(CancelMembershipScreen.this, "Username or password invalid ! ", Toast.LENGTH_LONG).show();
                    }
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
