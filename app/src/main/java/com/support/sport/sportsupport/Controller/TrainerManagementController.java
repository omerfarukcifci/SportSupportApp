package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Trainer;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import android.util.Log;
import android.widget.Toast;

import com.support.sport.sportsupport.Model.ClassMemberList;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrainerManagementController extends AppController {


    public void createTrainer(String name, String surname, String username, String password, int branchId){
        Call<Trainer> regCall = apiService.registerTrainer(name,surname,username, password,branchId);
        regCall.enqueue(new Callback<Trainer>() {
            @Override
            public void onResponse(Call<Trainer> call, Response<Trainer> response) {
                Key.newTrainer = response.body();
            }
            @Override
            public void onFailure(Call<Trainer> call, Throwable t) {
                Log.d("failure","Spring error Trainnneeeer");
            }
        });
    }

    public void allTrainers(int branchId){

        Call<List<Trainer>> trainers = apiService.allTrainers( branchId);
        trainers.enqueue(new Callback<List<Trainer>>() {
            @Override
            public void onResponse(Call<List<Trainer>> call, Response<List<Trainer>> response) {
                Key.allTrainers = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<List<Trainer>> call, Throwable t) {
                Log.d("failure","Spring error ALLL Trainers");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });


    }


}
