package com.example.sports_reachmobi.view;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sports_reachmobi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity
{

   Fragment selectedFragment;


    //private SportListAdapter adapter = new SportListAdapter(new ArrayList<>());

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




 /*swipeRefreshLayout.setOnRefreshListener(() -> {
    viewModel.refresh();
    swipeRefreshLayout.setRefreshing(false);

});*/

}