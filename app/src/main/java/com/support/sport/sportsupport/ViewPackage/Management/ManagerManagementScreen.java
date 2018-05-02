package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.Adapter.ManagerAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.SpecialOfferAdapter;
import com.support.sport.sportsupport.ViewPackage.R;

public class ManagerManagementScreen extends AppCompatActivity {

    FloatingActionButton fab ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_management_screen);


        RecyclerView recyclerView = findViewById(R.id.managers_list);

        Manager m1 = new Manager("Ömer","Çifci","mr123",12);
        Manager m2 = new Manager("Ali","Balıkçı","ali234",5);
        Manager m3 = new Manager("Merve","Kantarcı","merve2",6);
        Manager m4 = new Manager("Baran","Sönmez","baran1",12);
        Manager m5 = new Manager("Sena","Ceylan","senaa",14);

        Manager[] managerlist = new Manager[5];
        managerlist[0] = m1;
        managerlist[1] = m2;
        managerlist[2] = m3;
        managerlist[3] = m4;
        managerlist[4] = m5;

        ManagerAdapter managerAdapter = new ManagerAdapter(managerlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(managerAdapter);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManagerManagementScreen.this,ManagerAddScreen.class);
                startActivity(i);
            }
        });


    }
}
