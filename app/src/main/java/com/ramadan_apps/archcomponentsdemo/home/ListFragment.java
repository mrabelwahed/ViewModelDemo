package com.ramadan_apps.archcomponentsdemo.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ramadan_apps.archcomponentsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mahmoud Ramadan on 2/2/18.
 */

public class ListFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_error)
    TextView errorView;
    @BindView(R.id.progreesbar)
    ProgressBar loadingView;

    private ListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
         recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         recyclerView.setAdapter(new RepoAdapter(viewModel,this));
        observeViewModel();

    }

    private void observeViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if (repos != null){
                loadingView.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }
        });

        viewModel.getError().observe(this, isError ->{
            if (isError){
                loadingView.setVisibility(View.GONE);
                errorView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                errorView.setText("ERROR");
            }else{
                errorView.setVisibility(View.GONE);
                errorView.setText(null);
            }

        });


        viewModel.getLoading().observe(this,isLoading ->{
            loadingView.setVisibility(isLoading? View.VISIBLE: View.GONE);
            if (isLoading){
                recyclerView.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
