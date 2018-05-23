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
import com.support.sport.sportsupport.Model.Fee;
import com.support.sport.sportsupport.ViewPackage.Menu.PaymentScreen;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class BranchAddScreen extends AppCompatActivity {
    EditText branchName,branchQuota,branchPhoneNumber,branchCity,branchDistrict,branchAddress;
    EditText weekly,onetime,pool,stand,gold,platin;
    Button openNewBranch;
    Fee fee;
    final Context context = this;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            Toast.makeText(this, "Branch created ! ",Toast.LENGTH_LONG).show();
            Key.allBranches.add(0,Key.addedBranch);
            Key.branchSetChanged = true;
            finish();
        }else{
            Toast.makeText(this, "Create process failed! Try again.",Toast.LENGTH_LONG).show();
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
        weekly = findViewById(R.id.branch_fee_weekly);
        onetime = findViewById(R.id.branch_fee_one_time);
        pool = findViewById(R.id.branch_pool_mem);
        gold = findViewById(R.id.branch_gold_mem);
        platin = findViewById(R.id.branch_platin_mem);
        stand = findViewById(R.id.branch_sta_mem);

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
                spaceController += controlBlank(stand);
                spaceController += controlBlank(pool);
                spaceController += controlBlank(gold);
                spaceController += controlBlank(platin);
                spaceController += controlBlank(weekly);
                spaceController += controlBlank(onetime);

                if(spaceController == 0) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Are you sure?");
                    alertDialogBuilder
                            .setMessage("Do you want to create this branch?")
                            .setCancelable(false)
                            .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    BranchManagementController controller = new BranchManagementController();
                                    fee = new Fee(Integer.valueOf(weekly.getText().toString()),Integer.valueOf(onetime.getText().toString()),Integer.valueOf(pool.getText().toString()),Integer.valueOf(stand.getText().toString()),Integer.valueOf(gold.getText().toString()),Integer.valueOf(platin.getText().toString()));
                                    controller.addBranch(branchName.getText().toString(),Integer.valueOf(branchQuota.getText().toString()), Long.valueOf(branchPhoneNumber.getText().toString()),
                                            branchCity.getText().toString(),branchDistrict.getText().toString(),branchAddress.getText().toString(),fee);
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
