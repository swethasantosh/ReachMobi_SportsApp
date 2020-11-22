package com.example.sports_reachmobi.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface SportsApi
{
    @GET("api/v1/json/1/all_sports.php")
    Single<Sports_List> getSportsList();
    //if there is any other end points we need to access it here,so we are separating base url into service class
}
