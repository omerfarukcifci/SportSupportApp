package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.MyProfile;
import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Adapter.ActivityPlanAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.Management.FragmentManagementPanel;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import com.support.sport.sportsupport.ViewPackage.SignInScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MyScheduleScreen extends AppCompatActivity {

    private TextView moveName;
    private TextView moveSet;
    private TextView memberName;
    int memberId;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){

            RecyclerView recyclerView = findViewById(R.id.my_schedule_list_trainee);
            memberName= (TextView) findViewById(R.id.my_plan_trainee_member_name);

            //memberName.setText(Key.cMember.getName()+" "+Key.cMember.getSurname());

            //Burada activity planları Key içindeki activity planlardan çekmemiz gerekiyor.

            ActivityPlan[] planList = new ActivityPlan[Key.memberSchedule.size()];

            for(int i=0;i<Key.memberSchedule.size();i++){
                planList[i] = Key.memberSchedule.get(i);
            }


            ActivityPlanAdapter activityPlanAdapter = new ActivityPlanAdapter(planList,2);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(activityPlanAdapter);

            if(Key.memberSchedule.size()!=0){
                //Toast.makeText(this, "valid",Toast.LENGTH_LONG).show();
                Snackbar.make(recyclerView,"Your activity schedule is loaded succesfully.",Snackbar.LENGTH_LONG).show();
                Intent i = new Intent(this,MyProfile.class);
                startActivity(i);
            }
            else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyScheduleScreen.this);
                alertDialogBuilder.setTitle("Schedule Error");
                alertDialogBuilder
                        .setMessage("Sorry! You have not activity schedule. Consult your trainer")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        }else{
            Toast.makeText(this, "Invalid",Toast.LENGTH_LONG).show();
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

        if (Key.cMember.getStatue().equals("banned") || Key.cMember.getStatue().equals("inactive")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyScheduleScreen.this);
            alertDialogBuilder.setTitle("Courses");
            alertDialogBuilder
                    .setMessage("You can't see a schedule assigned by a trainer since you are not a member. Please become a member from profile page.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return;
        }

        setContentView(R.layout.activity_my_schedule_screen);


        //moveName = findViewById(R.id.move_name_1);
        //moveSet = findViewById(R.id.move_set_1);

        Intent i = getIntent();
        memberId= i.getIntExtra("id",0);


        MyProfile controller = new MyProfile();
        controller.loadSchedule(memberId);

    }
}
