package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.BranchManagementController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.ViewPackage.Adapter.BranchAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.ManagerAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BranchManagementScreen extends AppCompatActivity {

    FloatingActionButton fab ;
    BranchAdapter branchAdapter;
    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if (event.pID==0){
            if(event.isRetrofitCompleted){
                RecyclerView recyclerView = findViewById(R.id.branch_list);
                branchAdapter = new BranchAdapter(Key.allBranches);
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
            }else{
                Toast.makeText(this,"No branches to display",Toast.LENGTH_SHORT).show();
            }
        }else{
            if(event.isRetrofitCompleted){
                branchAdapter.notifyDataSetChanged();
                Toast.makeText(this,"Successfully deleted!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Delete process failed!",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.branch_management_screen);
        new BranchManagementController().showAllBranch();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Key.branchSetChanged){
            branchAdapter.setList(Key.allBranches);
            branchAdapter.notifyDataSetChanged();
            Key.branchSetChanged = false;
        }

    }
}
