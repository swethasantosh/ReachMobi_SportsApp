package com.example.sports_reachmobi.model;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface EventsApi
{
    @GET("api/v1/json/1/eventsnext.php?id=133602")
    Single<Events_List> getEventsList();
}
