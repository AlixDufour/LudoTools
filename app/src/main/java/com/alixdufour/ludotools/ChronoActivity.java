package com.alixdufour.ludotools;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChronoActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button start, stop, restart, counting, decounting, hourglass;
    EditText minuteDepart, secondeDepart;
    TextView titre;
    boolean running, isCountdown = false;
    long pauseOffset = 0;
    long value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Gestion du temps");
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChronoActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // initiate views
        chronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        chronometer.setFormat("%m:%s");
        start = (Button) findViewById(R.id.startButton);
        stop = (Button) findViewById(R.id.stopButton);
        restart = (Button) findViewById(R.id.restartButton);
        counting = (Button) findViewById(R.id.countingButton);
        decounting = (Button) findViewById(R.id.decountingButton);
        hourglass = (Button) findViewById(R.id.hourglassButton);
        minuteDepart = (EditText) findViewById(R.id.startMinute);
        secondeDepart = (EditText) findViewById(R.id.startSecond);
        titre = (TextView) findViewById(R.id.titre);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase("00:00")) {
                    chronometer.stop();
                }
            }
        });

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
                    value = chronometer.getBase();
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

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chronometer.stop();
                String minute, seconde, minute_seconde;
                if (!minuteDepart.getText().toString().isEmpty())
                    minute = String.format("%02d", Integer.parseInt(minuteDepart.getText().toString()));

                else
                    minute = "00";

                if (!secondeDepart.getText().toString().isEmpty())
                    seconde = String.format("%02d", Integer.parseInt(secondeDepart.getText().toString()));

                else
                    seconde = "00";
                minute_seconde = minute + ":" + seconde;
                chronometer.setText(minute_seconde);

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

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!running) {
                    isCountdown = true;
                    titre.setText("TIMER");
                }
            }
        });

        hourglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChronoActivity.this, HourglassActivity.class);
                startActivity(i);
            }
        });
    }
}