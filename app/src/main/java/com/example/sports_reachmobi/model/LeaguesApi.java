package com.example.sports_reachmobi.model;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface LeaguesApi
{
    @GET("api/v1/json/1/search_all_leagues.php?c=England")
    Single<Leagues_List> getLeaguesList();

}
