package com.example.sports_reachmobi.DI;

import com.example.sports_reachmobi.model.SportsService;
import com.example.sports_reachmobi.viewmodel.SportsListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent
{
    void inject(SportsService service);

    void inject(SportsListViewModel listViewModel);


}
