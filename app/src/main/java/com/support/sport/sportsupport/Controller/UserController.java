package com.support.sport.sportsupport.Controller;

import android.util.Log;
import android.widget.Toast;

import com.support.sport.sportsupport.Model.Member;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Merve on 2.05.2018.
 */

public class UserController extends AppController{

    public Member login(String username, String password) {
        final Member[] m = new Member[1];
        Call<Member> memberCall = apiService.getMemberWithUsernamePassword(username, password);
        //Key.cMember = memberCall.execute().body();
        memberCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                m[0] = response.body();
                Log.d("success","Spring success");
            }
            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Log.d("failure","Spring error");
            }
        });
        return m[0];
    }

    public void signIn(String name, String surname, String username, String password, String mail, Date age){
        Call<Member> regCall = apiService.registerMember(name,surname,username, password,mail,age);
        regCall.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Key.cMember = response.body();
            }
            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                //Toast.makeText("Spring Error",Toast.LENGTH_LONG).show();
            }
        });
    }


}
