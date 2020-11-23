package com.example.sports_reachmobi.model;

import com.example.sports_reachmobi.DI.DaggerApiComponent;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SportsService
{
    //private static final String BASE_URL = "https://www.thesportsdb.com";

    private static SportsService instance;


    //helps in injecting mock object for the purpose of testing
    @Inject
    public SportsApi api;

   /* private SportsApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(SportsApi.class);*/

    private SportsService()
    {

        DaggerApiComponent.create().inject(this);
    }
    public static SportsService getInstance(){
        if(instance == null)
        {
            instance = new SportsService();
        }
        return instance;
    }
   public Single<Sports_List> getSports()
    {
        return api.getSportsList();

    }
}
