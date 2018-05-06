package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Adapter.BranchAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CourseManagementScreen extends AppCompatActivity {

    private FloatingActionButton fab;
    private ImageButton courseDelete;
    private RecyclerView recyclerView;
    private Course[] coursesm;
    private CourseAdapter courseAdapter;

    @Subscribe
    public void onEvent(RetrofitEvent event){

        if (event.pID==-1){
            if (event.isRetrofitCompleted){
                courseAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(this,"Delete process failed",Toast.LENGTH_LONG).show();
            }
        }else{
            if (event.isRetrofitCompleted){
                recyclerView = findViewById(R.id.course_list_management);
                courseAdapter = new CourseAdapter(Key.allClist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(courseAdapter);
                fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonCourse);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(CourseManagementScreen.this,CourseAddScreen.class);
                        startActivity(i);
                    }
                });
            }
            else {
                Toast.makeText(getApplicationContext(), "The course list is empty!",Toast.LENGTH_LONG).show();
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_management_screen);

        CourseController courseController = new CourseController();
        courseController.showCourses(Key.cManager.getBranchId());

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
