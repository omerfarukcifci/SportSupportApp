package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Model.Move;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faruk on 23.05.2018.
 */

public class TraineeManagementController extends AppController{

    public void getAllTrainee(int trainerId){
        Call<List<Member>> planCall = apiService.getAllTraineeWithTrainerId(trainerId);
        planCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                Key.allTrainees=response.body();
                Log.d("success","Spring success activity plan");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.d("failure","Spring error GET ALL TRAINEE");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }

    public void assignActivity(List<Move> moveList){

    }

    public void deletePrev(int memberId){

        Call<ActivityPlan> del = apiService.deletePrevActivity(memberId);
        del.enqueue(new Callback<ActivityPlan>() {
            @Override
            public void onResponse(Call<ActivityPlan> call, Response<ActivityPlan> response) {
                Log.d("tag","hey");
                EventBus.getDefault().post(new RetrofitEvent(false,2));
            }
            @Override
            public void onFailure(Call<ActivityPlan> call, Throwable t) {
                EventBus.getDefault().post(new RetrofitEvent(true,2));
            }
        });

    }

    public void setMovement(final ArrayList<Move> moveList, final int memberId, final int size){
        if (size==-1) return;
        Call<ActivityPlan> planCall = apiService.setMovementToTrainee(moveList.get(size).getId(),memberId,moveList.get(size).getSetNumber());
        planCall.enqueue(new Callback<ActivityPlan>() {
            @Override
            public void onResponse(Call<ActivityPlan> call, Response<ActivityPlan> response) {
                setMovement(moveList,memberId,size-1);
                Log.d("success","Spring success activity plan");
                if(size==0) EventBus.getDefault().post(new RetrofitEvent(true,1));

            }
            @Override
            public void onFailure(Call<ActivityPlan> call, Throwable t) {
                Log.d("failure","Spring error GET ALL TRAINEE");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
    }


}
