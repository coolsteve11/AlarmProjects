package com.javapapers.androidalarmclock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;



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




