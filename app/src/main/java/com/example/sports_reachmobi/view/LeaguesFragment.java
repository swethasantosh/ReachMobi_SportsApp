package com.example.sports_reachmobi.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sports_reachmobi.AdapterClasses.LeaguesListAdapter;
import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.viewmodel.LeaguesViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class LeaguesFragment extends Fragment
{
    Context context;
    public View mView;
    FragmentActivity listener;
    //@BindView(R.id.id_homeRecyclerView)
    // RecyclerView sportsList;
    @BindView(R.id.id_leaguesLayout)
    LinearLayout linearLayout;

    RecyclerView leaguesList;

    private LeaguesListAdapter adapter = new LeaguesListAdapter(new ArrayList<>());

    public LeaguesFragment(){}

    private LeaguesViewModel mViewModel;

    public static LeaguesFragment newInstance() {
        return new LeaguesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.leagues_fragment, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getContext(),"meassage",Toast.LENGTH_LONG).show();

        //ButterKnife.bind((Activity) getContext());
        leaguesList = mView.findViewById(R.id.id_leaguesRecyclerView);

        mViewModel = ViewModelProviders.of(this).get(LeaguesViewModel.class);
        mViewModel.refresh();
        //mViewModel.SearchId("4328");

        leaguesList.setLayoutManager(new LinearLayoutManager(context));
        leaguesList.setAdapter(adapter);

        observerViewModel();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void observerViewModel()
    {

        mViewModel.leagues.observe(getActivity(),leaguesModels ->
        {
            if(leaguesModels != null)
            {
                leaguesList.setVisibility(View.VISIBLE);
                adapter.updateLeagues(leaguesModels.getLeaguesList());
            }
        });
         /*viewModel.sportLoadError.observe(this, isError ->{
             if(isError != null){
                 textError.setVisibility(isError ? View.VISIBLE : View.GONE);
             }
         } );
         viewModel.loading.observe(this, isLoading ->{
             if(isLoading != null)
             {
                 progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                 if(isLoading)
                 {
                     textError.setVisibility(View.GONE);
                     sportsList.setVisibility(View.GONE);
                 }
             }
         });*/
    }
}

