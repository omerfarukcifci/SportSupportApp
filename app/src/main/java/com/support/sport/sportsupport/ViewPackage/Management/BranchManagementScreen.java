package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.ViewPackage.Adapter.BranchAdapter;
import com.support.sport.sportsupport.ViewPackage.R;

public class BranchManagementScreen extends AppCompatActivity {

    FloatingActionButton fab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branch_management_screen);

        RecyclerView recyclerView = findViewById(R.id.branch_list);

        long phone = 0312231234;
        Branch b1 = new Branch("Eryaman Sport Support",100,phone,"Etimesgut","Eryaman","XYZ Street 15.Block 3 Etimesgut-ANKARA");
        Branch b2 = new Branch("Eryaman Sport Support",100,phone,"Etimesgut","Eryaman","XYZ Street 15.Block 3 Etimesgut-ANKARA");
        Branch b3 = new Branch("Eryaman Sport Support",100,phone,"Etimesgut","Eryaman","XYZ Street 15.Block 3 Etimesgut-ANKARA");
        Branch b4 = new Branch("Eryaman Sport Support",100,phone,"Etimesgut","Eryaman","XYZ Street 15.Block 3 Etimesgut-ANKARA");

        Branch[] branchList = new Branch[4];
        branchList[0]=b1;
        branchList[1]=b2;
        branchList[2]=b3;
        branchList[3]=b4;

        BranchAdapter branchAdapter = new BranchAdapter(branchList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(branchAdapter);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonBranch);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BranchManagementScreen.this,BranchAddScreen.class);
                startActivity(i);
            }
        });





    }
}
