package com.javapapers.androidalarmclock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.HeartRate;
import com.fitbit.api.models.HRLogs;
import com.fitbit.api.services.HRService;
import com.fitbit.api.services.UserService;

public class getHr extends Activity {

    public static Intent newIntent(Context context) {
        return new Intent(context, getHr.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_hr);
    }


    public void ok() {


    }
    }



