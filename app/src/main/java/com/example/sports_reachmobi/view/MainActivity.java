package com.example.sports_reachmobi.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.sports_reachmobi.HomeFragment;
import com.example.sports_reachmobi.R;
//import com.example.sports_reachmobi.Shedule_Fragment;
//import com.example.sports_reachmobi.viewmodel.SportsListViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
   /* @BindView(R.id.sportsList)
    RecyclerView sportsList;
    @BindView(R.id.textError)
    TextView textError;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomAppBar;*/

   Fragment selectedFragment;


   // private SportsListViewModel viewModel;

    private SportListAdapter adapter = new SportListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.id_frameLayout,new HomeFragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {
            switch (menuItem.getItemId())
            {
                case R.id.id_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.id_schedule:
                    selectedFragment = new EventsFragment();
                    break;
                case R.id.id_leagues:
                    selectedFragment = new LeaguesFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.id_frameLayout,selectedFragment).commit();
            return true;
        }
    };







       /* ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(SportsListViewModel.class);
        viewModel.refresh();

        sportsList.setLayoutManager(new LinearLayoutManager(this));
        sportsList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);

        });

        observerViewModel();*/





        /*bottomAppBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                if(getSupportActionBar() != null)
                {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setHomeButtonEnabled(false);
                }

                switch (menuItem.getItemId())
                { case R.id.id_allSports:
                    HomeFragment selectedFragment = HomeFragment.newInstance();
                    break;
                    case R.id.id_event_schedule:
                        Shedule_Fragment scheduleFragment = Shedule_Fragment.newInstance();
                        break;

                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.constraintLayout,HomeFragment.newInstance());
                transaction.commit();
                return false;
            }
        });*/



/*

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

*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.id_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
               //viewModel.SearchId(query);
                //return false;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}