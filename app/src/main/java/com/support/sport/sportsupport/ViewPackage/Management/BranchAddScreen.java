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

import com.support.sport.sportsupport.Controller.BranchManagementController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.ViewPackage.Menu.PaymentScreen;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class BranchAddScreen extends AppCompatActivity {
    EditText branchName,branchQuota,branchPhoneNumber,branchCity,branchDistrict,branchAddress;
    Button openNewBranch;
    final Context context = this;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){

            Toast.makeText(this, "Branch created ! ",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Branch hasn't created ! Try again.",Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.branch_add_screen);

        branchName = findViewById(R.id.branch_name_M);
        branchQuota = findViewById(R.id.branch_quota_M);
        branchPhoneNumber = findViewById(R.id.branch_phone_number_M);
        branchCity = findViewById(R.id.branch_city_M);
        branchDistrict = findViewById(R.id.branch_district_M);
        branchAddress = findViewById(R.id.branch_address_M);

        openNewBranch = findViewById(R.id.open_new_branch_button);




        openNewBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spaceController = 0;
                spaceController += controlBlank(branchName);
                spaceController += controlBlank(branchQuota);
                spaceController += controlBlank(branchPhoneNumber);
                spaceController += controlBlank(branchCity);
                spaceController += controlBlank(branchDistrict);
                spaceController += controlBlank(branchAddress);

                if(spaceController == 0) {






                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // alert dialog başlığını tanımlıyoruz.
                    alertDialogBuilder.setTitle("Are you sure?");

                    // alert dialog özelliklerini oluşturuyoruz.
                    alertDialogBuilder
                            .setMessage("Do you want to create this branch?")
                            .setCancelable(false)
                            //       .setIcon(R.mipmap.ic_launcher_round)

                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    long phone = 03121244123;
                                    BranchManagementController controller = new BranchManagementController();
                                    controller.addBranch(branchName.getText().toString(),Integer.valueOf(branchQuota.getText().toString()), Long.valueOf(branchPhoneNumber.getText().toString()),
                                            branchCity.getText().toString(),branchDistrict.getText().toString(),branchAddress.getText().toString());
                                    //Toast.makeText(BranchAddScreen.this, "The Branch Successfully Created" , Toast.LENGTH_LONG).show();
                                    //Toast.makeText(BranchAddScreen.this, "Branch created ! ",Toast.LENGTH_LONG).show();
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
