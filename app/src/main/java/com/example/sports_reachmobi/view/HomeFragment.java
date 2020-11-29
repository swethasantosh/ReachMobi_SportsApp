package com.example.sports_reachmobi.view;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.model.HomeNavigator;
import com.example.sports_reachmobi.model.Sports_Item_Model;
import com.example.sports_reachmobi.viewmodel.HomeViewModel;

import java.util.ArrayList;

//public class HomeFragment extends Fragment {

public class HomeFragment extends Fragment implements HomeNavigator
{



    Context context;
    public View mView;
    FragmentActivity listener;
    //@BindView(R.id.id_homeRecyclerView)
    // RecyclerView sportsList;
    @BindView(R.id.id_homeLayout)
    LinearLayout linearLayout;

    RecyclerView sportList;

    private HomeViewModel mViewModel;


    private SportListAdapter adapter = new SportListAdapter(new ArrayList<>());



    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;


    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment, container, false);
        return mView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ButterKnife.bind((Activity) getContext());
        sportList = mView.findViewById(R.id.id_homeRecyclerView);

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);



        mViewModel.setNavigator(this );



        mViewModel.refresh();
        //mViewModel.SearchId("4328");

        sportList.setLayoutManager(new LinearLayoutManager(context));
        sportList.setAdapter(adapter);



        observerViewModel();


    }

    /* @Override
     public void onStart() {
         super.onStart();

     }




*/
    private void observerViewModel() {

        mViewModel.sports.observe(getActivity(), sportsModels ->
        {
            if (sportsModels != null) {
                sportList.setVisibility(View.VISIBLE);
                adapter.updateSports(sportsModels.getSportsList());
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


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.id_search);
        SearchView searchView = (SearchView) item.getActionView();

        // SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        // if (item != null) {
        //    searchView = (SearchView) item.getActionView();
        //}
        //if (searchView != null)
        //{
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        // queryTextListener = new SearchView.OnQueryTextListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                //searchViewModel.SearchId(query);
                //return false;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {

                adapter.getFilter().filter(newText);



                return true;
            }
        });
        //searchView.setOnQueryTextListener(queryTextListener);

        //}
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public void onItemClick(Sports_Item_Model sports_item_model)
    {
        new AlertDialog.Builder(getContext()).setMessage(sports_item_model.getSportName()).show();

    }
}
