package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephen on 7/21/2017.
 */

public class HRData1 {



    @SerializedName("restingHeartRate")
    @Expose
    private Integer restingHeartRate;

public Integer getRestingHeartRate(){
    if (restingHeartRate != null){
    return restingHeartRate;
    }
    else{return 0;}
}}
