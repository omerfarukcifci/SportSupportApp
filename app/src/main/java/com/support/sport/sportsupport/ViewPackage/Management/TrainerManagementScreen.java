package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.TrainerManagementController;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Trainer;
import com.support.sport.sportsupport.ViewPackage.Adapter.ManagerAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.TrainerAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TrainerManagementScreen extends AppCompatActivity {

    private FloatingActionButton fab;
    private TrainerAdapter trainerAdapter;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if (event.pID==0){
            if (event.isRetrofitCompleted) {
                RecyclerView recyclerView = findViewById(R.id.trainers_list);
                trainerAdapter = new TrainerAdapter(Key.allTrainers);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(trainerAdapter);
                fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonTrainer);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(TrainerManagementScreen.this, TrainerAddScreen.class);
                        startActivity(i);
                    }
                });
            }else {
                Toast.makeText(this, "Trainer list is empty!" , Toast.LENGTH_LONG).show();
            }
        }else {
            if (event.isRetrofitCompleted){
                trainerAdapter.notifyDataSetChanged();
                Toast.makeText(this, "The Trainer Successfully Deleted!" , Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Delete Process Failed!" , Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.trainer_management_screen);
        TrainerManagementController tmController = new TrainerManagementController();
        tmController.allTrainers(Key.cManager.getBranchId());
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Key.trainerSetChanged){
            trainerAdapter.setList(Key.allTrainers);
            trainerAdapter.notifyDataSetChanged();
            Key.trainerSetChanged = false;
        }
    }
}
