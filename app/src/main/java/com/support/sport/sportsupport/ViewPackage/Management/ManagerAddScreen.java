package com.support.sport.sportsupport.ViewPackage.Management;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.BranchManagementController;
import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.ManagerManagementController;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.Menu.FragmentCourse;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ManagerAddScreen extends AppCompatActivity {
    EditText managerName,managerSurname,managerUsername,managerPassword;
    Spinner managerBranchSpinner;
    String branchName = null;
    Button createManager;
    final Context context = this;
    int branchID = 0;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.pID==1){
            if(event.isRetrofitCompleted){
                Toast.makeText(ManagerAddScreen.this, "The Manager Successfully Created" , Toast.LENGTH_LONG).show();
                Key.allManagers.add(0,Key.newManager);
                Key.manSetChanged = true;
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Create process failed!",Toast.LENGTH_LONG).show();
            }
        }else{
            managerName = findViewById(R.id.manager_name_M);
            managerSurname = findViewById(R.id.manager_surname_M);
            managerUsername = findViewById(R.id.manager_username_M);
            managerBranchSpinner = findViewById(R.id.add_man_branch_spinner);
            managerPassword = findViewById(R.id.manager_password_M);
            createManager = findViewById(R.id.create_manager_button);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Key.getAllBranchesName());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            managerBranchSpinner.setAdapter(adapter);
            managerBranchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    branchID = Key.allBranches.get(position).getId();
                    branchName = Key.allBranches.get(position).getName();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    branchID = Key.allBranches.get(0).getId();
                    branchName = Key.allBranches.get(0).getName();
                }
            });


            createManager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int spaceController = 0;
                    spaceController += controlBlank(managerName);
                    spaceController += controlBlank(managerSurname);
                    spaceController += controlBlank(managerUsername);
                    spaceController += controlBlank(managerPassword);


                    if(spaceController == 0){
                        final String manName = managerName.getText().toString();
                        final   String manSurname = managerSurname.getText().toString();
                        final   String manUsername = managerUsername.getText().toString();
                        final   String manPassword = managerPassword.getText().toString();

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Are you sure?");
                        alertDialogBuilder
                                .setMessage("Do you want to create this manager?")
                                .setCancelable(false)
                                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ManagerManagementController managerMC = new ManagerManagementController();
                                        managerMC.createManager(manName,manSurname,manUsername,manPassword,branchID,branchName);
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
        setContentView(R.layout.manager_add_screen);
        new BranchManagementController().showAllBranch();

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
