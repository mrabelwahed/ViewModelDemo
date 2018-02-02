package com.ramadan_apps.archcomponentsdemo.home;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramadan_apps.archcomponentsdemo.R;
import com.ramadan_apps.archcomponentsdemo.home.model.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud Ramadan on 2/2/18.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoVH> {

    private List<Repo> data = new ArrayList<>();


    public RepoAdapter(ListViewModel viewModel , LifecycleOwner owner){
        viewModel.getRepos().observe(owner , repos -> {
           if (repos !=null){
               data.addAll(repos);
           }
           notifyDataSetChanged();
        });

        setHasStableIds(true);
    }

    @Override
    public RepoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo,parent,false);
        return new RepoVH(view);
    }

    @Override
    public void onBindViewHolder(RepoVH holder, int position) {
        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public long getItemId(int position) {
        return  data.get(position).id;
    }

    public static class RepoVH extends RecyclerView.ViewHolder {

        @BindView(R.id.repo_name)
        TextView repoName;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.fork)
        TextView fork;
        @BindView(R.id.star)
        TextView star;

        public RepoVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Repo repo) {
         repoName.setText(repo.name);
         description.setText(repo.description);
         fork.setText(String.valueOf(repo.forks));
         star.setText(String.valueOf(repo.stars));

        }
    }
}
