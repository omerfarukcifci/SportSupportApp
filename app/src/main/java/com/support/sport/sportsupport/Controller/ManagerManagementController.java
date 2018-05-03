package com.support.sport.sportsupport.Controller;


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


public class ManagerManagementController extends AppController{


    public void createManager(String name, String surname, String username, String password, int branchId){
        Call<Manager> regCall = apiService.registerManager(name,surname,username, password,branchId);
        regCall.enqueue(new Callback<Manager>() {
            @Override
            public void onResponse(Call<Manager> call, Response<Manager> response) {
                Key.newManager = response.body();
            }
            @Override
            public void onFailure(Call<Manager> call, Throwable t) {
                Log.d("failure","Spring error MANAGERMANAGERGMANAGER");
            }
        });
    }
}
