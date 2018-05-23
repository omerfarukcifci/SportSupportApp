package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import com.support.sport.sportsupport.ViewPackage.Adapter.UserCourseAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Merve on 25.04.2018.
 */

public class FragmentAllCourses extends Fragment {

    View v;
    Date timeStamp = Calendar.getInstance().getTime();
    UserCourseAdapter mAdapter;
    boolean enteredCreate = true;

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
                if (Key.allClist.size()==0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setTitle("Courses");
                    alertDialogBuilder
                            .setMessage("There is not any open courses in this branch currently.Please come back later.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().onBackPressed();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                    return;
                }
                RecyclerView recyclerView = v.findViewById(R.id.courses_list);
                mAdapter = new UserCourseAdapter(Key.allClist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position){
                        Course c = Key.allClist.get(position);
                        try {
                            if (timeStamp.after(new SimpleDateFormat("yyyy-MM-dd").parse(c.getEndDate()))){
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                                alertDialogBuilder.setTitle("Closed Course");
                                alertDialogBuilder
                                        .setMessage("This course is closed.Please try other courses.")
                                        .setCancelable(true)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }else{
                                Intent intent = new Intent(getContext(),FragmentCourse.class);
                                intent.putExtra("MyCourse",Key.allClist.get(position));
                                startActivity(intent);
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            }else{
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
        enteredCreate = true;
        if (Key.cMember.getStatue().equals("banned") || Key.cMember.getStatue().equals("inactive")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Courses");
            alertDialogBuilder
                    .setMessage("You can't see open courses since you are not a member. Please become a meember from profile page.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().onBackPressed();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return v;
        }
        new UserController().getBranchId(Key.cMember.getId());
        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Key.userCourseListChanged && !enteredCreate){
            mAdapter.setList(Key.allClist);
            mAdapter.notifyDataSetChanged();
            enteredCreate = false;
            Key.userCourseListChanged = false;
        }

    }


}