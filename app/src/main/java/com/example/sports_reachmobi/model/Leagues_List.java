package com.example.sports_reachmobi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Leagues_List
{
    @SerializedName("countrys")
    //@SerializedName("leagues")
    public ArrayList<Leagues_Item_Model> leaguesList;

    public ArrayList<Leagues_Item_Model> getLeaguesList() {
        return leaguesList;
    }
}
