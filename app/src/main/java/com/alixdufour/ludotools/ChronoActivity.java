package com.alixdufour.ludotools;


import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class ChronoActivity extends AppCompatActivity {
    Chronometer simpleChronometer;
    Button start, stop, restart, setFormat, clearFormat;
    boolean running;
    long pauseOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        // initiate views
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        start = (Button) findViewById(R.id.startButton);
        stop = (Button) findViewById(R.id.stopButton);
        restart = (Button) findViewById(R.id.restartButton);

        // perform click  event on start button to start a chronometer
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!running) {
                    simpleChronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    simpleChronometer.start();
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
                    simpleChronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
                    running = false;
                }
            }
        });

        // perform click  event on restart button to set the base time on chronometer
        restart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
            }
        });
    }
}