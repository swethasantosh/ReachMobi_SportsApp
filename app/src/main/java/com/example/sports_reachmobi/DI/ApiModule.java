package com.example.sports_reachmobi.DI;

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

}
