package com.support.sport.sportsupport.ViewPackage.Management;
/*
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

public class SpecialOfferManagementScreen extends AppCompatActivity {
    EditText offerName,branchName,startDate,endDate,referenceNumberLimit,attendanceLimit,deleteOfferName;
    Button createNewSpecialOffer,deleteSpecialOffer;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_offer_management_screen);

        offerName = findViewById(R.id.soffer_name_M);
        branchName = findViewById(R.id.soffer_branch_name_M);
        startDate = findViewById(R.id.soffer_start_date_M);
        endDate = findViewById(R.id.soffer_end_date_M);
        referenceNumberLimit = findViewById(R.id.soffer_reference_number__limitM);
        attendanceLimit = findViewById(R.id.soffer_attendance_limit);
        deleteOfferName = findViewById(R.id.delete_soffor_name_M);


        createNewSpecialOffer = findViewById(R.id.create_soffer_button);
        deleteSpecialOffer = findViewById(R.id.soffer_delete_M);



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

                String offerNameStr = offerName.getText().toString();
                String branchNameStr = branchName.getText().toString();
                String startDateStr = startDate.getText().toString();
                String endDateStr = endDate.getText().toString();
                String referenceNumberLimitStr = referenceNumberLimit.getText().toString();
                String attendanceLimitStr = attendanceLimit.getText().toString();

                if(spaceController == 0){



                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                    alertDialogBuilder.setTitle("Are you sure?");


                    alertDialogBuilder
                            .setMessage("Do you want to create this special offer?")
                            .setCancelable(false)
                            //       .setIcon(R.mipmap.ic_launcher_round)

                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(SpecialOfferManagementScreen.this, "The Special Offer Successfully Created" , Toast.LENGTH_LONG).show();


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


        deleteSpecialOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spaceController = 0;

                spaceController += controlBlank(deleteOfferName);
                String deleteOfferNameStr = deleteOfferName.getText().toString();


                if(spaceController == 0){



                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                    alertDialogBuilder.setTitle("Are you sure?");


                    alertDialogBuilder
                            .setMessage("Do you want to delete this special offer?")
                            .setCancelable(false)
                            //       .setIcon(R.mipmap.ic_launcher_round)

                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(SpecialOfferManagementScreen.this, "The Special Offer Successfully Deleted" , Toast.LENGTH_LONG).show();


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
*/