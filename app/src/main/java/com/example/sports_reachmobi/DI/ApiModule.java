package com.example.sports_reachmobi.DI;


import com.example.sports_reachmobi.model.EventsApi;
import com.example.sports_reachmobi.model.EventsService;
import com.example.sports_reachmobi.model.LeaguesApi;
import com.example.sports_reachmobi.model.LeaguesService;
import com.example.sports_reachmobi.model.SportsApi;
import com.example.sports_reachmobi.model.SportsService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//dagger component
@Module
public class ApiModule
{
    //private static final String BASE_URL = "https://www.thesportsdb.com";
    private static final String BASE_URL = "https://www.thesportsdb.com";

    @Provides
    public SportsApi provideSportsApi()
    {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(SportsApi.class);
    }

    @Provides
    public SportsService provideSportsService()
    {
        return SportsService.getInstance();

    }




    @Provides
    public EventsApi provideEventsApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(EventsApi.class);
    }

    @Provides
    public EventsService provideEventsService() {
        return EventsService.getInstance();

    }

    @Provides
    public LeaguesApi provideLeaguesApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(LeaguesApi.class);
    }

    @Provides
    public LeaguesService provideLeaguesService() {
        return LeaguesService.getInstance();
    }


}
