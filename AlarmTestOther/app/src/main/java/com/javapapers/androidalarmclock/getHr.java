package com.javapapers.androidalarmclock;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.HRData;
import com.fitbit.api.models.HeartRate;
import com.fitbit.api.models.HRLogs;
import com.fitbit.api.services.HRService;
import com.fitbit.api.services.UserService;
import android.content.Loader;
import java.util.List;

public class getHr extends Activity {

    public static Intent newIntent(Context context) {
        return new Intent(context, getHr.class);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_get_hr);
    }



    }







