package com.support.sport.sportsupport.Controller;

import android.util.Log;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.Model.Trainer;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialOfferController extends AppController {

    public void allSpecialOffers(int branchId){

        Call<List<SpecialOffer>> soffers = apiService.getSpecialOffers( branchId);
        soffers.enqueue(new Callback<List<SpecialOffer>>() {
            @Override
            public void onResponse(Call<List<SpecialOffer>> call, Response<List<SpecialOffer>> response) {
                Key.allSpecialOffers = response.body();
                EventBus.getDefault().post(new RetrofitEvent(true,0));
            }
            @Override
            public void onFailure(Call<List<SpecialOffer>> call, Throwable t) {
                Log.d("failure","Spring error All Special Offers");
                EventBus.getDefault().post(new RetrofitEvent(false,0));
            }
        });


    }


    public void createSpecialOffer(String name, String branchId, String startDate, String finishDate, String discountAmount, String referenceNumberLimit, String atttendanceLimit){
        Call<SpecialOffer> regCall = apiService.registerSpecialOffer(name,branchId,startDate, finishDate,discountAmount,referenceNumberLimit,atttendanceLimit);
        regCall.enqueue(new Callback<SpecialOffer>() {
            @Override
            public void onResponse(Call<SpecialOffer> call, Response<SpecialOffer> response) {
                Key.newSpecialOffer = response.body();
                Log.d("SUCCCCCCESSSSSSSSSSSSSS","Spring error Trainnneeeer");
            }
            @Override
            public void onFailure(Call<SpecialOffer> call, Throwable t) {
                Log.d("failure","Spring error Trainnneeeer");
            }
        });
    }


    public void deleteSpecialOffer(int specialOfferId){
        Call<SpecialOffer> regCall = apiService.deleteSpecialOffer(specialOfferId);
        regCall.enqueue(new Callback<SpecialOffer>() {
            @Override
            public void onResponse(Call<SpecialOffer> call, Response<SpecialOffer> response) {
                Key.deletedSpecialOffer = response.body();
            }
            @Override
            public void onFailure(Call<SpecialOffer> call, Throwable t) {
                Log.d("failure","Spring error Delete Manager Delete Manager");
            }
        });
    }


    public void applySpecialOffer(int specialOfferId, int memberId){
        Call<SpecialOffer> regCall = apiService.applySpecialOffer(specialOfferId,memberId);
        regCall.enqueue(new Callback<SpecialOffer>() {
            @Override
            public void onResponse(Call<SpecialOffer> call, Response<SpecialOffer> response) {
                Key.appliedSpecialOffer = response.body();
            }
            @Override
            public void onFailure(Call<SpecialOffer> call, Throwable t) {
                Log.d("failure","Spring error Applied SPecial OFfer");
            }
        });
    }

}
