package com.support.sport.sportsupport.Controller;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.support.sport.sportsupport.Model.Member;
/**
 * Created by Faruk on 13.04.2018.
 */

public class ProfileController extends AppController{


    Member[] members = null;

    public Member findUser(int id){
        for (Member m : members){
            if (m.getId() == id) return m;
        }
        return null;
    }

    public Member[] getAll(){

        /****
         * GET ALL
         */
        Call<List<Member>> call = apiService.getAllMembers();
        call.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>>call, Response<List<Member>> response) {
                List<Member> memberlist = response.body();
                members = memberlist.toArray(new Member[memberlist.size()]);
            }

            @Override
            public void onFailure(Call<List<Member>>call, Throwable t) {
                // Log error here since request failed
                Log.e("hey", t.toString());
            }
        });

        return members;
    }

    public Member readOne(String username, String password) {
        /****
         * READ ONE
         */
        getAll();
        final Member[] m = new Member[1];
        Call<Integer> oneID = apiService.getMember(username, password);
        oneID.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer>oneID, Response<Integer> response) {
                m[0] = findUser(response.body());
                Log.d("hey", "Number of movies received: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer>oneID, Throwable t) {
                // Log error here since request failed
                Log.e("hey", t.toString());
            }
        });
        return m[0];
    }

    public void deleteMember(String username, String password){
        Call<Integer> call2 = apiService.deleteMember(username,password);
        call2.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer>call2, Response<Integer> response) {
            }

            @Override
            public void onFailure(Call<Integer>call2, Throwable t) {
                // Log error here since request failed
                Log.e("hey", t.toString());
            }
        });

    }

    public void updateMember(String username,String password,
                             String newusername,String newpassword,
                             String mail,String name,String surname){
        Call<Integer> call3 = apiService.updateMember(username,password,newusername,newpassword,mail,name,surname);
        call3.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call3, Response<Integer> response) {
            }

            @Override
            public void onFailure(Call<Integer>call3, Throwable t) {
                // Log error here since request failed
                Log.e("hey", t.toString());
            }
        });
    }

    public void addMember(int referenceNumber,int branchAuthority,
                          String username,String password,
                          String statue,String status,
                          String mail,String name,String surname){

        Call<Integer> call4 = apiService.addMember(referenceNumber,branchAuthority,username,password,statue,status,mail,name,surname);
        call4.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer>call4, Response<Integer> response) {
            }

            @Override
            public void onFailure(Call<Integer>call4, Throwable t) {
                // Log error here since request failed
                Log.e("hey", t.toString());
            }
        });

    }


}
