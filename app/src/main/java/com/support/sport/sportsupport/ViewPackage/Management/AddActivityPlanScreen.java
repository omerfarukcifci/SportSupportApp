package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ActivityPlanController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.TraineeManagementController;
import com.support.sport.sportsupport.Controller.TrainerManagementController;
import com.support.sport.sportsupport.Model.Move;
import com.support.sport.sportsupport.ViewPackage.Adapter.ActivityPlanAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class AddActivityPlanScreen extends AppCompatActivity  {

    private Button addToMember;
    private boolean check;
    private int memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        memberId= i.getIntExtra("id",0);
        setContentView(R.layout.add_plan_screen);
        ActivityPlanController controller = new ActivityPlanController();
        controller.getAllMovements();
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.pID==0){
            if(event.isRetrofitCompleted){
                RecyclerView recyclerView = findViewById(R.id.add_schedule_list_trainee);

                Move[] moveList = new Move[Key.allMovements.size()];
                for(int i=0;i<Key.allMovements.size();i++){
                    moveList[i] = Key.allMovements.get(i);
                }
                ActivityPlanAdapter activityPlanAdapter = new ActivityPlanAdapter(moveList,0);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(activityPlanAdapter);

                addToMember = (Button) findViewById(R.id.add_plan_to_member_button);
                addToMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new TraineeManagementController().deletePrev(memberId);
                    }
                });
            }else{
                Toast.makeText(this,"HATA - AddActivityPlan",Toast.LENGTH_SHORT).show();
            }
        }
        else if (event.pID==2){
            if (event.isRetrofitCompleted){
                ArrayList<Move> moveArrayList = new ArrayList<Move>();
                for (Move m : Key.selectedMovements){
                    moveArrayList.add(m);
                }
                new TraineeManagementController().setMovement(moveArrayList,memberId,Key.selectedMovements.size()-1);

            }else{
                Toast.makeText(this,"A problem occurred. Please try later.",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else{
            if (event.isRetrofitCompleted){
                Key.selectedMovements = new ArrayList<Move>();
                Toast.makeText(this,"Plan is assigned",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this,"A problem occurred. Please try later.",Toast.LENGTH_SHORT).show();
                Key.selectedMovements = new ArrayList<Move>();
                finish();
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



}
