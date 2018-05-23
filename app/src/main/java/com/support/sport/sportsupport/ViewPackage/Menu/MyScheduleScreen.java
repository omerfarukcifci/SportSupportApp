package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.DialogInterface;
import android.content.Intent;
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

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            List<ActivityPlan> a = Key.memberSchedule;
            moveName.setText(""+a.get(0).getName());
            moveSet.setText(""+a.get(0).getSets());
            Toast.makeText(this, "valid",Toast.LENGTH_LONG).show();

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
                    .setMessage("You can't see a schedule assigned by a trainer since you are not a member. Please become a meember from profile page.")
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


        moveName = findViewById(R.id.move_name_1);
        moveSet = findViewById(R.id.move_set_1);


        MyProfile controller = new MyProfile();
        controller.loadSchedule(23);

    }
}
