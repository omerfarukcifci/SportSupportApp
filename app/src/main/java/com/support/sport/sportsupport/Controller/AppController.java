package com.support.sport.sportsupport.Controller;

/**
 * Created by Merve on 25.04.2018.
 */

public class AppController {

    public static ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

}
