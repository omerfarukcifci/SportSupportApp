package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.Adapter.ManagerAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.UserAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.UserAddScreen;
import com.support.sport.sportsupport.ViewPackage.R;

public class UserManagementScreen extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_management_screen);

        RecyclerView recyclerView = findViewById(R.id.users_list);

        Member m1 = new Member("Ahmet","Çelik","ahmet.celik1234","Active","Gold");
        Member m2 = new Member("Ahmet","Çelik","ahmet.celik1234","Active","Gold");
        Member m3 = new Member("Ahmet","Çelik","ahmet.celik1234","Active","Gold");
        Member m4 = new Member("Ahmet","Çelik","ahmet.celik1234","Active","Gold");
        Member m5 = new Member("Ahmet","Çelik","ahmet.celik1234","Active","Gold");

        Member[] memberList = new Member[5];
        memberList[0] = m1;
        memberList[1] = m2;
        memberList[2] = m3;
        memberList[3] = m4;
        memberList[4] = m5;

        UserAdapter userAdapter = new UserAdapter(memberList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(userAdapter);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonUser);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserManagementScreen.this,UserAddScreen.class);
                startActivity(i);
            }
        });



    }
}
