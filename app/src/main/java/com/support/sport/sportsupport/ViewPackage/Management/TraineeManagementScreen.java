package com.support.sport.sportsupport.ViewPackage.Management;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.Adapter.TraineeAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.UserAdapter;
import com.support.sport.sportsupport.ViewPackage.R;

public class TraineeManagementScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainee_management_screen);

        RecyclerView recyclerView = findViewById(R.id.trainee_list);

        Member m1 = new Member("Trainee","Ahmet","ahmet.celik1234","Active","Gold");
        Member m2 = new Member("Trainee","Mehmet","ahmet.celik1234","Active","Gold");
        Member m3 = new Member("Trainee","Ahmet","ahmet.celik1234","Active","Gold");
        Member m4 = new Member("Trainee","Ahmet","ahmet.celik1234","Active","Gold");
        Member m5 = new Member("Trainee","Mehmet","ahmet.celik1234","Active","Gold");

        Member[] memberList = new Member[5];
        memberList[0] = m1;
        memberList[1] = m2;
        memberList[2] = m3;
        memberList[3] = m4;
        memberList[4] = m5;

        TraineeAdapter traineeAdapter = new TraineeAdapter(memberList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(traineeAdapter);



    }
}
