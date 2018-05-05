package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.ManagerManagementController;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.ManagerAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.Adapter.SpecialOfferAdapter;
import com.support.sport.sportsupport.ViewPackage.Menu.FragmentCourse;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ManagerManagementScreen extends AppCompatActivity {

    FloatingActionButton fab ;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            final Manager[] managers = new Manager[Key.allManagers.size()];

            for(int i = 0; i < Key.allManagers.size();i++){
                managers[i] = Key.allManagers.get(i);
            }
            RecyclerView recyclerView = findViewById(R.id.managers_list);
            ManagerAdapter managerAdapter = new ManagerAdapter(managers);

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
        setContentView(R.layout.manager_management_screen);


      //  RecyclerView recyclerView = findViewById(R.id.managers_list);

        ManagerManagementController mngrC = new ManagerManagementController();
        mngrC.allManagers();



/*
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



*/
    }
}
