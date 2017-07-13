package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Stephen on 7/13/2017.
 */

public class HeartRate {



    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("value")
    @Expose
    private Integer value;

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param value The value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return The value
     */
    public Integer getValue() {
        return value;
    }



    }

