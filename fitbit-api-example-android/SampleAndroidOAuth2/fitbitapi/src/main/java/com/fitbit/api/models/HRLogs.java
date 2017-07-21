package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 7/13/2017.
 */

public class HRLogs {
   @SerializedName("activities-heart-intraday")
    @Expose
    private HeartRate heartrate = new HeartRate();

//    /**
//     * @return The heartrate
//     */
//    public List<HeartRate> getHeartRate() {
//        return heartrate;
//    }
//
//    /**
//     * @param heartrate The rate
//     */
//    public void setHeartrate(List<HeartRate> heartrate) {
//        this.heartrate = heartrate;
//    }

}
