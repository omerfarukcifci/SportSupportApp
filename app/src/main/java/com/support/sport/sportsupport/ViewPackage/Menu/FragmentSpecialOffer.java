package com.support.sport.sportsupport.ViewPackage.Menu;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentSpecialOffer extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View specialOffer = inflater.inflate(R.layout.fragment_special_offer,container,false);

        RecyclerView recyclerView = specialOffer.findViewById(R.id.special_offers_list);

        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = dateformat.parse("27/05/2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SpecialOffer s1 = new SpecialOffer("Platin membership is 100 ₺ special for you",d,d,25);
        SpecialOffer s2 = new SpecialOffer("Standard membership is 50 ₺ special for you",d,d,15);
        SpecialOffer s3 = new SpecialOffer("Gold membership is %25 off special for you",d,d,60);
        SpecialOffer s4 = new SpecialOffer("Platin membership is %30 off special for you",d,d,50);

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
    }
}
