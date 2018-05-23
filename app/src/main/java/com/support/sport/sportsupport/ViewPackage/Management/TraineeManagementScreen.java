package com.support.sport.sportsupport.ViewPackage.Management;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.TraineeManagementController;
import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.Adapter.ActivityPlanAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.TraineeAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TraineeManagementScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainee_management_screen);

        TraineeManagementController controller = new TraineeManagementController();
        controller.getAllTrainee(Key.cTrainer.getId());
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){

            RecyclerView recyclerView = findViewById(R.id.trainee_list);

            Member[] memberList = new Member[Key.allTrainees.size()];

            for(int i =0;i<Key.allTrainees.size();i++){
                memberList[i] = Key.allTrainees.get(i);
            }

            if(Key.allTrainees.size()==0){
                Snackbar.make(recyclerView,"Sorry! You have not any Trainee.",Snackbar.LENGTH_LONG).show();
            }
            else{
                TraineeAdapter traineeAdapter = new TraineeAdapter(memberList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(traineeAdapter);
            }
      }else{
            Toast.makeText(this,"ERROR - TRAINE MANAGAMENT SCREEN",Toast.LENGTH_SHORT).show();
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
