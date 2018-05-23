package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.support.sport.sportsupport.ViewPackage.Adapter.UserCourseAdapter;
import com.support.sport.sportsupport.ViewPackage.Management.FragmentManagementPanel;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import com.support.sport.sportsupport.ViewPackage.SignInScreen;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MyCoursesScreen extends AppCompatActivity {

    TextView textView ;
    UserCourseAdapter mAdapter;
    boolean enteredCrate = false;
    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            textView = findViewById(R.id.coursetext);
            RecyclerView recyclerView = findViewById(R.id.my_courses_list);
            if (Key.myClist == null){
                textView.setText("You have not enrolled to any courses yet.\nYou can see our open courses from Courses tab.");
            }else {
                textView.setText("You have enrolled to "+Key.myClist.size()+" courses so far.");
                mAdapter = new UserCourseAdapter(Key.myClist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(MyCoursesScreen.this,FragmentCourse.class);
                        intent.putExtra("MyCourse",Key.myClist.get(position));
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
        enteredCrate = true;
        if (Key.cMember.getStatue().equals("banned") || Key.cMember.getStatue().equals("inactive")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyCoursesScreen.this);
            alertDialogBuilder.setTitle("Courses");
            alertDialogBuilder
                    .setMessage("You can't see your courses since you are not a member. Please become a meember from profile page.")
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
        setContentView(R.layout.activity_my_courses_screen);
        new CourseController().getMyCourses(Key.cMember.getId());
    }

    @Override
    public void onStart() {
        super.onStart();  // Always call the superclass method first
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Key.userMyCListChanged && !enteredCrate){
            mAdapter.setList(Key.myClist);
            mAdapter.notifyDataSetChanged();
            Key.userMyCListChanged = false;
            enteredCrate = false;
        }

    }


}