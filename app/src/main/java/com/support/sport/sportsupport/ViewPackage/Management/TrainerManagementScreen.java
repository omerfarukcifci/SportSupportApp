package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Trainer;
import com.support.sport.sportsupport.ViewPackage.Adapter.ManagerAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.TrainerAdapter;
import com.support.sport.sportsupport.ViewPackage.R;

public class TrainerManagementScreen extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_management_screen);

        RecyclerView recyclerView = findViewById(R.id.trainers_list);

        Trainer t1 = new Trainer("Ahmet","Keser","ahmet_123");
        Trainer t2 = new Trainer("Ahmet","Keser","ahmet_123");
        Trainer t3 = new Trainer("Ahmet","Keser","ahmet_123");
        Trainer t4 = new Trainer("Ahmet","Keser","ahmet_123");

        Trainer[] trainerList = new Trainer[4];
        trainerList[0] = t1;
        trainerList[1] = t2;
        trainerList[2] = t3;
        trainerList[3] = t4;

        TrainerAdapter trainerAdapter = new TrainerAdapter(trainerList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(trainerAdapter);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonTrainer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrainerManagementScreen.this,TrainerAddScreen.class);
                startActivity(i);
            }
        });




    }
}
