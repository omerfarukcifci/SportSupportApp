package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.Fee;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by Faruk on 3.05.2018.
 */

public class BranchManagementController extends AppController {

    public Branch addBranch(String name, int quota, long phoneNumber, String city, String district, String address, final Fee fee){

        Call<Branch> regCall = apiService.createBranch(name,quota,phoneNumber,city,district,address);
        regCall.enqueue(new Callback<Branch>() {
            @Override
            public void onResponse(Call<Branch> call, Response<Branch> response) {
                Key.addedBranch = response.body();
                Log.d("success","Spring succes ADDBRANCH-1");
                Call<Fee> feeSave = apiService.saveFeeList(fee.getWeeklyClass(),fee.getOneTimeClass(),fee.getPoolMembership()
                        ,fee.getStandardMembership(),fee.getGoldMembership(),fee.getPlatinumMembership(),Key.addedBranch.getId());
                feeSave.enqueue(new Callback<Fee>() {
                    @Override
                    public void onResponse(Call<Fee> call, Response<Fee> response) {
                        Log.d("success","Spring succes ADDBRANCH-2");
                        EventBus.getDefault().post(new RetrofitEvent(true));
                    }
                    @Override
                    public void onFailure(Call<Fee> call, Throwable t) {
                        Log.d("failure","Spring error ADDBRANCH-2");
                        EventBus.getDefault().post(new RetrofitEvent(false));
                    }
                });

            }
            @Override
            public void onFailure(Call<Branch> call, Throwable t) {
                Log.d("failure","Spring error ADDBRANCH-1");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
        return Key.addedBranch;
    }

    public Branch deleteBranch(int id){

        Call<Branch> regCall = apiService.deleteBranch(id);
        regCall.enqueue(new Callback<Branch>() {
            @Override
            public void onResponse(Call<Branch> call, Response<Branch> response) {
                Key.deletedBranch = response.body();
                Log.d("success","Spring succes ADDBRANCH");
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<Branch> call, Throwable t) {

                Log.d("failure","Spring error ADDBRANCH");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
        return Key.deletedBranch;
    }

    public List<Branch> showAllBranch(){
        Call<List<Branch>> regCall = apiService.showBranches();
        regCall.enqueue(new Callback<List<Branch>>() {
            @Override
            public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {
                Key.allBranches = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,0));
            }
            @Override
            public void onFailure(Call<List<Branch>> call, Throwable t) {

                Log.d("failure","Spring error ADDBRANCH");
                EventBus.getDefault().post(new RetrofitEvent(false,0));
            }
        });
        return Key.allBranches;
    }


}
