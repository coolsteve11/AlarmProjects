package com.javapapers.androidalarmclock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;



public class AlarmActivity extends Activity implements GestureDetector.OnGestureListener {
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
    private GestureDetector gDetector;
    private float a = 0;


   public static AlarmActivity instance() {
        return inst;
    }
    Calendar rightNow = Calendar.getInstance();
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
        gDetector = new GestureDetector(this);



    }



    public void onModeSwitch(View view) {
        Boolean switchState = modeswitch.isChecked();

        if (switchState) {
            mode = 1;
            questiontext.setText("How long do you want to sleep for?");

            if (tField1.getText().length() != 0) {

                tField1.setText(Integer.toString(Integer.valueOf(tField1.getText().toString()) - ((rightNow.get(Calendar.HOUR_OF_DAY)))));

            }
            if (tField2.getText().length() != 0) {
                tField2.setText(Integer.toString(Integer.valueOf(tField2.getText().toString()) - ((rightNow.get(Calendar.MINUTE)))));
            }
    if ((tField1.getText().length() != 0) && (tField2.getText().length() != 0)) {
        if ((Integer.valueOf(tField1.getText().toString())) < 0) {
            tField1.setText(Integer.toString(24 - (Integer.valueOf(tField1.getText().toString()))));
        }
        if ((Integer.valueOf(tField2.getText().toString())) < 0) {
            tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) - 1));
            tField2.setText(Integer.toString(60 - ((Integer.valueOf(tField2.getText().toString())))));
        }
    }
            }
         else {
            if (tField1.getText().length() == 0 || tField2.getText().length() == 0 ){
                tField1.setText("0");
                tField2.setText("0");
            }
            mode = 0;
            questiontext.setText("When do you want to wake up?");

            if ((tField2.getText().length() != 0) && (tField1.getText().length() != 0)) {
                if ((Integer.valueOf(tField2.getText().toString())) >= 60) {
                    tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) + (Integer.valueOf(tField2.getText().toString())) / 60));
                    tField2.setText(Integer.toString((Integer.valueOf(tField2.getText().toString())) - 60 * ((Integer.valueOf(tField2.getText().toString())) / 60)));
                }

                tField2.setText(Integer.toString(Integer.valueOf(tField2.getText().toString()) + ((rightNow.get(Calendar.MINUTE)))));
            }

            if (tField1.getText().length() != 0) {

                tField1.setText(Integer.toString(Integer.valueOf(tField1.getText().toString()) + ((rightNow.get(Calendar.HOUR_OF_DAY)))));
            }
            if ((((Integer.valueOf(tField1.getText().toString())) > 24) && (tField1.getText().length() != 0))){
                tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) - 24));
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


                alarmTimePicker.setCurrentHour((rightNow.get(Calendar.HOUR_OF_DAY) + (Integer.valueOf((tField1.getText().toString())))));
                alarmTimePicker.setCurrentMinute((rightNow.get(Calendar.MINUTE)+ (Integer.valueOf((tField2).getText().toString()))));
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





    @Override
    public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
        if (tField1.getText().length() == 0) {
            tField1.setText("0");
        }
        if (tField2.getText().length() == 0) {
            tField2.setText("0");
        }
        if (finish.getRawY() > start.getRawY() ){
            a = Math.abs(finish.getRawY() - start.getRawY());}
        else{
            a = Math.abs(start.getRawY() - finish.getRawY());
        }


        if (a > 80){
            if ((start.getRawY() > finish.getRawY())){tField1.requestFocus();
                a = 0;
                return true;}
            if ((start.getRawY() < finish.getRawY())){tField2.requestFocus();
                a = 0;
                return true;}
        }
        else {
                if ((start.getRawX() < finish.getRawX())) {
                    if (tField1.hasFocus()) {
                        tField1.setText(Integer.toString((Integer.valueOf(tField1.getText().toString())) + 1));
                    }
                    if (tField2.hasFocus()) {
                        tField2.setText(Integer.toString((Integer.valueOf(tField2.getText().toString())) + 1));
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
                    }
                    if (tField2.hasFocus()) {
                        tField2.setText(Integer.toString((Integer.valueOf(tField2.getText().toString())) - 1));
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

}







