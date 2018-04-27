package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;

/**
 * Created by Merve on 25.04.2018.
 */

public class FragmentAllCourses extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_courses,container,false);

        RecyclerView recyclerView = v.findViewById(R.id.courses_list);

        Course c1 = new Course("ZUMBA",50,40,"Monday","24/05/2019","Lewis Carroll","This Zumba Class is given by our new trainer. We hope to enjoy the class together! Join us on every monday!");

        Course c2 = new Course("PILATES",30,10,"Month","24/05/2019","Johnathan Swift","This class will be perfect for you. Give it a try, we hope to see you every month!");

        Course c3 = new Course("SWIMMING",20,10,"Sunday","24/07/2018","Jane Swift","This swimming class is just as you wanted, fun adn relaxing! Hurry up before quota gets full!");

        final Course[] courses = new Course[3];
        courses[0] = c1;
        courses[1] = c2;
        courses[2] = c3;

        CourseAdapter mAdapter = new CourseAdapter(courses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(),FragmentCourse.class);
                intent.putExtra("MyCourse",courses[position]);
                intent.putExtra("category",0);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return v;

    }
}
