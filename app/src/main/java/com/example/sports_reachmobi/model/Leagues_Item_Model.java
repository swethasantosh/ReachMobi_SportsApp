package com.example.sports_reachmobi.model;

import com.google.gson.annotations.SerializedName;

public class Leagues_Item_Model
{
    @SerializedName("strLeague")
    String leagueName;
    @SerializedName("idLeague")
    String leadueId;
    @SerializedName("strBadge")
    String leagueImage;
    @SerializedName("strCountry")
    String leagueCountry;

    public Leagues_Item_Model(String leagueName, String leadueId, String leagueImage, String leagueCountry) {
        this.leagueName = leagueName;
        this.leadueId = leadueId;
        this.leagueImage = leagueImage;
        this.leagueCountry = leagueCountry;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeadueId() {
        return leadueId;
    }

    public String getLeagueImage() {
        return leagueImage;
    }

    public String getLeagueCountry() {
        return leagueCountry;
    }
}
