package com.support.sport.sportsupport.ViewPackage.Management;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.SpecialOfferController;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.Adapter.SofferAdapter;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SpecialOfferManagementScreen extends AppCompatActivity {


    FloatingActionButton fab ;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            RecyclerView recyclerView = findViewById(R.id.soffer_list);
            SpecialOffer[] array = new SpecialOffer[Key.allSpecialOffers.size()];
            SofferAdapter soAdapter = new SofferAdapter(Key.allSpecialOffers.toArray(array));

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(soAdapter);
            fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonSpecialOffer);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(SpecialOfferManagementScreen.this,SpecialOfferAddScreen.class);
                    startActivity(i);
                }
            });
        }else{
            Toast.makeText(this,"No managers to display",Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soffer_management_screen);
        SpecialOfferController soController = new SpecialOfferController();
        soController.allSpecialOffers(Key.cManager.getBranchId());
    }

}
