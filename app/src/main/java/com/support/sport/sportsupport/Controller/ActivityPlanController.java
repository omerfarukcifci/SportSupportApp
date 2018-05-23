package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Move;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faruk on 21.05.2018.
 */

public class ActivityPlanController extends AppController{

    public void getMySchedule(int memberId){
        Call<List<ActivityPlan>> planCall = apiService.getMySchedule(memberId);
        planCall.enqueue(new Callback<List<ActivityPlan>>() {
            @Override
            public void onResponse(Call<List<ActivityPlan>> call, Response<List<ActivityPlan>> response) {
                Key.memberSchedule=response.body();
                Log.d("success","Spring success activity plan");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<List<ActivityPlan>> call, Throwable t) {
                Log.d("failure","Spring error GET SCHEDULE");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }

    public void getAllMovements(){
        Call<List<Move>> planCall = apiService.getAllMoves();
        planCall.enqueue(new Callback<List<Move>>() {
            @Override
            public void onResponse(Call<List<Move>> call, Response<List<Move>> response) {
                Key.allMovements=response.body();
                Log.d("success","Spring success movements");
                EventBus.getDefault().post(new RetrofitEvent(true,0));
            }
            @Override
            public void onFailure(Call<List<Move>> call, Throwable t) {
                Log.d("failure","Spring error GET ALL MOVE");
                EventBus.getDefault().post(new RetrofitEvent(false,0));
            }
        });
    }



}
