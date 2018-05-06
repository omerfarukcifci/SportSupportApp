package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.UserController;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.Management.FragmentManagementPanel;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import com.support.sport.sportsupport.ViewPackage.SignInScreen;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MyCoursesScreen extends AppCompatActivity {

    TextView textView ;
    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
           // Toast.makeText(getApplicationContext(), Key.myClist.get(0).getName()+"",Toast.LENGTH_LONG).show();
            textView = findViewById(R.id.coursetext);
            RecyclerView recyclerView = findViewById(R.id.my_courses_list);
            final Course[] courses = new Course[Key.myClist.size()];

            for(int i = 0; i < Key.myClist.size();i++){
                courses[i] = Key.myClist.get(i);
            }

            if (courses == null){
                textView.setText("You have not enrolled to any courses yet.\nYou can see our open courses from Courses tab.");
            }else {
                textView.setText("You have enrolled to "+courses.length+" courses so far.");
                CourseAdapter mAdapter = new CourseAdapter(Key.myClist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(MyCoursesScreen.this,FragmentCourse.class);
                        intent.putExtra("MyCourse",courses[position]);
                        intent.putExtra("category",1);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

        }else{
            setContentView(R.layout.activity_my_courses_screen);
            textView = findViewById(R.id.coursetext);
       //     Toast.makeText(getApplicationContext(), "Invalid",Toast.LENGTH_LONG).show();
            textView.setText("You have not enrolled to any courses yet.\nYou can see our open courses from Courses tab.");
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses_screen);
        CourseController courseC = new CourseController();

        courseC.getMyCourses(Key.cMember.getId());
        Log.d("onCreate","onCreateonCreateonCreateonCreateonCreateonCreate");

/*
        textView = findViewById(R.id.coursetext);
        RecyclerView recyclerView = findViewById(R.id.my_courses_list);
        Course c1 = new Course("ZUMBA",50,40,"Monday","24/05/2019","Lewis Carroll","This Zumba Class is given by our new trainer. We hope to enjoy the class together! Join us on every monday!");
        Course c2 = new Course("PILATES",30,10,"Month","24/05/2019","Johnathan Swift","This class will be perfect for you. Give it a try, we hope to see you every month!");
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
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(MyCoursesScreen.this,FragmentCourse.class);
                    intent.putExtra("MyCourse",courses[position]);
                    intent.putExtra("category",1);
                    startActivity(intent);
                }
                @Override
                public void onLongClick(View view, int position) {
                }
            }));
        }
        */
    }

    @Override
    public void onStart() {
        super.onStart();  // Always call the superclass method first
        EventBus.getDefault().register(this);
        Log.d("onStart","startsatart");


    }


}