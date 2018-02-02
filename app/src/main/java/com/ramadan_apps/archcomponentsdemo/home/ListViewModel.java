package com.ramadan_apps.archcomponentsdemo.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.ramadan_apps.archcomponentsdemo.home.model.Repo;
import com.ramadan_apps.archcomponentsdemo.home.network.ApiRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmoud Ramadan on 2/2/18.
 */

public class ListViewModel extends ViewModel {
    private final MutableLiveData<List<Repo>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> errorLoadView = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingView = new MutableLiveData<>();

    private Call<List<Repo>> call;


    public ListViewModel() {
        fetchRepos();
    }


    public LiveData<List<Repo>> getRepos() {
        return repos;
    }

    public LiveData<Boolean> getError() {
        return errorLoadView;
    }

    public LiveData<Boolean> getLoading() {
        return loadingView;
    }

    private void fetchRepos() {
        loadingView.setValue(true);

        call = ApiRepo.getInstance().getRepos();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                errorLoadView.setValue(false);
                repos.setValue(response.body());
                loadingView.setValue(false);
                call = null;

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "error loading repos", t);
                errorLoadView.setValue(true);
                loadingView.setValue(false);
                call = null;
            }
        });

    }


    @Override
    protected void onCleared() {
        if (call == null) {
            call.cancel();
            call = null;
        }
    }
}
