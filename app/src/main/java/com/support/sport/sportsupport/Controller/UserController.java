package com.support.sport.sportsupport.Controller;

import android.util.Log;
import android.widget.Toast;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.Model.MemberList;
import com.support.sport.sportsupport.Model.Trainer;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Merve on 2.05.2018.
 */

public class UserController extends AppController{

    public void loginMember(String username, String password){
        //final Member[] m = new Member[1];
        Call<Member> memberCall = apiService.getMemberWithUsernamePassword(username, password);
        //Key.cMember = memberCall.execute().body();
        memberCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.cMember = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }
    public void loginManager(String username, String password){
        Call<Manager> memberCall = apiService.getManagerWithUsernamePassword(username, password);
        memberCall.enqueue(new Callback<Manager>() {
            @Override
            public void onResponse(Call<Manager> call, Response<Manager> response) {
                Key.cManager = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Manager> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }
    public void loginTrainer(String username, String password){
        //final Member[] m = new Member[1];
        Call<Trainer> memberCall = apiService.getTrainerWithUsernamePassword(username, password);
        //Key.cMember = memberCall.execute().body();
        memberCall.enqueue(new Callback<Trainer>() {
            @Override
            public void onResponse(Call<Trainer> call, Response<Trainer> response) {
                Key.cTrainer = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Trainer> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }

    public void signUp(String name, String surname, String username, String password, String mail, String age){
        Call<Member> regCall = apiService.registerMember(name,surname,username, password,mail,age);
        regCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.cMember = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                //Toast.makeText("Spring Error",Toast.LENGTH_LONG).show();
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }

    public void addUser(String name, String surname, String username, String password, String mail, String age){
        Call<Member> regCall = apiService.registerMember(name,surname,username, password,mail,age);
        regCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.addedMember = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                //Toast.makeText("Spring Error",Toast.LENGTH_LONG).show();
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }

    public void getBranchId(int id){
        Call<MemberList> regCall = apiService.getBranchId(id);
        regCall.enqueue(new Callback<MemberList>() {
            @Override
            public void onResponse(Call<MemberList> call, Response<MemberList> response) {
                Key.cMemberList = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<MemberList> call, Throwable t) {
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
    }




}
