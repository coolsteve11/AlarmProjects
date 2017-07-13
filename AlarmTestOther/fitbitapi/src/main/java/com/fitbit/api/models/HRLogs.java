package com.fitbit.api.models;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Stephen on 7/13/2017.
 */

public class HRLogs {


    @SerializedName("Heartrate")
    @Expose
    private List<HeartRate> heartrate = new ArrayList<HeartRate>();

    /**
     * @return The rate
     */
    public List<HeartRate> getRate() {
        return heartrate;
    }

    /**
     * @param heartrate The rate
     */
    public void setRate(List<HeartRate> heartrate) {
        this.heartrate = heartrate;
    }

}
