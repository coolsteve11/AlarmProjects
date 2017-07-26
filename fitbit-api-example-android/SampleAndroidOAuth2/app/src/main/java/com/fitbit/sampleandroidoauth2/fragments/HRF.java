
package com.fitbit.sampleandroidoauth2.fragments;


import com.fitbit.api.loaders.ResourceLoaderResult;

import com.fitbit.api.models.HRData;
import com.fitbit.api.models.HRLogs;

import com.fitbit.api.services.HRService;
import com.fitbit.sampleandroidoauth2.R;

import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jboggess on 10/17/16.
 */

public class HRF extends InfoFragment<HRLogs> {
    Double x = 0.0;
    static List<HRData> rates;
    int restingrate= 0;
    int sleeprate= 0;
    int lowpoint= 0;
    int highpoint = 0;
    String sleeptime = "";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public int getTitleResourceId() {
        return R.string.HR;
    }

    @Override
    protected int getLoaderId() {
        return 4;
    }

    @Override
    public Loader<ResourceLoaderResult<HRLogs>> onCreateLoader(int id, Bundle args) {
        return HRService.getHRLogLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<HRLogs>> loader, ResourceLoaderResult<HRLogs> data) {
        super.onLoadFinished(loader, data);
        if (data.isSuccessful()) {
            bindHeartLogs(data.getResult());

        }
    }


    public void bindHeartLogs(HRLogs heartLogs) {
        rates = heartLogs.getHRData();
        if (rates.size() == 0){  Log.e("NOINFOR", "RIP");}
        else {
            int restingrate = heartLogs.getResting();
            int sleeprate = restingrate - 5;

            DataPoint[] dataPoints = new DataPoint[rates.size()];
            int sleepyet = 0;
            int lowpoint = 100;
            int highpoint = 0;
            String sleeptime = "sleeptime";

            for (int i = 0; i < rates.size(); i++) {
                int val = (rates.get(i)).getValue();
                if ((val > highpoint) && (val) < 250) {
                    highpoint = val;
                }
                if ((val < lowpoint) && (val) > 20) {
                    lowpoint = val;
                }
                if ((sleepyet == 0) && val < sleeprate) {
                    sleeptime = (rates.get(i)).getTime();
                    sleepyet = 1;
                }
                dataPoints[i] = new DataPoint(x, (double) (rates.get(i)).getValue());
                x += 1;
            }

        }
    }


    public List<HRData> getRates(){return rates;}
    public int getlow(){return lowpoint;}
    public int gethigh(){return highpoint;}
    public int getresting(){return restingrate;}
    public int getsleeprate(){return sleeprate;}
    public String getTime(){return sleeptime;}


}
