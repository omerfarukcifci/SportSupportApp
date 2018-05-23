package com.support.sport.sportsupport.ViewPackage.Management;

import android.app.DatePickerDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.SpecialOfferController;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

public class SpecialOfferAddScreen extends AppCompatActivity {

    EditText offerName,branchName,referenceNumberLimit,attendanceLimit,deleteOfferName;
    Button createNewSpecialOffer,deleteSpecialOffer,setStartDate,setEndDate;
    public  static EditText startDate,endDate;
    final Context context = this;

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
                        );
            } else {
                endDate.setText( new StringBuilder().append(year)
                        .append("-").append(month + 1).append("-").append(day)
                        );

            }
        }
    }



    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            Toast.makeText(SpecialOfferAddScreen.this, "The Special Offer Successfully Created" , Toast.LENGTH_LONG).show();
            Key.offerListChanged = true;
            Key.allSpecialOffers.add(0,Key.newSpecialOffer);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Process failed!",Toast.LENGTH_LONG).show();
        }

    }

    public void onDestroy() {

        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_offer_management_screen);
        EventBus.getDefault().register(this);
        offerName = findViewById(R.id.soffer_name_M);
        branchName = findViewById(R.id.soffer_branch_name_M);
        startDate = findViewById(R.id.soffer_start_date_M);
        endDate = findViewById(R.id.soffer_end_date_M);
        referenceNumberLimit = findViewById(R.id.soffer_reference_number__limitM);
        attendanceLimit = findViewById(R.id.soffer_attendance_limit);
        deleteOfferName = findViewById(R.id.delete_soffor_name_M);

        setEndDate = findViewById(R.id.BTN_so_endDate);
        setStartDate = findViewById(R.id.BTN_so_startDate);

        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",2);

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        setStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("DATE",1);

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setArguments(bundle);

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        createNewSpecialOffer = findViewById(R.id.create_soffer_button);
        createNewSpecialOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spaceController = 0;
                spaceController += controlBlank(offerName);
                spaceController += controlBlank(branchName);
                spaceController += controlBlank(startDate);
                spaceController += controlBlank(endDate);
                spaceController += controlBlank(referenceNumberLimit);
                spaceController += controlBlank(attendanceLimit);
                final String offerNameStr = offerName.getText().toString();
                final String branchNameStr = branchName.getText().toString();
                final String startDateStr = startDate.getText().toString();
                final String endDateStr = endDate.getText().toString();
                final String referenceNumberLimitStr = referenceNumberLimit.getText().toString();
                final String attendanceLimitStr = attendanceLimit.getText().toString();
                if(spaceController == 0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Are you sure?");
                    alertDialogBuilder
                            .setMessage("Do you want to create this special offer?")
                            .setCancelable(false)
                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SpecialOfferController specialOfferController = new SpecialOfferController();
                                    specialOfferController.createSpecialOffer(offerNameStr, Integer.toString(Key.cManager.getBranchId()),startDateStr,endDateStr,branchNameStr,referenceNumberLimitStr,attendanceLimitStr);
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
