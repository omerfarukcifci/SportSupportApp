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
import com.support.sport.sportsupport.Controller.UserManagementController;
import com.support.sport.sportsupport.ViewPackage.Adapter.UserAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class UserManagementScreen extends AppCompatActivity {

    private FloatingActionButton fab;
    private UserAdapter userAdapter;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if (event.pID==0){
            if (event.isRetrofitCompleted) {
                RecyclerView recyclerView = findViewById(R.id.users_list);
                userAdapter = new UserAdapter(Key.allMembers);
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

            }else{
                Toast.makeText(getApplicationContext(),"There isn't any members registered to your branch!",Toast.LENGTH_SHORT).show();
            }
        }else if(event.pID!=6){
            if (event.isRetrofitCompleted) {
                userAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Member Successfully Deleted!" , Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Delete Process Failed!" , Toast.LENGTH_LONG).show();
            }
        }else{
            if (event.isRetrofitCompleted) {

                Toast.makeText(this, "Member Successfully Banned!" , Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Ban Process Failed!" , Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.user_management_screen);
        UserManagementController umController = new UserManagementController();
        umController.allMembers(Key.cManager.getBranchId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Key.memberSetChanged){
            userAdapter.setList(Key.allMembers);
            userAdapter.notifyDataSetChanged();
            Key.memberSetChanged = false;
        }
    }
}
