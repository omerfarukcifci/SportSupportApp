package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ActivityPlanController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.ViewPackage.Adapter.ActivityPlanAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.ManagerAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faruk on 21.05.2018.
 */

public class ShowActivityPlanScreen extends AppCompatActivity{

    private TextView memberName;
    private int memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_member_plan_screen);
        Intent i = getIntent();
        memberId= i.getIntExtra("id",0);

        ActivityPlanController controller = new ActivityPlanController();
        controller.getMySchedule(memberId);
        Log.d("onCreate","oncreteGETMYSCHEDULEEEE");

    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){

            RecyclerView recyclerView = findViewById(R.id.show_schedule_list_trainee);
            memberName= (TextView) findViewById(R.id.show_plan_trainee_member_name);

            //memberName.setText(Key.cMember.getName()+" "+Key.cMember.getSurname());

            //Burada activity planları Key içindeki activity planlardan çekmemiz gerekiyor.

            ActivityPlan[] planList = new ActivityPlan[Key.memberSchedule.size()];

            for(int i=0;i<Key.memberSchedule.size();i++){
                planList[i] = Key.memberSchedule.get(i);
            }


            ActivityPlanAdapter activityPlanAdapter = new ActivityPlanAdapter(planList,1);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(activityPlanAdapter);



        }else{

            Toast.makeText(this,"HATA-ShowActivityPlan",Toast.LENGTH_SHORT).show();
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


}
