package com.example.sports_reachmobi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Sports_List
{
    @SerializedName("sports")
    public ArrayList<Sports_Item_Model> sportsList;

    public ArrayList<Sports_Item_Model> getSportsList() {
        return sportsList;
    }
}
