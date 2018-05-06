package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.UserController;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Merve on 25.04.2018.
 */

public class FragmentAllCourses extends Fragment {

    View v;
    Course[] coursesm ;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if (event.pID == 1){
            CourseController courseController = new CourseController();
            courseController.showCourses(Key.cMemberList.getBranchId());
        }else{
            if(event.isRetrofitCompleted){
                RecyclerView recyclerView = v.findViewById(R.id.courses_list);
                coursesm = new Course[Key.allClist.size()];
                coursesm = Key.allClist.toArray(coursesm);
                CourseAdapter mAdapter = new CourseAdapter(Key.allClist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(getContext(),FragmentCourse.class);
                        intent.putExtra("MyCourse",coursesm[position]);
                      ///  intent.putExtra("category",0);
                        startActivity(intent);
                    }
                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            }else{
                Toast.makeText(getContext(), "Invalid",Toast.LENGTH_LONG).show();
            }
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_all_courses,container,false);
        new UserController().getBranchId(Key.cMember.getId());
        return v;

    }

    @Override
    public void onResume(){
        if (Key.courseUpdated){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            Key.courseUpdated = false;
        }
        super.onResume();
    }


}