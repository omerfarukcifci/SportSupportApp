package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by Merve on 3.05.2018.
 */

public class MyProfile extends AppController {

    public List<ActivityPlan> loadSchedule(int memberId){

        Call<List<ActivityPlan>> cCall = apiService.getMySchedule(memberId);
        cCall.enqueue(new Callback<List<ActivityPlan>>() {
            @Override
            public void onResponse(Call<List<ActivityPlan>> call, Response<List<ActivityPlan>> response) {
                Key.memberSchedule = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<List<ActivityPlan>> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
        return Key.memberSchedule;

    }

    public Member updateProfileInfo(int memberId, String name, String surname, String username, String newpassword,
                                    String mail, String age)
    {
        Call<Member> memberCall = apiService.updateProfile(memberId,name,surname,username,newpassword,mail,age);
        memberCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.updatedMember = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });

        return Key.updatedMember;
    }

    public Member cancelMembership(String username, String endDate){

        Call<Member> memCall = apiService.cancelMembership(username,endDate);
        memCall.enqueue(new Callback<Member>() { 
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.cancelledMember = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });


        return Key.cancelledMember;
    }



}
