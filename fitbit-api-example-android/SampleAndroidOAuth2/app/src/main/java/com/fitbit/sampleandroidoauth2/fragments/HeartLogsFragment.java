
package com.fitbit.sampleandroidoauth2.fragments;


import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.HeartRate;
import com.fitbit.api.models.HRLogs;
import com.fitbit.api.models.Weight;

import com.fitbit.api.services.HRService;
import com.fitbit.sampleandroidoauth2.R;

import android.content.Loader;
import android.os.Bundle;
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
        //List<HeartRate> rates = heartLogs.getHeartRate();
      //  DataPoint[] dataPoints = new DataPoint[rates.size()];

//        for (int i = 0; i < rates.size(); i++) {
//          //  HeartRate rate = rates.get(i);
//          // dataPoints[i] =  new DataPoint(x,(double) rate.getValue());
//           // x += 1;
//        }
      //  LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
//       binding.graph.addSeries(series);
//
//       binding.graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
//       binding.graph.getGridLabelRenderer().setNumHorizontalLabels(3);
//
//    //   binding.graph.getViewport().setMinX(dataPoints[0].getX());
//    // binding.graph.getViewport().setMaxX(dataPoints[dataPoints.length - 1].getX());
//        binding.graph.getViewport().setXAxisBoundsManual(true);
//
//        binding.graph.getGridLabelRenderer().setHumanRounding(false);

    }
}
