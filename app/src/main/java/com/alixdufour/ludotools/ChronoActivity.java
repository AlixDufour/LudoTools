package com.alixdufour.ludotools;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class ChronoActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button start, stop, restart, counting, decounting;
    EditText minuteDepart, secondeDepart;
    TextView titre;
    boolean running, isCountdown = false;
    long pauseOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        // initiate views
        chronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        chronometer.setFormat("%m:%s");
        start = (Button) findViewById(R.id.startButton);
        stop = (Button) findViewById(R.id.stopButton);
        restart = (Button) findViewById(R.id.restartButton);
        counting = (Button) findViewById(R.id.countingButton);
        decounting = (Button) findViewById(R.id.decountingButton);
        minuteDepart = (EditText) findViewById(R.id.startMinute);
        secondeDepart = (EditText) findViewById(R.id.startSecond);
        titre = (TextView) findViewById(R.id.titre);

        // perform click  event on start button to start a chronometer
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!running) {
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        chronometer.setCountDown(isCountdown);
                   }

                    if (isCountdown) {
                        if (pauseOffset != 0)
                            chronometer.setBase(SystemClock.elapsedRealtime() + pauseOffset);

                        else if (!minuteDepart.getText().toString().isEmpty() && !secondeDepart.getText().toString().isEmpty())
                            chronometer.setBase(SystemClock.elapsedRealtime() + (Long.parseLong(minuteDepart.getText().toString()) * 1000 * 60 + Long.parseLong(secondeDepart.getText().toString()) * 1000));

                        else if (!minuteDepart.getText().toString().isEmpty())
                            chronometer.setBase(SystemClock.elapsedRealtime() + (Long.parseLong(minuteDepart.getText().toString()) * 1000 * 60));

                        else if (!secondeDepart.getText().toString().isEmpty())
                            chronometer.setBase(SystemClock.elapsedRealtime() + (Long.parseLong(secondeDepart.getText().toString()) * 1000));

                        else {
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            return;
                        }
                    }

                    else {
                        if (pauseOffset != 0)
                            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);

                        else if (!minuteDepart.getText().toString().isEmpty() && !secondeDepart.getText().toString().isEmpty())
                            chronometer.setBase(SystemClock.elapsedRealtime() - (Long.parseLong(minuteDepart.getText().toString()) * 1000 * 60 + Long.parseLong(secondeDepart.getText().toString()) * 1000));

                        else if (!minuteDepart.getText().toString().isEmpty())
                            chronometer.setBase(SystemClock.elapsedRealtime() - (Long.parseLong(minuteDepart.getText().toString()) * 1000 * 60));

                        else if (!secondeDepart.getText().toString().isEmpty())
                            chronometer.setBase(SystemClock.elapsedRealtime() - (Long.parseLong(secondeDepart.getText().toString()) * 1000));

                        else
                            chronometer.setBase(SystemClock.elapsedRealtime());
                    }
                    chronometer.start();
                    running = true;
                }
            }
        });

        // perform click  event on stop button to stop the chronometer
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (running) {
                    chronometer.stop();
                    if (isCountdown)
                        pauseOffset = chronometer.getBase() - SystemClock.elapsedRealtime();
                    else
                        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;
                }
            }
        });

        // perform click  event on restart button to set the base time on chronometer
        restart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                running = false;
            }
        });

        // perform click  event on counting button to put chronometer mode
        counting.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!running) {
                    isCountdown = false;
                    titre.setText("CHRONO");
                }

            }
        });

        // perform click  event on counting button to put timer mode
        decounting.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!running) {
                    isCountdown = true;
                    titre.setText("TIMER");
                }
            }
        });
    }
}