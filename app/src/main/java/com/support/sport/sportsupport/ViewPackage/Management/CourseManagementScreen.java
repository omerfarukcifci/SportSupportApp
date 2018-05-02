package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Adapter.BranchAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.R;

public class CourseManagementScreen extends AppCompatActivity {

    private FloatingActionButton fab;
    private ImageButton courseDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_management_screen);



        RecyclerView recyclerView = findViewById(R.id.course_list_management);


        long phone = 0312231234;
        Course c1 = new Course("Zumba",50,20,"Monday","25/07/2018","Trainer Ahmet","Zumba zumba");
        Course c2 = new Course("Zumba",50,20,"Monday","25/07/2018","Trainer Ahmet","Zumba zumba");
        Course c3 = new Course("Zumba",50,20,"Monday","25/07/2018","Trainer Ahmet","Zumba zumba");
        Course c4 = new Course("Zumba",50,20,"Monday","25/07/2018","Trainer Ahmet","Zumba zumba");


        Course[] courseList = new Course[4];
        courseList[0] = c1;
        courseList[1] = c2;
        courseList[2] = c3;
        courseList[3] = c4;

        CourseAdapter courseAdapter = new CourseAdapter(courseList);

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
}
