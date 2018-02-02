package com.ramadan_apps.archcomponentsdemo.home.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Mahmoud Ramadan on 2/2/18.
 */

public class ApiRepo {
    private final static String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit;
    private static RepoService repoService;

    private ApiRepo() {

    }


    public static RepoService getInstance() {
        if (repoService != null)
            return repoService;
        if (retrofit == null)
            initRetrofit();
        repoService = retrofit.create(RepoService.class);
        return repoService;
    }

    private static  void initRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build();
    }

}
