package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by Faruk on 3.05.2018.
 */

public class BranchManagementController extends AppController {

    public Branch addBranch(String name, int quota,long phoneNumber, String city,String district, String address){

        Call<Branch> regCall = apiService.createBranch(name,quota,phoneNumber,city,district,address);
        regCall.enqueue(new Callback<Branch>() {
            @Override
            public void onResponse(Call<Branch> call, Response<Branch> response) {
                Key.addedBranch = response.body();
                Log.d("success","Spring succes ADDBRANCH");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Branch> call, Throwable t) {

                Log.d("failure","Spring error ADDBRANCH");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
        return Key.addedBranch;
    }


}
