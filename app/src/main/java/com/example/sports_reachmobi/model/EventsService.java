package com.example.sports_reachmobi.model;


import com.example.sports_reachmobi.DI.DaggerApiComponent;

import javax.inject.Inject;

import io.reactivex.Single;

public class EventsService
{
    //private static final String BASE_URL = "https://www.thesportsdb.com";

    private static EventsService instance;


    //helps in injecting mock object for the purpose of testing
    @Inject
    public EventsApi api;



   /* private SportsApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(SportsApi.class);*/

    private EventsService()
    {
        //DaggerApiComponent.create().inject(this);
        DaggerApiComponent.create().inject(this);
    }

    public static EventsService getInstance(){
        if(instance == null)
        {
            instance = new EventsService();
        }
        return instance;
    }
    public Single<Events_List> getEvents()

    {
        return api.getEventsList();

    }


  /*  public Single<Sports_List> getSportsId(String id)
    {
        return api.getSearchResponse(id);
    }
*/

}

