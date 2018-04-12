package com.support.sport.sportsupport.services;

import java.util.List;

import com.support.sport.sportsupport.models.Member;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Faruk on 12.04.2018.
 */

public interface PostService {
    @GET("all")
    Call<List<Member>> getAllPosts();

    @GET("all/{id}")
    Call<List<Member>> getPostById(@Path("id") int id);

    @POST("all")
    Call<Member> createPost(@Body Member member);
}
