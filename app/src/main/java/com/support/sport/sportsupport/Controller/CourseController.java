package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import java.util.List;

import de.greenrobot.event.EventBus;
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
                EventBus.getDefault().post(new RetrofitEvent(true));
            }
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d("failure","Spring error");
                EventBus.getDefault().post(new RetrofitEvent(false));
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
                Log.d("success","Spring success");
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
}
