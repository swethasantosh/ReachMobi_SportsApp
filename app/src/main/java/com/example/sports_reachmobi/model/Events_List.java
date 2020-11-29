package com.example.sports_reachmobi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Events_List
{
    @SerializedName("events")
    //@SerializedName("leagues")
    public ArrayList<Event_Item_Model> eventsList;

    public ArrayList<Event_Item_Model> getEventsList() {
        return eventsList;
    }
}
