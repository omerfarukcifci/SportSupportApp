package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Faruk on 23.04.2018.
 */

public class FragmentMyProfile extends Fragment {

    Button buttonMySchedule,buttonMyCourses,buttonBecomeMember,buttonCancelMembership;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_profile,container,false);

        buttonMySchedule = (Button) rootView.findViewById(R.id.my_schedule_button);
        buttonMySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyScheduleScreen.class);
                startActivity(intent);
            }
        });

        buttonBecomeMember = (Button) rootView.findViewById(R.id.become_upgrade_membership_button);
        buttonBecomeMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MembershipOptionsScreen.class);
                startActivity(intent);
            }
        });

        buttonMyCourses = (Button) rootView.findViewById(R.id.my_courses_button);
        buttonMyCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyCoursesScreen.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
