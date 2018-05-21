package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Model.Trainer;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManagementController extends AppController{

    public void deleteMember(int memberId){
        Call<Member> regCall = apiService.deleteMember(memberId);
        regCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.deletedMember = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.d("failure","Spring error Delete Manager Delete Manager");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
    }
    public void allMembers(int branchId){

        Call<List<Member>> managers = apiService.allMembers(branchId);
        managers.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                Key.allMembers = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,0));
            }
            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.d("failure","Spring error ALLL MANAGERSSS");
                EventBus.getDefault().post(new RetrofitEvent(false,0));
            }
        });


    }


}
