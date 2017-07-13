package com.javapapers.androidalarmclock;

import com.fitbit.authentication.AuthenticationConfiguration;
import com.fitbit.authentication.AuthenticationConfigurationBuilder;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.ClientCredentials;
import com.fitbit.authentication.Scope;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import static com.fitbit.authentication.Scope.activity;
public class FitbitAuthApplication extends Application {
String a = "";
    @Override
    public void onCreate() {
        super.onCreate();

        ClientCredentials clientCredentials = new ClientCredentials("228H24", "c5ead87f1c6737f86971377ac71d7d9b","https://www.fitbit.com/user/5TZLG6");
        String secureKey = "94nY2hXXw0gROb7m6kTvbOhZbeOik9dwg7cj8CWRouM";

        AuthenticationConfiguration config = new AuthenticationConfigurationBuilder()
                .setClientCredentials(clientCredentials)
                .setEncryptionKey(secureKey)
                .addRequiredScopes(Scope.profile, Scope.settings)
                .addOptionalScopes(activity, Scope.sleep, Scope.heartrate)
                .build();

        AuthenticationManager.configure(this, config);
   a = "ok";
    }
}
