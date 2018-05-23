package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.Fee;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by Merve on 22.05.2018.
 */

public class PaymentController extends AppController{

    public Fee loadMemberShipOptions(int branchId){

        Call<Fee> regCall = apiService.showFeeList(branchId);
        regCall.enqueue(new Callback<Fee>() {
            @Override
            public void onResponse(Call<Fee> call, Response<Fee> response) {
                Key.selectedBranchFee = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<Fee> call, Throwable t) {
                Log.d("failure","Spring error MANAGERMANAGERGMANAGER");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
        return Key.selectedBranchFee;
    }

    public Fee loadUpgradeOptions(int memberId){

        Call<Fee> regCall = apiService.loadUpgradeFee(memberId);
        regCall.enqueue(new Callback<Fee>() {
            @Override
            public void onResponse(Call<Fee> call, Response<Fee> response) {
                Key.selectedBranchFee = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<Fee> call, Throwable t) {
                Log.d("failure","Spring error upgrade");
                Key.selectedBranchFee = null;
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
        return Key.selectedBranchFee;
    }

    public boolean payNow(int id,String startDate,int branchId, String status){
        Call<Member> regCall = apiService.makePayment(id,startDate,branchId,status);
        regCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.cMember = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.d("failure","Spring error pay");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
        return true;
    }
}
