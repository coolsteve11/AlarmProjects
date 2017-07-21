package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by Stephen on 7/13/2017.
 */

public class HeartRate1 {

    @SerializedName("value")
    @Expose
    private HRData1 value;


public HRData1 getValue(){return value;}
    public Integer getResting(){return value.getRestingHeartRate();}

}


