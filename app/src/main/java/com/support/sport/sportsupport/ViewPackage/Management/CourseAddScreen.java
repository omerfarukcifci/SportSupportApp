package com.support.sport.sportsupport.ViewPackage.Management;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.TrainerManagementController;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

public class CourseAddScreen extends AppCompatActivity {
    private EditText courseName,quota,description,species;
    private Spinner trainersp;
    private Button openCourse, setStart,setEnd;
    public static  EditText startDate,endDate;
    final Context context = this;
    int trainerID = 0;








    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        static final int START_DATE = 1;
        static final int END_DATE = 2;

        private int mChosenDate;

        int cur = 0;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            Bundle bundle = this.getArguments();
            if (bundle != null) {
                mChosenDate = bundle.getInt("DATE", 1);
            }


            switch (mChosenDate) {

                case START_DATE:
                    cur = START_DATE;
                    return new DatePickerDialog(getActivity(), this, year, month, day);

                case END_DATE:
                    cur = END_DATE;
                    return new DatePickerDialog(getActivity(), this, year, month, day);

            }
            return null;
        }


        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            if (cur == START_DATE) {
                // set selected date into textview
                startDate.setText( new StringBuilder().append(year)
                        .append("-").append(month + 1).append("-").append(day)
                        .append(" "));
            } else {
                endDate.setText( new StringBuilder().append(year)
                        .append("-").append(month + 1).append("-").append(day)
                        .append(" "));

            }
        }
    }











    @Subscribe
    public void onEvent(RetrofitEvent event){

        if (event.pID==0){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Key.getAllTrainersName());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            trainersp.setAdapter(adapter);
            trainersp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    trainerID = Key.allTrainers.get(position).getId();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    trainerID = Key.allTrainers.get(0).getId();
                }
            });

            openCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int blankController = 0;

                    blankController = controlBlank(courseName);
                    blankController += controlBlank(quota);
                    blankController += controlBlank(startDate);
                    blankController += controlBlank(endDate);
                    blankController += controlBlank(description);
                    blankController += controlBlank(species);

                    if(blankController == 0){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Are you sure?");
                        alertDialogBuilder
                                .setMessage("Do you want to create this course?")
                                .setCancelable(false)
                                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new CourseController().addNewCourse(courseName.getText().toString(),Integer.parseInt(quota.getText().toString()),trainerID,Key.cManager.getBranchId(),startDate.getText().toString(),
                                                endDate.getText().toString(),description.getText().toString(),species.getText().toString());
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

        }else{
            if (event.isRetrofitCompleted){
                Toast.makeText(CourseAddScreen.this, "The Course Is Successfully Created" , Toast.LENGTH_SHORT).show();
                Key.allClist.add(0,Key.addedCourse);
                Key.courseSetChanged = true;
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "An error occurred during adding new course!",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_add_screen);

        courseName = findViewById(R.id.course_name_M);
        quota = findViewById(R.id.course_quota_M);
        startDate = findViewById(R.id.start_date_course);
        endDate = findViewById(R.id.end_date_course);
        description = findViewById(R.id.course_description);
        openCourse = findViewById(R.id.open_course_button);
        trainersp = findViewById(R.id.add_course_trainer_spinner);
        species = findViewById(R.id.course_species_M);
        setEnd = findViewById(R.id.BTN_C_End_Date);
        setStart = findViewById(R.id.BTN_C_Start_Date);

        setEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",2);

                DialogFragment newFragment = new CourseAddScreen.DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        setStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",1);

                DialogFragment newFragment = new CourseAddScreen.DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });



        new TrainerManagementController().allTrainers(Key.cManager.getBranchId());
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


    public int controlBlank(EditText edt){

        String strUserName = edt.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            edt.setError("This field cannot be empty.");
            return 1;
        }
        return 0;
    }
}
