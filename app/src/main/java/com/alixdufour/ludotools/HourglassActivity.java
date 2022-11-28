package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class HourglassActivity extends AppCompatActivity {
    Chronometer hautSablier, basSablier;
    Button start, restart, flip;
    EditText minuteDepart, secondeDepart;
    TextView titre;
    String minute_seconde;
    boolean running, flipped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourglass);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Gestion du temps");
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HourglassActivity.this, ChronoActivity.class);
                startActivity(i);
            }
        });

        // initiate views
        hautSablier = (Chronometer) findViewById(R.id.hautSablier);
        hautSablier.setFormat("%m:%s");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            hautSablier.setCountDown(true);
        }
        basSablier = (Chronometer) findViewById(R.id.basSablier);
        basSablier.setFormat("%m:%s");
        start = (Button) findViewById(R.id.startButton);
        restart = (Button) findViewById(R.id.restartButton);
        flip = (Button) findViewById(R.id.flipButton);
        minuteDepart = (EditText) findViewById(R.id.startMinute);
        minuteDepart.setText("01", TextView.BufferType.EDITABLE);
        secondeDepart = (EditText) findViewById(R.id.startSecond);
        secondeDepart.setText("00", TextView.BufferType.EDITABLE);
        titre = (TextView) findViewById(R.id.titre);

        hautSablier.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase("00:00")) {
                    chronometer.stop();
                }
            }
        });

        basSablier.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase(minute_seconde)) {
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
                    if (!minuteDepart.getText().toString().isEmpty() && !secondeDepart.getText().toString().isEmpty())
                        hautSablier.setBase(SystemClock.elapsedRealtime() + (Long.parseLong(minuteDepart.getText().toString()) * 1000 * 60 + Long.parseLong(secondeDepart.getText().toString()) * 1000));

                    else if (!minuteDepart.getText().toString().isEmpty())
                        hautSablier.setBase(SystemClock.elapsedRealtime() + (Long.parseLong(minuteDepart.getText().toString()) * 1000 * 60));

                    else if (!secondeDepart.getText().toString().isEmpty())
                        hautSablier.setBase(SystemClock.elapsedRealtime() + (Long.parseLong(secondeDepart.getText().toString()) * 1000));

                    else {
                        //hautSablier.setBase(SystemClock.elapsedRealtime());
                        return;
                    }

                    basSablier.setBase(SystemClock.elapsedRealtime() - 1000);

                    minute_seconde = minute_seconde_str(minuteDepart, secondeDepart);

                    hautSablier.start();
                    basSablier.start();
                    running = true;
                }
            }
        });

        // perform click  event on restart button to set the base time on chronometer
        restart.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                minute_seconde = minute_seconde_str(minuteDepart, secondeDepart);

                hautSablier.stop();
                basSablier.stop();

                hautSablier.setText(minute_seconde);
                basSablier.setText("00:00");

                running = false;
            }
        });

        // perform click  event on counting button to put chronometer mode
        flip.setOnClickListener(new View.OnClickListener() {
            long hautSablierTime, basSablierTime;

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (running) {
                    flipped = !flipped;
                    if (flipped) {
                        flip.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#de894b")));
                        hautSablier.setTextColor(ColorStateList.valueOf(Color.parseColor("#de894b")));
                        basSablier.setTextColor(ColorStateList.valueOf(Color.parseColor("#5a90d1")));
                    }
                    else {
                        flip.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5a90d1")));
                        hautSablier.setTextColor(ColorStateList.valueOf(Color.parseColor("#5a90d1")));
                        basSablier.setTextColor(ColorStateList.valueOf(Color.parseColor("#de894b")));
                    }

                    hautSablierTime = getChronometerAsLong(hautSablier);
                    basSablierTime = getChronometerAsLong(basSablier);

                    hautSablier.stop();
                    basSablier.stop();
                    hautSablier.setBase(SystemClock.elapsedRealtime() + 1000 + basSablierTime);// - basSablierTime);
                    basSablier.setBase(SystemClock.elapsedRealtime() - hautSablierTime);
                    basSablier.start();
                    hautSablier.start();
                }
            }
        });
    }

    public Long getChronometerAsLong(Chronometer chronometer) {

        String chronoText = chronometer.getText().toString();
        String[] array = chronoText.split(":");
        return Long.parseLong(array[0]) * 1000 * 60 + Long.parseLong(array[1]) * 1000;
    }

    public String minute_seconde_str(EditText minuteDepart, EditText secondeDepart){
        String minute, seconde, minute_seconde;
        if (!minuteDepart.getText().toString().isEmpty())
            minute = String.format("%02d", Integer.parseInt(minuteDepart.getText().toString()));

        else
            minute = "00";

        if (!secondeDepart.getText().toString().isEmpty())
            seconde = String.format("%02d", Integer.parseInt(secondeDepart.getText().toString()));

        else
            seconde = "00";
        return minute + ":" + seconde;
    }
}