package com.ramadan_apps.archcomponentsdemo.home.network;

import com.ramadan_apps.archcomponentsdemo.home.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mahmoud Ramadan on 2/2/18.
 */

public interface RepoService {
    @GET("orgs/twitter/repos ")
    Call<List<Repo>> getRepos();
}
