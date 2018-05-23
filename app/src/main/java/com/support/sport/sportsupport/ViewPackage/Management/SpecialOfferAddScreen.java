package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.SpecialOfferController;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SpecialOfferAddScreen extends AppCompatActivity {

    EditText offerName,branchName,startDate,endDate,referenceNumberLimit,attendanceLimit,deleteOfferName;
    Button createNewSpecialOffer,deleteSpecialOffer;
    final Context context = this;


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
