package com.support.sport.sportsupport.Controller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Member;

import java.util.Date;
import java.util.List;

/**
 * Created by Faruk on 13.04.2018.
 */

public interface ApiInterface {

    @GET("member/get")
    Call<Member> getMemberWithUsernamePassword(@Query("username") String username, @Query("password") String password);
    @GET("member/add")
    Call<Member> registerMember(@Query("name") String name, @Query("surname") String surname, @Query("username") String username,
                                @Query("password") String password, @Query("mail") String mail, @Query("age") Date age);
    @GET("course/all/{id}")
    Call<Course> showAllCourses(@Path("id") int branchId);
    @GET("enrolledcourses/all/my/{id}")
    Call<Course> showMyCourses(@Path("id") int memberId);








    /*@GET("delete")
    Call<Integer> deleteMember(@Query("username") String username, @Query("password") String password);
    @GET("all")
    Call<List<Member>> getAllMembers();
    @GET("update/personalinfo")
    Call<Integer> updateMember(@Query("username") String username, @Query("password") String password,
                               @Query("newusername") String newusername,@Query("newpassword") String newpassword,
                               @Query("mail") String mail, @Query("name") String name, @Query("surname") String surname);
    @GET("add")
    Call<Integer> addMember(@Query("referenceNumber") int referenceNumber, @Query("branchAuthority") int branchAuthority,
                            @Query("username") String username,@Query("password") String password,
                            @Query("statue") String statue, @Query("status") String status,
                            @Query("mail") String mail, @Query("name") String name, @Query("surname") String surname);
    @GET("get")
    Call<Integer> getMember(@Query("username") String username, @Query("password") String password);*/
}