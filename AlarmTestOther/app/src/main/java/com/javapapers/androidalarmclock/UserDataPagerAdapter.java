package com.javapapers.androidalarmclock;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.Scope;
import com.javapapers.androidalarmclock.fragments.ActivitiesFragment;
import com.javapapers.androidalarmclock.fragments.DeviceFragment;
import com.javapapers.androidalarmclock.fragments.HeartLogsFragment;
import com.javapapers.androidalarmclock.fragments.InfoFragment;
import com.javapapers.androidalarmclock.fragments.ProfileFragment;
import com.javapapers.androidalarmclock.fragments.WeightLogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jboggess on 10/17/16.
 */

public class UserDataPagerAdapter extends FragmentPagerAdapter {

    private final List<InfoFragment> fragments = new ArrayList<>();

    public UserDataPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        if (containsScope(Scope.profile)) {
            fragments.add(new ProfileFragment());
        }
        if (containsScope(Scope.settings)) {
            fragments.add(new DeviceFragment());
        }
        if (containsScope(Scope.activity)) {
            fragments.add(new ActivitiesFragment());
        }
       if (containsScope(Scope.weight)) {
            fragments.add(new WeightLogFragment());
    }
        if (containsScope(Scope.heartrate)) {
           fragments.add(new HeartLogsFragment());
        }
    }

    private boolean containsScope(Scope scope) {
        return AuthenticationManager.getCurrentAccessToken().getScopes().contains(scope);
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= fragments.size()) {
            return null;
        }

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getTitleResourceId(int index) {
        return fragments.get(index).getTitleResourceId();
    }
}
