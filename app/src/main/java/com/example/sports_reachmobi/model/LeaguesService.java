package com.example.sports_reachmobi.model;

import com.example.sports_reachmobi.DI.DaggerApiComponent;

import javax.inject.Inject;

import io.reactivex.Single;

public class LeaguesService
{
    //private static final String BASE_URL = "https://www.thesportsdb.com";

    private static LeaguesService instance;


    //helps in injecting mock object for the purpose of testing
    @Inject
    public LeaguesApi api;

   /* private SportsApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(SportsApi.class);*/

    private LeaguesService()
    {

        //DaggerApiComponent.create().inject(this);
        DaggerApiComponent.create().inject(this);
    }
    public static LeaguesService getInstance(){
        if(instance == null)
        {
            instance = new LeaguesService();
        }
        return instance;
    }
    public Single<Leagues_List> getLeagues()
    {
        return api.getLeaguesList();

    }


}
