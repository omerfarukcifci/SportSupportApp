package com.support.sport.sportsupport.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.activities.R;
import com.support.sport.sportsupport.models.Member;
import com.support.sport.sportsupport.services.ApiClient;
import com.support.sport.sportsupport.services.ApiInterface;
import com.support.sport.sportsupport.services.CrudOPs;
import com.support.sport.sportsupport.services.PostService;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvUsername;
    private TextView tvStatus;
    private ListView listView;

    private List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        CrudOPs cr = new CrudOPs();

        //Member[] members = cr.getAll(apiService);
        //getAllPosts(apiService);

        /*String[] memberStr = new String[5];
        for(int j=0;j<5;j++){
            memberStr[j]= new String(memberList.get(j).getName()+" "+memberList.get(j).getSurname()
                    +" "+memberList.get(j).getUsername()+" "+memberList.get(j).getPassword());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, memberList);
        listView.setAdapter(adapter);*/


        //cr.addMember(apiService,0,0,"yeter4","artik","banned","standard","bitsin@gmail.com","nefret","ediyorum");
        //cr.deleteMember(apiService,"yeter4","artik");
        //cr.updateMember(apiService,"priasa","username9","username9","password9","bittimi@gmail.com",null,null);
        //cr.readOne(apiService,"yeter","artik");

        //getAllPosts(postService);
        //createPost(postService, newMember);



    }
    private void getAllPosts(ApiInterface apiService) {
        Call<List<Member>> getAllPostsCall = apiService.getAllMembers();
        getAllPostsCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                //displayPost(response.body().get(0));
                for(int i=0; i<response.body().size();i++){
                    memberList.add(response.body().get(i));
                }

            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.e(TAG, "Error occured while fetching post.");
            }
        });
    }



    /*private void createPost(PostService postService, Member newMember) {

        Call<Member> call = postService.createPost(newMember);
        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                displayPost(response.body());
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to create post" , Toast.LENGTH_LONG).show();
                Log.e(TAG,t.toString());
            }
        });
    }

    private void getAllPosts(PostService postService) {
        Call<List<Member>> getAllPostsCall = postService.getAllPosts();

        getAllPostsCall.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                displayPost(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.e(TAG, "Error occured while fetching post.");
            }
        });
    }

    private void initViews() {
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvSurname = (TextView) findViewById(R.id.tv_surname);
        this.tvUsername = (TextView) findViewById(R.id.tv_username);
        this.tvStatus = (TextView) findViewById(R.id.tv_status);
    }

    private void displayPost(Member member) {
        tvName.setText(member.getName());
        tvSurname.setText(member.getSurname());
        tvUsername.setText(member.getUsername());
        tvStatus.setText(member.getStatus());
    }
    */


}
