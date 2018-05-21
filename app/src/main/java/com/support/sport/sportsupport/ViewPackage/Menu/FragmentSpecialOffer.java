package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
        View specialOffer = inflater.inflate(R.layout.fragment_special_offer,container,false);

      /*  RecyclerView recyclerView = specialOffer.findViewById(R.id.special_offers_list);

        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = dateformat.parse("27/05/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SpecialOffer s1 = new SpecialOffer("Platin membership is 100 ₺ special for you","27/05/2018","27/05/2018",25);
        SpecialOffer s2 = new SpecialOffer("Standard membership is 50 ₺ special for you","27/05/2018","27/05/2018",15);
        SpecialOffer s3 = new SpecialOffer("Gold membership is %25 off special for you","27/05/2018","27/05/2018",60);
        SpecialOffer s4 = new SpecialOffer("Platin membership is %30 off special for you","27/05/2018","27/05/2018",50);

        SpecialOffer[] specialOffers = new SpecialOffer[4];
        specialOffers[0]=s1;
        specialOffers[1]=s2;
        specialOffers[2]=s3;
        specialOffers[3]=s4;

        SpecialOfferAdapter offerAdapter = new SpecialOfferAdapter(specialOffers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(offerAdapter);

        return specialOffer;


        */


        v = inflater.inflate(R.layout.fragment_special_offer,container,false);

        //  UserController uCon = new UserController();
        new UserController().getBranchId(Key.cMember.getId());
         // uCon.getBranchId(Key.cMember.getId());



      //  new SpecialOfferController().getBranchId(Key.cMember.getId());
        return v;

        //return specialOffer;
    }
}
