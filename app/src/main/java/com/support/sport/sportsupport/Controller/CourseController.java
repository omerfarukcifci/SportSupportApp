package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.google.gson.JsonObject;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.ClassMemberList;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Merve on 2.05.2018.
 */

public class CourseController extends AppController{

    public List<Course> showCourses(int branchId){
        Call<List<Course>> cCall = apiService.showAllCourses(branchId);
        cCall.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Key.allClist = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true,0));
            }
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false,0));
            }
        });
        return Key.allClist;
    }

    public List<Course> getMyCourses(int memberId){
        Call<List<Course>> cCall = apiService.showMyCourses(memberId);
        cCall.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Key.myClist = response.body();
                Log.d("success","Spring success cour");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
        return Key.myClist;
    }

    public Boolean isEnrolledCheck(int memberId, final Course c){
        Call<List<Course>> cCall = apiService.showMyCourses(memberId);
        cCall.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Key.myClist = response.body();
                Key.isEnrolled = false;
                for (Course c1 : Key.myClist){
                    if (c1.getId()==c.getId()) Key.isEnrolled = true;
                }
                Log.d("success","Spring success cour");
                EventBus.getDefault().post(new RetrofitEvent(true,0));
            }
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Key.isEnrolled = false;
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false,0));
            }
        });
        return Key.isEnrolled;
    }



    public void getCourse(int courseId){
        //final Member[] m = new Member[1];
        Call<Course> courseCall = apiService.getOneCourse(courseId);
        //Key.cMember = memberCall.execute().body();
        courseCall.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Key.oneCourse = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
            }
        });
    }


    public void enrollCourse(int courseId,int memberId){
        //final Member[] m = new Member[1];
        Call<ClassMemberList> courseCall = apiService.enrollCourse(courseId,memberId);
        //Key.cMember = memberCall.execute().body();
        courseCall.enqueue(new Callback<ClassMemberList>() {
            @Override
            public void onResponse(Call<ClassMemberList> call, Response<ClassMemberList> response) {
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<ClassMemberList> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
    }


    public void dropCourse(int courseId,int memberId){
        //final Member[] m = new Member[1];
        Call<ClassMemberList> courseCall = apiService.dropCourse(courseId,memberId);
        //Key.cMember = memberCall.execute().body();
        courseCall.enqueue(new Callback<ClassMemberList>() {
            @Override
            public void onResponse(Call<ClassMemberList> call, Response<ClassMemberList> response) {
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<ClassMemberList> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
    }

    public Course addNewCourse(String name,int quota,int traninerId,
                             int branchId,String startDate,String endDate,
                             String description,String species){
        Call<Course> courseCall = apiService.addCourse(name,quota,traninerId,branchId,startDate,endDate,null,description,species);
        courseCall.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Key.addedCourse = response.body();
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true,1));
            }
            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false,1));
            }
        });
        return Key.addedCourse;
    }

    public void deleteCourse(int id){
        Call<Course> courseCall = apiService.deleteCourse(id);
        courseCall.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Log.d("success","Spring success");
                EventBus.getDefault().post(new RetrofitEvent(true,-1));
            }
            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false,-1));
            }
        });
    }

}