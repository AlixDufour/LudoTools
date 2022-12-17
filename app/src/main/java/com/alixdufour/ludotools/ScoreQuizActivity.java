package com.alixdufour.ludotools;

import static com.alixdufour.ludotools.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alixdufour.ludotools.objects.quizPlayerBar;

public class ScoreQuizActivity extends AppCompatActivity {
    final int max_nb_players = 8;
    Button addPlayerButton, removePlayerButton;
    quizPlayerBar[] tab = new quizPlayerBar[max_nb_players];
    View[] savedViews = new View[max_nb_players];
    int nb_players = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_score_quiz);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Score Quizz");
        toolbar_text.setPadding(0,0,80,0);
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);
        ImageButton toolbar_info = (ImageButton) findViewById(R.id.info_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScoreQuizActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        ImageButton param = (ImageButton) findViewById(R.id.param_toolbar);
        param.setVisibility(View.VISIBLE);

        addPlayerButton = (Button) findViewById(id.addPlayer);
        removePlayerButton = (Button) findViewById(id.removePlayer);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        addPlayerToView(); //Joueur1

        addPlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                addPlayerToView();
            }
        });

        removePlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (nb_players > 1) {
                    View view = savedViews[nb_players - 1];
                    LinearLayout container = (LinearLayout) findViewById(id.linearLayout);
                    container.removeView(view);
                    nb_players -= 1;
                }
            }
        });
    }

    private void addPlayerToView(){
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        if(nb_players < max_nb_players) {
            nb_players += 1;
            View view = inflater.inflate(R.layout.player_quizz, null);
            LinearLayout container = (LinearLayout) findViewById(id.linearLayout);
            container.addView(view);
            tab[nb_players - 1] = new quizPlayerBar(view);
            tab[nb_players - 1].setOnClickIncreaseButton();
            tab[nb_players - 1].setOnClickReduceButton();
            tab[nb_players - 1].setPlayerNameHint(nb_players);

            savedViews[nb_players - 1] = view;
        }

    }
}