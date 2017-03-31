package com.alliedinfosoft.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Allied Infosoft on 3/31/2017.
 */

public class RestartTime extends AppCompatActivity{
    EditText editTextHour,editTextMinute,editTextSecond;
    Button startTimer;
    TextView textViewDisplayTimer;
    int hourint,minuteint,secondint;
    long totalTimeCountInMilliseconds,timeBlinkInMilliseconds;
    private boolean blink;
    private CountDownTimer countDownTimer;
    public void UIReference()
    {
        editTextHour = (EditText) findViewById(R.id.edittxthour);
        editTextMinute = (EditText) findViewById(R.id.edittxtminute);
        editTextSecond = (EditText) findViewById(R.id.edittxtsecond);
        textViewDisplayTimer= (TextView) findViewById(R.id.textViewDisplay);
        startTimer = (Button) findViewById(R.id.btnStartTimer);

    }

    public void UIClickListener()
    {
        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hourint  = Integer.valueOf(editTextHour.getText().toString());

                minuteint = Integer.valueOf(editTextMinute.getText().toString());

                secondint = Integer.valueOf(editTextSecond.getText().toString());

                Log.i("YourActivity", "Hours: " + hourint);

                Log.i("YourActivity", "Minutes: " + minuteint);

                Log.i("YourActivity", "Seconds: " + secondint);
                totalTimeCountInMilliseconds = ((hourint*60*60) +(minuteint*60) + (secondint)) * 1000;      // time count for 3 minutes = 180 seconds
                timeBlinkInMilliseconds = totalTimeCountInMilliseconds/1000;
                countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
                    // 500 means, onTick function will be called at every 500 milliseconds

                    @Override
                    public void onTick(long leftTimeInMilliseconds) {
                        long seconds = leftTimeInMilliseconds / 1000;
                        //  mSeekArc.setVisibility(View.INVISIBLE);


                        if ( leftTimeInMilliseconds < timeBlinkInMilliseconds ) {
                            // textViewShowTime.setTextAppearance(getApplicationContext(), R.style.blinkText);
                            // change the style of the textview .. giving a red alert style

                            if ( blink ) {
                                editTextSecond.setVisibility(View.VISIBLE);
                                editTextMinute.setVisibility(View.VISIBLE);
                                editTextHour.setVisibility(View.VISIBLE);


                                // if blink is true, textview will be visible
                            } else {
                                editTextHour.setVisibility(View.INVISIBLE);
                                editTextMinute.setVisibility(View.INVISIBLE);
                                editTextSecond.setVisibility(View.INVISIBLE);


                            }

                            blink = !blink;         // toggle the value of blink
                        }

                        textViewDisplayTimer.setText(String.format("%02d:%02d:%02d", seconds / 3600,
                                (seconds % 3600) / 60, (seconds % 60)));

            // format the textview to show the easily readable format
                    }


                    @Override
                    public void onFinish() {
                        // this function will be called when the timecount is finished
                        //textViewShowTime.setText("Time up!");
                        editTextSecond.setVisibility(View.VISIBLE);
                        editTextMinute.setVisibility(View.VISIBLE);
                        editTextHour.setVisibility(View.VISIBLE);
                        //  mSeekArc.setVisibility(View.VISIBLE);
                    }

                }.start();

            }
        });
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer);

        UIReference();
        UIClickListener();




    }
}