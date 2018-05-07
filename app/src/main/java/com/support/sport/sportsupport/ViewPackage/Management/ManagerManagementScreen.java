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
    ManagerAdapter managerAdapter;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if (event.pID==0){
            if(event.isRetrofitCompleted){
                RecyclerView recyclerView = findViewById(R.id.managers_list);
                managerAdapter = new ManagerAdapter(Key.allManagers);
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
            }else{
                Toast.makeText(this,"No managers to display",Toast.LENGTH_SHORT).show();
            }
        }else{
            if(event.isRetrofitCompleted){
                managerAdapter.notifyDataSetChanged();
                Toast.makeText(this,"Successfully Deleted!",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.manager_management_screen);
        ManagerManagementController mngrC = new ManagerManagementController();
        mngrC.allManagersWithBNames();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Key.manSetChanged){
            managerAdapter.setList(Key.allManagers);
            managerAdapter.notifyDataSetChanged();
            Key.manSetChanged = false;
        }

    }
}
