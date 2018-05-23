package com.support.sport.sportsupport.ViewPackage.Management;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_plan_screen);

        ActivityPlanController controller = new ActivityPlanController();
        controller.getAllMovements();
        Log.d("onCreate","oncreteGETMYSCHEDULEEEE");




    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){

            RecyclerView recyclerView = findViewById(R.id.add_schedule_list_trainee);
            addToMember = (Button) findViewById(R.id.add_plan_to_member_button);

            final TraineeManagementController controller = new TraineeManagementController();
            addToMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // DEĞİŞCEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEKK !!!!!!!!!

                    for(int i =0 ; i<Key.selectedMovements.size();i++){
                        int moveid=0;
                        for(int j=0; j<Key.allMovements.size();j++){
                            if(Key.selectedMovements.get(i).getName().compareTo(Key.allMovements.get(j).getName())==0){
                                moveid = Key.allMovements.get(j).getId();
                            }
                        }

                        controller.setMovement(moveid,23,Key.selectedMovements.get(i).getSetNumber());//23 değişcek.

                    }
                    Toast.makeText(view.getContext(),""+Key.selectedMovements.get(1).getSetNumber(),Toast.LENGTH_LONG).show();
                    Key.selectedMovements = new ArrayList<Move>();
                }
            });

            Move[] moveList = new Move[Key.allMovements.size()];
            for(int i=0;i<Key.allMovements.size();i++){
                moveList[i] = Key.allMovements.get(i);
            }




            ActivityPlanAdapter activityPlanAdapter = new ActivityPlanAdapter(moveList,0);


            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(activityPlanAdapter);



        }else{

            Toast.makeText(this,"HATA - AddActivityPlan",Toast.LENGTH_SHORT).show();
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
