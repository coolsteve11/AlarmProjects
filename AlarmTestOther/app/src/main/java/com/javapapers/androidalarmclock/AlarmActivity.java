package com.javapapers.androidalarmclock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;



public class AlarmActivity extends Activity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    private TextView questiontext;
    private Button button1;
    private EditText tField1;
    private EditText tField2;
    private Switch modeswitch;
    private TextView currentalarm;
    private int mode = 0;

   public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        button1 = (Button) findViewById(R.id.button2);
        tField1 = (EditText)findViewById(R.id.editText);
        tField2  = (EditText)findViewById(R.id.editText2);
        modeswitch  = (Switch)findViewById(R.id.switch1);
        questiontext  = (TextView)findViewById(R.id.textView);
        currentalarm = (TextView)findViewById(R.id.textView5);
        }

    public void onModeSwitch(View view) {
        Boolean switchState = modeswitch.isChecked();
        Calendar rightNow = Calendar.getInstance();
        if (switchState) {
            mode = 1;
            questiontext.setText("How long do you want to sleep for?");

            if (tField1.getText().length() != 0) {

                tField1.setText(Integer.toString(Integer.valueOf(tField1.getText().toString()) - ((rightNow.get(Calendar.HOUR_OF_DAY)))));
            }
            if (tField2.getText().length() != 0) {
                tField2.setText(Integer.toString(Integer.valueOf(tField2.getText().toString()) - ((rightNow.get(Calendar.MINUTE)))));
            }
        } else {
            mode = 0;
            questiontext.setText("When do you want to wake up?");
            if (tField1.getText().length() == 0) {
                tField1.setText("0");
            }
            if (tField2.getText().length() == 0) {
                tField2.setText("0");
            }
                if (tField1.getText().length() != 0) {

                    tField1.setText(Integer.toString(Integer.valueOf(tField1.getText().toString()) + ((rightNow.get(Calendar.HOUR_OF_DAY)))));
                }
                if (tField2.getText().length() != 0) {
                    tField2.setText(Integer.toString(Integer.valueOf(tField2.getText().toString()) + ((rightNow.get(Calendar.MINUTE)))));
                }

            }
        }




    public void onSetTime(View view) {
        if (tField1.getText().length() == 0) {
            tField1.setText("0");
        } else {
        }
        if (tField2.getText().length() == 0) {
            tField2.setText("0");
        }
        if (button1.isPressed()) {
            if (mode == 1) {


                alarmTimePicker.setCurrentHour((alarmTimePicker.getCurrentHour()) + (Integer.valueOf((tField1.getText().toString()))));
                alarmTimePicker.setCurrentMinute((alarmTimePicker.getCurrentMinute() + (Integer.valueOf((tField2).getText().toString()))));
            }
            if (mode == 0){
                alarmTimePicker.setCurrentHour(Integer.valueOf((tField1.getText().toString())));
                alarmTimePicker.setCurrentMinute(Integer.valueOf((tField2).getText().toString()));}


        } else {}




            }



    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("MyActivity", "Alarm On");
            if (alarmTimePicker.getCurrentMinute() < 10){
                currentalarm.setText("Current Alarm: " + alarmTimePicker.getCurrentHour() + ":0" + alarmTimePicker.getCurrentMinute());
            }
            else {
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

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }







}
