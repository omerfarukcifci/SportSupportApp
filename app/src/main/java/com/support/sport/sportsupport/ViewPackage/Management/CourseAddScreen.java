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

public class CourseAddScreen extends AppCompatActivity {
    private EditText courseName,quota,branchName,trainerName,species,startDate,endDate,description;
    private Button openCourse;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_add_screen);

        courseName = findViewById(R.id.course_name_M);
        quota = findViewById(R.id.course_quota_M);
        branchName = findViewById(R.id.branch_name_M);
        trainerName = findViewById(R.id.trainer_name_M);
        species = findViewById(R.id.speciesM);
        startDate = findViewById(R.id.start_date_course);
        endDate = findViewById(R.id.end_date_course);
        description = findViewById(R.id.course_description);
        openCourse = findViewById(R.id.open_course_button);


        openCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int blankController = 0;

                blankController = controlBlank(courseName);
                blankController += controlBlank(quota);
                blankController += controlBlank(branchName);
                blankController += controlBlank(trainerName);
                blankController += controlBlank(species);
                blankController += controlBlank(startDate);
                blankController += controlBlank(endDate);
                blankController += controlBlank(description);


                if(blankController == 0){



                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                    alertDialogBuilder.setTitle("Are you sure?");


                    alertDialogBuilder
                            .setMessage("Do you want to create this course?")
                            .setCancelable(false)
                            //       .setIcon(R.mipmap.ic_launcher_round)

                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(CourseAddScreen.this, "The Course Successfully Created" , Toast.LENGTH_LONG).show();


                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();



                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    blankController = 0;

                    alertDialog.show();


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
