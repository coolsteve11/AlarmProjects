package com.fitbit.api.services;

import android.app.Activity;
import android.content.Loader;

import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.exceptions.TokenExpiredException;
import com.fitbit.api.loaders.ResourceLoaderFactory;
import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.HRLogs;
import com.fitbit.authentication.Scope;

import java.util.Date;

/**
 * Created by Stephen on 7/13/2017.
 */

public class HRService {
//https://api.fitbit.com/1/user/-/activities/heart/date/today/1d/1sec.json
        private final static String HR_URL = "https://api.fitbit.com/1/user/-/activities/heart/date/today/1d/1sec.json";
    private static final ResourceLoaderFactory<HRLogs> HR_LOG_LOADER_FACTORY = new ResourceLoaderFactory<>(HR_URL, HRLogs.class);


    public static Loader<ResourceLoaderResult<HRLogs>> getHRLogLoader(Activity activityContext) throws MissingScopesException, TokenExpiredException {
                return HR_LOG_LOADER_FACTORY.newResourceLoader(
                activityContext,
                new Scope[]{Scope.heartrate}
                );
    }



}
