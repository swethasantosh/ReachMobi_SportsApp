package com.example.sports_reachmobi.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.sports_reachmobi.AdapterClasses.EventsListAdapter;
import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.viewmodel.EventsViewModel;

import java.util.ArrayList;

public class EventsFragment extends Fragment
{
    Context context;
    public View mView;
    FragmentActivity listener;
    //@BindView(R.id.id_homeRecyclerView)
    // RecyclerView sportsList;
    @BindView(R.id.id_eventsLayout)
    LinearLayout linearLayout;

    RecyclerView eventsList;

    private EventsListAdapter adapter = new EventsListAdapter(new ArrayList<>());

    public EventsFragment(){}

    private EventsViewModel mViewModel;

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.events_fragment, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getContext(),"meassage",Toast.LENGTH_LONG).show();

        //ButterKnife.bind((Activity) getContext());
        eventsList = mView.findViewById(R.id.id_eventsRecyclerView);

        mViewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
        mViewModel.refresh();
        //mViewModel.SearchId("4328");

        eventsList.setLayoutManager(new LinearLayoutManager(context));
        eventsList.setAdapter(adapter);

        observerViewModel();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void observerViewModel()
    {

        mViewModel.events.observe(getActivity(),eventsModels ->
        {
            if(eventsModels != null)
            {
                eventsList.setVisibility(View.VISIBLE);
                adapter.updateEvents(eventsModels.getEventsList());
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