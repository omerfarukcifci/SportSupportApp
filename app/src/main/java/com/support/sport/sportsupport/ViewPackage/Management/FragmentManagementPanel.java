package com.support.sport.sportsupport.ViewPackage.Management;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.ViewPackage.Menu.CustomerNavigationMenu;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.WelcomeScreen;

public class FragmentManagementPanel extends AppCompatActivity {

    private Button userManagement,managerManagement,trainerManagement,traineeManagement,courseManagement,branchManagement,offerManagement;
    private Button logout, viewBranchStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_panel);

        Intent intent = getIntent();
        boolean manager = intent.getExtras().getBoolean("checkboxManager");
        boolean owner = intent.getExtras().getBoolean("checkboxOwner");
        boolean trainer = intent.getExtras().getBoolean("checkboxTrainer");


        userManagement = (Button) findViewById(R.id.user_management_button);
        managerManagement = (Button) findViewById(R.id.manager_management_button);
        trainerManagement = (Button) findViewById(R.id.trainer_management_button);
        traineeManagement = (Button) findViewById(R.id.trainee_management_button);
        courseManagement = (Button) findViewById(R.id.courses_management_button);
        offerManagement = (Button) findViewById(R.id.offers_management_button);
        branchManagement = (Button) findViewById(R.id.branch_management_button);
        logout = (Button) findViewById(R.id.management_panel_logout_button);
        viewBranchStats= (Button) findViewById(R.id.manager_view_branch_stats);

        if(manager==true){
            viewBranchStats.setVisibility(View.VISIBLE);
            userManagement.setEnabled(true);
            managerManagement.setEnabled(false);
            trainerManagement.setEnabled(true);
            traineeManagement.setEnabled(false);
            courseManagement.setEnabled(true);
            offerManagement.setEnabled(true);
            branchManagement.setEnabled(false);
        }
        if(owner==true){
            userManagement.setEnabled(false);
            managerManagement.setEnabled(true);
            trainerManagement.setEnabled(false);
            traineeManagement.setEnabled(false);
            courseManagement.setEnabled(false);
            offerManagement.setEnabled(false);
            branchManagement.setEnabled(true);
        }
        if(trainer==true){
            userManagement.setEnabled(false);
            managerManagement.setEnabled(false);
            trainerManagement.setEnabled(false);
            traineeManagement.setEnabled(true);
            courseManagement.setEnabled(false);
            offerManagement.setEnabled(false);
            branchManagement.setEnabled(false);
        }

        viewBranchStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( FragmentManagementPanel.this ,FragmentBranchStats.class);
                intent.putExtra("SelectedBranch", Key.cManager.getBranchId());
                startActivity(intent);
            }
        });

        userManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentManagementPanel.this,UserManagementScreen.class);
                startActivity(intent);

            }
        });
        managerManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentManagementPanel.this,ManagerManagementScreen.class);
                startActivity(intent);
            }
        });
        trainerManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentManagementPanel.this,TrainerManagementScreen.class);
                startActivity(intent);

            }
        });
        traineeManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentManagementPanel.this,TraineeManagementScreen.class);
                startActivity(intent);
            }
        });
        courseManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentManagementPanel.this,CourseManagementScreen.class);
                startActivity(intent);
            }
        });
        offerManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentManagementPanel.this,SpecialOfferManagementScreen.class);
                startActivity(intent);
            }
        });
        branchManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentManagementPanel.this,BranchManagementScreen.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FragmentManagementPanel.this);
                // Setting Dialog Title
                alertDialog.setTitle("Confirm Logout");
                alertDialog.setCancelable(true);
                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want to log out?");
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent3 = new Intent(FragmentManagementPanel.this,WelcomeScreen.class);
                        startActivity(intent3);
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });


    }
}
