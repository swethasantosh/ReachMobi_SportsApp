package com.example.sports_reachmobi.model;


import com.google.gson.annotations.SerializedName;

public class Event_Item_Model
{
    @SerializedName("strSport")
    String sportName;
    @SerializedName("idEvent")
    String eventId;
    @SerializedName("strEvent")
    String eventName;
    @SerializedName("idLeague")
    String leagueId;
    @SerializedName("strLeague")
    String leagueName;
    @SerializedName("strThumb")
    String eventImage;

    public Event_Item_Model(String sportName, String eventId, String eventName, String leagueId, String leagueName, String eventImage) {
        this.sportName = sportName;
        this.eventId = eventId;
        this.eventName = eventName;
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.eventImage = eventImage;
    }

    public String getSportName() {
        return sportName;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getEventImage() {
        return eventImage;
    }
}
