package com.example.sports_reachmobi.DI;

import com.example.sports_reachmobi.model.EventsService;
import com.example.sports_reachmobi.model.LeaguesService;
import com.example.sports_reachmobi.model.SportsService;
import com.example.sports_reachmobi.viewmodel.EventsViewModel;
import com.example.sports_reachmobi.viewmodel.HomeViewModel;
import com.example.sports_reachmobi.viewmodel.LeaguesViewModel;
//import com.example.sports_reachmobi.viewmodel.SportsListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent
{
    void inject(SportsService service);
    //void inject(SportsListViewModel listViewModel);
    void inject(HomeViewModel listViewModel);



    void inject(EventsService events_service);

    void inject(EventsViewModel eventlistViewModel);



    void inject(LeaguesService leagues_service);

    void inject(LeaguesViewModel leaguelistViewModel);


}
