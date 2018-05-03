package com.support.sport.sportsupport.ViewPackage;

/**
 * Created by Merve on 3.05.2018.
 */

public class RetrofitEvent {

    public boolean isRetrofitCompleted;
    public int pID = 0;
    public RetrofitEvent(boolean b){
        isRetrofitCompleted = b;
    }
    public RetrofitEvent(boolean b, int i) {
        isRetrofitCompleted = b;
        pID = i;
    }

}
