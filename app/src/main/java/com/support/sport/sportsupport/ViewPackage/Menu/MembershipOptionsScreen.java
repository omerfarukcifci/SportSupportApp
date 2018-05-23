package com.support.sport.sportsupport.ViewPackage.Menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.BranchManagementController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.PaymentController;
import com.support.sport.sportsupport.Model.Cost;
import com.support.sport.sportsupport.ViewPackage.Adapter.MembershipAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MembershipOptionsScreen extends AppCompatActivity {

    Button buttonPayBecomeMemberPool, buttonPayBecomeMemberGold,buttonPayBecomeMemberStandart,buttonPayBecomeMemberPlatin;
    TextView feePool, feeStand, feeGold, feePlatin;
    TextView pls;
    Spinner brspinner ;
    int branchID = 0;
    String branchName;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.pID==0){
            brspinner = findViewById(R.id.pay_branch_spinner);
            pls = findViewById(R.id.pls_select_branch_text);
            if (!Key.cMember.getStatue().equals("inactive")){
                ((ViewManager)brspinner.getParent()).removeView(brspinner);
                ((ViewManager)pls.getParent()).removeView(pls);
                new PaymentController().loadUpgradeOptions(Key.cMember.getId());
            }
            else{
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Key.getAllBranchesName());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                brspinner.setAdapter(adapter);
                brspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        branchID = Key.allBranches.get(position).getId();
                        branchName = Key.allBranches.get(position).getName();
                        new PaymentController().loadMemberShipOptions(branchID);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        branchID = Key.allBranches.get(0).getId();
                        branchName = Key.allBranches.get(0).getName();
                        new PaymentController().loadMemberShipOptions(branchID);
                    }
                });
            }
        }else{
            if (event.isRetrofitCompleted){
                ArrayList<Cost> costArrayList = makeCostList();
                RecyclerView recyclerView = findViewById(R.id.mem_options_list);
                MembershipAdapter mAdapter = new MembershipAdapter(costArrayList,branchID,getApplicationContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);

                View parentLayout = findViewById(android.R.id.content);
                Snackbar mySnackbar = Snackbar.make(parentLayout, "Membership Options Refreshed!", Snackbar.LENGTH_SHORT);
                mySnackbar.show();

            }else{
                ArrayList<Cost> costArrayList = makeCostList();
                if (costArrayList==null){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MembershipOptionsScreen.this);
                    alertDialog.setTitle("Upgrade");
                    alertDialog.setCancelable(false);
                    alertDialog.setMessage("You don't have any upgrade option.");
                    alertDialog.setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                        }
                    });
                    alertDialog.show();
                }

            }
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
        setContentView(R.layout.activity_membership_options_screen);

        /*buttonPayBecomeMemberPool = (Button) findViewById(R.id.pay_become_member_button_pool);
        buttonPayBecomeMemberGold = (Button) findViewById(R.id.pay_become_member_button_gold);
        buttonPayBecomeMemberStandart = (Button) findViewById(R.id.pay_become_member_button_standard);
        buttonPayBecomeMemberPlatin = (Button) findViewById(R.id.pay_become_member_button_platin);

        feePool = findViewById(R.id.options_fee_pool);
        feeStand = findViewById(R.id.options_fee_standard);
        feePlatin = findViewById(R.id.options_fee_platin);
        feeGold = findViewById(R.id.options_fee_gold);*/

        new BranchManagementController().showAllBranch();
    }

    private ArrayList<Cost> makeCostList(){
        ArrayList<Cost> costList = new ArrayList<Cost>();
        if (Key.cMember.getStatue().equals("inactive")){
            Cost c = new Cost("Pool","No","Yes","No");
            c.setTotalcost(Key.selectedBranchFee.getPoolMembership());
            Cost c1 = new Cost("Standard","Yes","No","No");
            c1.setTotalcost(Key.selectedBranchFee.getStandardMembership());
            Cost c2 = new Cost("Gold","Yes","Yes","No");
            c2.setTotalcost(Key.selectedBranchFee.getGoldMembership());
            Cost c3 = new Cost("Platin","Yes","Yes","Yes");
            c3.setTotalcost(Key.selectedBranchFee.getPlatinumMembership());
            costList.add(c);
            costList.add(c1);
            costList.add(c2);
            costList.add(c3);
            return costList;
        }

        if (Key.cMember.getStatus().equals("platinum")){
            return null;
        }
        else if (Key.cMember.getStatus().equals("gold")){
            Cost c3 = new Cost("Platin","Yes","Yes","Yes");
            c3.setTotalcost(Key.selectedBranchFee.getPlatinumMembership());
            costList.add(c3);
        }
        else if (Key.cMember.getStatus().equals("standard")){
            Cost c2 = new Cost("Gold","Yes","Yes","No");
            c2.setTotalcost(Key.selectedBranchFee.getGoldMembership());
            Cost c3 = new Cost("Platin","Yes","Yes","Yes");
            c3.setTotalcost(Key.selectedBranchFee.getPlatinumMembership());
            costList.add(c2);
            costList.add(c3);
        }
        else{
            Cost c1 = new Cost("Standard","Yes","No","No");
            c1.setTotalcost(Key.selectedBranchFee.getStandardMembership());
            Cost c2 = new Cost("Gold","Yes","Yes","No");
            c2.setTotalcost(Key.selectedBranchFee.getGoldMembership());
            Cost c3 = new Cost("Platin","Yes","Yes","Yes");
            c3.setTotalcost(Key.selectedBranchFee.getPlatinumMembership());
            costList.add(c1);
            costList.add(c2);
            costList.add(c3);
        }
        return costList;
    }

}
