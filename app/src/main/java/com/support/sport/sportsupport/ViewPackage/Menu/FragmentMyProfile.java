package com.support.sport.sportsupport.ViewPackage.Menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Faruk on 23.04.2018.
 */

public class FragmentMyProfile extends Fragment {

    Button buttonMySchedule,buttonMyCourses,buttonBecomeMember,buttonCancelMembership;
    TextView profileName,memberStatus;
    Member member;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_profile,container,false);
        /*if (Key.updatedProfile){
            reload();
        }*/
        member = Key.cMember;
        profileName = rootView.findViewById(R.id.myprofile_name);
        memberStatus = rootView.findViewById(R.id.myprofile_status);
        profileName.setText("WELCOME "+member.getName().toUpperCase()+" "+member.getSurname().toUpperCase());
        memberStatus.setText("MEMBERSHIP STATUS : "+member.getStatus().toUpperCase());

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

    @Override
    public void onResume(){
        if (Key.updatedProfile){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            member = Key.cMember;
            Key.updatedProfile = false;
        }
        super.onResume();
    }

}
