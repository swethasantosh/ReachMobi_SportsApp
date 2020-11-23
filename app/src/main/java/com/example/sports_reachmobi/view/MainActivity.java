package com.example.sports_reachmobi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.viewmodel.SportsListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.sportsList)
    RecyclerView sportsList;
    @BindView(R.id.textError)
    TextView textError;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    private SportsListViewModel viewModel;

    private SportListAdapter adapter = new SportListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(SportsListViewModel.class);
        viewModel.refresh();

        sportsList.setLayoutManager(new LinearLayoutManager(this));
        sportsList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);

        });

        observerViewModel();
    }
    private void observerViewModel()
    {

        viewModel.sports.observe(this,sportsModels ->
        {
            if(sportsModels != null)
            {
                sportsList.setVisibility(View.VISIBLE);
                adapter.updateSports(sportsModels.getSportsList());
            }
        });
        viewModel.sportLoadError.observe(this, isError ->{
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
        });
    }
}