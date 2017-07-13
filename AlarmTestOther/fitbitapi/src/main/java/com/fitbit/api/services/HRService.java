package com.fitbit.api.services;
import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.exceptions.TokenExpiredException;
import com.fitbit.api.loaders.ResourceLoaderFactory;
import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.HRLogs;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.content.Loader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Stephen on 7/13/2017.
 */

public class HRService {

    private final static String HR_URL = "https://api.fitbit.com/1/user/-/activities/heart/date/today/1d/1sec.json";
    private static final ResourceLoaderFactory<HRLogs> HR_LOG_LOADER_FACTORY = new ResourceLoaderFactory<>(HR_URL, HRLogs.class);


    public static Loader<ResourceLoaderResult<HRLogs>> getHRLogLoader(Activity activityContext, Date startDate, int calendarDateType, int number) throws MissingScopesException, TokenExpiredException {


        return HR_LOG_LOADER_FACTORY.newResourceLoader(
                activityContext,
                new Scope[]{Scope.heartrate}
                );
    }



}
