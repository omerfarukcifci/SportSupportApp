package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.SpecialOfferController;
import com.support.sport.sportsupport.Controller.UserController;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.Adapter.CourseAdapter;
import com.support.sport.sportsupport.ViewPackage.Adapter.RecyclerTouchListener;
import com.support.sport.sportsupport.ViewPackage.Adapter.SpecialOfferAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentSpecialOffer extends Fragment {


    View v;
    SpecialOffer[] soffers ;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {


        if (event.pID == 1) {
            SpecialOfferController soController = new SpecialOfferController();
            soController.membersSpecialOffer(Key.cMemberList.getMemberId());
        } else {
            if (event.isRetrofitCompleted) {
                if (Key.membersSpecialOffer.size()==0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setTitle("Courses");
                    alertDialogBuilder
                            .setMessage("There is not any special offers announced in this branch currently.Please come back later.")
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

                RecyclerView recyclerView = v.findViewById(R.id.special_offers_list);
                soffers = new SpecialOffer[Key.membersSpecialOffer.size()];
                soffers = Key.membersSpecialOffer.toArray(soffers);
                SpecialOfferAdapter mAdapter = new SpecialOfferAdapter(soffers);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
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
        v = inflater.inflate(R.layout.fragment_special_offer,container,false);
        if (Key.cMember.getStatue().equals("banned") || Key.cMember.getStatue().equals("inactive")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Offers");
            alertDialogBuilder
                    .setMessage("You can't see offers since you are not a member. Please become a meember from profile page.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().onBackPressed();
                            //startActivity(new Intent(getContext(),CustomerNavigationMenu.class));
                            //new CustomerNavigationMenu().displayView(R.id.nav_my_profile);
                            /*FragmentManager fm = getFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            FragmentMyProfile llf = new FragmentMyProfile();
                            ft.replace(R.id.layout_all_courses, llf);
                            ft.commit();*/
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return v;
        }
        new UserController().getBranchId(Key.cMember.getId());
        return v;
    }
}
