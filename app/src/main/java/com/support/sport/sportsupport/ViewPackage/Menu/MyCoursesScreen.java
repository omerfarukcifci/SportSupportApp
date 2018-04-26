package com.support.sport.sportsupport.ViewPackage.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.R;

public class MyCoursesScreen extends AppCompatActivity {

    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses_screen);

        textView = findViewById(R.id.coursetext);

        RecyclerView recyclerView = findViewById(R.id.my_courses_list);

        Course c1 = new Course("ZUMBA",50,40,"Monday","24/05/2019");

        Course c2 = new Course("PILATES",30,10,"Month","24/05/2019");

        final Course[] courses = new Course[2];
        courses[0] = c1;
        courses[1] = c2;

        final Course[] coursestry = null;

        if (courses == null){
            textView.setText("You have not enrolled to any courses yet.\nYou can see our open courses from Courses tab.");
        }else {
            textView.setText("You have enrolled to "+courses.length+" courses so far.");
            CourseAdapter mAdapter = new CourseAdapter(courses);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
           /* recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Toast.makeText(getApplicationContext(), position+ " is selected successfully", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));*/
        }
    }
}
