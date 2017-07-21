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


 @SerializedName("activities-heart")
 @Expose
 private ArrayList<HeartRate1> hrlistthing = new ArrayList<HeartRate1>();


public HeartRate getHeartrate(){return heartrate;}

 public ArrayList<HeartRate1> getHeartrate1(){return hrlistthing;}

 public Integer getResting(){return (hrlistthing.get(0)).getResting();}

 public List<HRData> getHRData(){return heartrate.getDataset();}

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
