package com.example.sports_reachmobi.model;
import com.google.gson.annotations.SerializedName;

public class Sports_Item_Model
{
    @SerializedName("strSport")
    String sportName;

    @SerializedName("strFormat")
    String sportFormat;

    @SerializedName("strSportThumb")
    String sportThumb;

    public Sports_Item_Model(String sportName, String sportFormat, String sportThumb) {
        this.sportName = sportName;
        this.sportFormat = sportFormat;
        this.sportThumb = sportThumb;
    }

    public String getSportName() {
        return sportName;
    }

    public String getSportFormat() {
        return sportFormat;
    }

    public String getSportThumb() {
        return sportThumb;
    }
}
