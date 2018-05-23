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


    public void createManager(String name, String surname, String username, String password, int branchId, final String branchName){
        Call<Manager> regCall = apiService.registerManager(name,surname,username, password,branchId);
        regCall.enqueue(new Callback<Manager>() {
            @Override
            public void onResponse(Call<Manager> call, Response<Manager> response) {
                Key.newManager = response.body();
                Key.newManager.setBrancName(branchName);
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<Manager> call, Throwable t) {
                Log.d("failure","Spring error MANAGERMANAGERGMANAGER");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
    }


    public void deleteManager(String managerId){
        Call<Manager> regCall = apiService.deleteManager(managerId);
        regCall.enqueue(new Callback<Manager>() {
            @Override
            public void onResponse(Call<Manager> call, Response<Manager> response) {
                Key.deletedManager = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,1));

            }
            @Override
            public void onFailure(Call<Manager> call, Throwable t) {
                Log.d("failure","Spring error Delete Manager Delete Manager");
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
        });
    }


    public void allManagers(){

        Call<List<Manager>> managers = apiService.allManagers();
        managers.enqueue(new Callback<List<Manager>>() {
            @Override
            public void onResponse(Call<List<Manager>> call, Response<List<Manager>> response) {
                Key.allManagers = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<List<Manager>> call, Throwable t) {
                Log.d("failure","Spring error ALLL MANAGERSSS");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });


    }
    public void allManagersWithBNames(){

        Call<List<Manager>> managers = apiService.allManagersWithBranchNames();
        managers.enqueue(new Callback<List<Manager>>() {
            @Override
            public void onResponse(Call<List<Manager>> call, Response<List<Manager>> response) {
                Key.allManagers = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,0));
            }
            @Override
            public void onFailure(Call<List<Manager>> call, Throwable t) {
                Log.d("failure","Spring error ALLL MANAGERSSS");
                EventBus.getDefault().post(new RetrofitEvent(false,0));
            }
        });


    }

}
