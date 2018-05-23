package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.BranchManagementController;
import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.BranchStats;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Menu.FragmentCourse;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FragmentBranchStats extends AppCompatActivity {

    TextView name, managerC, trainerC, standartMcount, goldMemberC,platinumMCount,courseStudentCount;
    Button enrolldrop;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            BranchStats thisBranchStats = Key.getSelectedBranchStats();
            name = findViewById(R.id.stat_branchName);
            managerC = findViewById(R.id.stat_manager_count);
            trainerC = findViewById(R.id.stat_trainer_count);
            standartMcount = findViewById(R.id.stat_standarM_count);
            goldMemberC = findViewById(R.id.stat_goldM_count);
            platinumMCount = findViewById(R.id.stat_platinM_count);
            courseStudentCount = findViewById(R.id.stat_course_student_count);

            name.setText(thisBranchStats.getName());
            managerC.setText(String.valueOf(thisBranchStats.getManagerCount()));
            trainerC.setText(String.valueOf(thisBranchStats.getTrainerCount()));
            standartMcount.setText(String.valueOf(thisBranchStats.getStandartMemberCount()));
            goldMemberC.setText(String.valueOf(thisBranchStats.getGoldMemberCount()));
            platinumMCount.setText(String.valueOf(thisBranchStats.getPlatinumMemberCount()));
            courseStudentCount.setText(String.valueOf(thisBranchStats.getCourseStudentCount()));



        }else{
            //    Toast.makeText(getApplicationContext(), "Invalid process",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_stats);



        Intent i = getIntent();
      //  final Branch c = (Branch) i.getSerializableExtra("SelectedBranch");
        //  int category =0;
        int branchId = (int) i.getSerializableExtra("SelectedBranch");
        BranchManagementController bmc = new BranchManagementController();
        bmc.viewBranchStats(branchId);








    }


}
