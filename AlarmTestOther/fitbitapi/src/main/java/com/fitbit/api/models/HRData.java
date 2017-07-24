package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by carsa on 7/20/2017.
 */

public class HRData {

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("value")
    @Expose
    private Integer value;

    public String getTime(){return time;}
    public Integer getValue(){return value;}


}
