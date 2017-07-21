
package com.fitbit.sampleandroidoauth2.fragments;


import com.fitbit.api.loaders.ResourceLoaderResult;

import com.fitbit.api.models.HRData;
import com.fitbit.api.models.HRLogs;

import com.fitbit.api.services.HRService;
import com.fitbit.sampleandroidoauth2.R;

import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jboggess on 10/17/16.
 */

public class HeartLogsFragment extends InfoFragment<HRLogs> {
Double x = 0.0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        binding.webview.setVisibility(View.GONE);
        binding.graph.setVisibility(View.VISIBLE);

        return v;

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
        int restingrate = heartLogs.getResting();
        int sleeprate = restingrate - 10;
        List<HRData> rates = heartLogs.getHRData();
      DataPoint[] dataPoints = new DataPoint[rates.size()];
        int sleepyet = 0;
       int lowpoint = 100;
        int highpoint = 0;
        String sleeptime = "sleeptime";
       for (int i = 0; i < rates.size(); i++) {
           int val = (rates.get(i)).getValue();
            if ((val > highpoint) && (val) < 250){highpoint = val;}
           if ((val < lowpoint) && (val) > 20 ){lowpoint = val;}
           if ((sleepyet == 0) && val < sleeprate){sleeptime = (rates.get(i)).getTime(); sleepyet = 1; }
          dataPoints[i] =  new DataPoint(x,(double) (rates.get(i)).getValue());
            x += 1;
        }

        Log.i("LOWPOINT", Integer.toString(lowpoint));
        Log.i("HIGHPOINT", Integer.toString(highpoint));
        Log.i("SLEEPYET", Integer.toString(sleepyet));
        Log.i("RESTINGRATE", Integer.toString(restingrate));
        Log.i("SLEEPTIME", sleeptime);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
       binding.graph.addSeries(series);

       binding.graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
       binding.graph.getGridLabelRenderer().setNumHorizontalLabels(3);

       binding.graph.getViewport().setMinX(dataPoints[0].getX());
     binding.graph.getViewport().setMaxX(dataPoints[dataPoints.length - 1].getX());
        binding.graph.getViewport().setXAxisBoundsManual(true);

        binding.graph.getGridLabelRenderer().setHumanRounding(false);

    }



}
