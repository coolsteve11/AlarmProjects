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

public class HeartRate {

    @SerializedName("datasetType")
    @Expose
    private String datasetType;

    @SerializedName("datasetInterval")
    @Expose
    private Integer datasetInterval;


    @SerializedName("dataset")
    @Expose
    private List<HRData> dataset;

    public String getDatasetType(){return datasetType;}
    public Integer getDatasetInterval(){return datasetInterval;}
    public List<HRData> getDataset(){return dataset;}


//    @SerializedName("value")
//    @Expose
//    private String value;



//      /**
//      * @return The dateTime
//     */
//       public String getDateTime() {
//       return dateTime;
//     }
//
//    /**
//     * @param  dateTime
//     */
//    public void setDateTime(String dateTime) {
//        this.dateTime =  dateTime;
//    }

//    /**
//     * @param value The value
//     */
//    public void setValue(String value) {
//        this.value = value;
//    }
//
//    /**
//     * @return The value
//     */
//    public String getValue() {
//        return value;
//    }


}