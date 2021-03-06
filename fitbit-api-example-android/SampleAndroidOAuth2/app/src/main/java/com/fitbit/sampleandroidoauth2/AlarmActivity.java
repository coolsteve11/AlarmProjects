package com.fitbit.sampleandroidoauth2;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.HRData;
import com.fitbit.api.models.HRLogs;
import com.fitbit.api.services.HRService;
import com.fitbit.authentication.AuthenticationHandler;
import com.fitbit.authentication.AuthenticationManager;
import com.fitbit.authentication.AuthenticationResult;
import com.fitbit.authentication.Scope;
import com.fitbit.sampleandroidoauth2.fragments.HRF;
import com.fitbit.sampleandroidoauth2.fragments.HeartLogsFragment;
import com.fitbit.sampleandroidoauth2.fragments.InfoFragment;


import java.util.Calendar;
import java.util.List;
import java.util.Set;


public class AlarmActivity extends Activity implements GestureDetector.OnGestureListener, AuthenticationHandler, LoaderManager.LoaderCallbacks<ResourceLoaderResult<HRLogs>> {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    private EditText tField1;
    private EditText tField2;
    private TextView currentalarm;
    private CheckBox boxcheck;
    private GestureDetector gDetector;
    private float a = 0;
    public static AlarmActivity instance() {
        return inst;
    }

    static List<HRData> rates;
    int restingrate= 0;
    int sleeprate= 0;
    int lowpoint= 0;
    int highpoint = 0;
    String sleeptime = "";
int id = 42;
    Calendar rightNow = Calendar.getInstance();

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AlarmActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLoaderManager().initLoader(id, null, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        alarmTimePicker = (TimePicker)  findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        tField1 =  (EditText) findViewById(R.id.editText);
        tField2 = (EditText)  findViewById(R.id.editText2);
        currentalarm = (TextView) findViewById(R.id.textView5);
        gDetector = new GestureDetector(this);
        alarmTimePicker.setIs24HourView(true);
        boxcheck = (CheckBox)  findViewById(R.id.checkBox);




    }


    @Override
    public Loader<ResourceLoaderResult<HRLogs>> onCreateLoader(int id, Bundle args) {

        AuthenticationManager.login(this);
        Log.i("SUCCESSSISH","at least the loader is created");
        return HRService.getHRLogLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<HRLogs>> loader, ResourceLoaderResult<HRLogs> data) {
        onLoadFinished(loader, data);
        if (data.isSuccessful()) {
            Log.i("SUCCESSSMORE","the loader finished !!!!");
            bindHeartLogs(data.getResult());

        }
        else{
            Log.i("FAILURE","FAILURE  oh");}
    }

    @Override
    public void onLoaderReset(Loader<ResourceLoaderResult<HRLogs>> loader) {
        // data is not available anymore, delete reference

    }


    public void onSetTime(View view) {
        if (tField1.getText().length() == 0) {
            tField1.setText("0");
        } else {
        }
        if (tField2.getText().length() == 0) {
            tField2.setText("0");
        }
        alarmTimePicker.setCurrentHour((rightNow.get(Calendar.HOUR_OF_DAY) + (Integer.valueOf((tField1.getText().toString())))));
        alarmTimePicker.setCurrentMinute((rightNow.get(Calendar.MINUTE) + (Integer.valueOf((tField2).getText().toString()))));


    }

    public void onSetSleep(View view) {
        if (tField1.getText().length() == 0) {
            tField1.setText("0");
        }
        if (tField2.getText().length() == 0) {
            tField2.setText("0");
        }
        if (alarmTimePicker.getCurrentHour() >= rightNow.get(Calendar.HOUR_OF_DAY)) {
            tField1.setText(Integer.toString(alarmTimePicker.getCurrentHour() - rightNow.get(Calendar.HOUR_OF_DAY)));
        }
        else{
            tField1.setText(Integer.toString(24 - (rightNow.get(Calendar.HOUR_OF_DAY)-(alarmTimePicker.getCurrentHour()))));
        }
        if(alarmTimePicker.getCurrentMinute() >= rightNow.get(Calendar.MINUTE)){
            tField2.setText(Integer.toString(alarmTimePicker.getCurrentMinute()- rightNow.get(Calendar.MINUTE)));
        }
        else{
            tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) - 1));
            tField2.setText(Integer.toString(60 - (Integer.valueOf(tField2.getText().toString()))));
        }

        if ((Integer.valueOf((tField1.getText().toString()))) < 0){
            tField1.setText(Integer.toString(24 - Math.abs((Integer.valueOf(tField1.getText().toString())))));
        }
        if ((Integer.valueOf((tField2.getText().toString()))) < 0){
            tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) - 1));
            tField2.setText(Integer.toString(60 - (Integer.valueOf(tField2.getText().toString()))));
        }

        if (((Integer.valueOf((tField2.getText().toString())) == 60))){
            tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) + 1));
            tField2.setText("0");
        }

    }





    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("MyActivity", "Alarm On");
            if (alarmTimePicker.getCurrentMinute() < 10) {
                currentalarm.setText("Current Alarm: " + alarmTimePicker.getCurrentHour() + ":0" + alarmTimePicker.getCurrentMinute());
            } else {
                currentalarm.setText("Current Alarm: " + alarmTimePicker.getCurrentHour() + ":" + alarmTimePicker.getCurrentMinute());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            currentalarm.setText("Current Alarm: None");
            setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
        }
    }


    public void onFitClick(View view) {
        if (((CheckBox) view).isChecked()) {
            alarmTimePicker.setVisibility(View.INVISIBLE);
            //bindHeartLogs1();
        }
        else {
            alarmTimePicker.setVisibility(View.VISIBLE);

        }
    }


    public void bindHeartLogs(HRLogs heartLogs) {

        Log.i("SUCCESSS","SUCCESSS");
        rates = heartLogs.getHRData();
        if (rates.size() == 0){  Log.e("NOINFOR", "RIP");}
        else {
            int restingrate = heartLogs.getResting();
            int sleeprate = restingrate - 5;

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
            }
            Log.i("LOWPOINT", Integer.toString(lowpoint));
       Log.i("HIGHPOINT", Integer.toString(highpoint));

        Log.i("RESTINGRATE", Integer.toString(restingrate));
        Log.i("SLEEPTIME", sleeptime);

        }
    }

//    public void bindHeartLogs1(){
//          this didn't work, probably because HRF needs to be loaded, not just created
//        Log.i("SUCCESSS","SUCCESSS");
//        HRF wew = new HRF();
//        sleeptime = wew.getTime();
//        rates = wew.getRates();
//        lowpoint = wew.getlow();
//        highpoint = wew.gethigh();
//        sleeprate = wew.getsleeprate();
//        restingrate = wew.getresting();
//
//        Log.i("LOWPOINT", Integer.toString(lowpoint));
//        Log.i("HIGHPOINT", Integer.toString(highpoint));
//
//        Log.i("RESTINGRATE", Integer.toString(restingrate));
//        Log.i("SLEEPTIME", sleeptime);
//    }


    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }


    @Override
    public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {

        if (tField1.getText().length() == 0) {
            tField1.setText("0");
        }
        if (tField2.getText().length() == 0) {
            tField2.setText("0");
        }
        if (finish.getRawY() > start.getRawY()) {
            a = Math.abs(finish.getRawY() - start.getRawY());
        } else {
            a = Math.abs(start.getRawY() - finish.getRawY());
        }


        if (a > 80) {
            if ((start.getRawY() > finish.getRawY())) {
                tField1.requestFocus();
                a = 0;

                return true;
            }
            if ((start.getRawY() < finish.getRawY())) {
                tField2.requestFocus();
                a = 0;

                return true;
            }
        } else {
            if ((start.getRawX() < finish.getRawX())) {
                if (tField1.hasFocus()) {
                    tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) + 1));
                    checkVal();
                }
                if (tField2.hasFocus()) {
                    tField2.setText(Integer.toString((Integer.valueOf(tField2.getText().toString())) + 1));
                    checkVal();
                } else {
                    {
                        tField1.setText(tField1.getText());
                        tField2.setText(tField2.getText());
                    }

                }
                a = 0;

                return true;
            }
            if (start.getRawX() > finish.getRawX()) {
                if (tField1.hasFocus()) {
                    tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) - 1));
                    checkVal();
                }
                if (tField2.hasFocus()) {
                    tField2.setText(Integer.toString((Integer.valueOf(tField2.getText().toString())) - 1));
                    checkVal();
                } else {
                    {
                        tField1.setText(tField1.getText());
                        tField2.setText(tField2.getText());

                    }

                }
                a = 0;
                return true;
            }
            a = 0;

            return false;
        }
        a = 0;
        return false;

    }

    public void checkVal(){
        if (tField1.getText().length() == 0) {
            tField1.setText("0");
        }
        if (tField2.getText().length() == 0) {
            tField2.setText("0");
        }
        if ((Integer.valueOf(tField1.getText().toString())) > 24){tField1.setText("0");}
        if ((Integer.valueOf(tField1.getText().toString())) < 0){tField1.setText("24");}
        if ((Integer.valueOf(tField2.getText().toString())) > 60){tField2.setText("0");}
        if ((Integer.valueOf(tField2.getText().toString())) < 0){tField2.setText("60");}
    }

    //these overrides are needed to implement GestureDetector
    @Override
    public boolean onDown(MotionEvent arg0) {

        return false;
    }


    @Override
    public void onLongPress(MotionEvent arg0) {

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gDetector.onTouchEvent(me);
    }


    public void onLoggedIn() {
              //  List<HRData> rates =  HeartLogsFragment.getRates();
//        Intent intent = getHr.newIntent(this);
//        startActivity(intent);
//        AuthenticationManager.login(this);
    }


//    public void onLoginClick(View view) {
//        AuthenticationManager.login(this);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AuthenticationManager.onActivityResult(requestCode, resultCode, data, (AuthenticationHandler) this);
    }


    public void onAuthFinished(AuthenticationResult authenticationResult) {
        if (authenticationResult.isSuccessful()) {
            onLoggedIn();
        } else {
            displayAuthError(authenticationResult);
        }

    }

    private void displayAuthError(AuthenticationResult authenticationResult) {
        String message = "";

        switch (authenticationResult.getStatus()) {
            case dismissed:
                message = "Login dismissed or no scopes selected";
                break;
            case error:
                message = authenticationResult.getErrorMessage();
                break;
            case missing_required_scopes:
                Set<Scope> missingScopes = authenticationResult.getMissingScopes();
                String missingScopesText = TextUtils.join(", ", missingScopes);
                message = "Error logging in. Missing the following required scopes:" + missingScopesText;
                break;
        }

        new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .create()
                .show();
    }


}
