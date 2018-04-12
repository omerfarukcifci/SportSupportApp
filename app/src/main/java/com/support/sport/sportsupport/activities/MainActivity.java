package com.support.sport.sportsupport.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.activities.R;
import com.support.sport.sportsupport.models.Member;
import com.support.sport.sportsupport.services.PostService;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://207.154.246.182:8080/support-2.1/member/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostService postService = retrofit.create(PostService.class);

        //getAllPosts(postService);

        Member newMember = new Member();
        newMember.setName("Omer");
        newMember.setSurname("Cifci");
        newMember.setUsername("UserNameOmer");
        newMember.setPassword("123456");
        newMember.setMail("omer@gmail.com");
        newMember.setStatue("active");
        newMember.setStatus("gold");
        newMember.setBranchAuthority(0);
        newMember.setReferenceNumber(0);

        //createPost(postService, newMember);
        getAllPosts(postService);


    }

    private void createPost(PostService postService, Member newMember) {

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



}
