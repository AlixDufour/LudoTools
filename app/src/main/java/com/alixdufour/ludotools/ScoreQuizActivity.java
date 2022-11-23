package com.alixdufour.ludotools;

import static com.alixdufour.ludotools.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alixdufour.ludotools.objects.quizPlayerBar;

public class ScoreQuizActivity extends AppCompatActivity {
    final int max_nb_players = 8;
    Button addPlayerButton, removePlayerButton;
    quizPlayerBar[] tab = new quizPlayerBar[max_nb_players];
    View[] savedViews = new View[max_nb_players];
    int nb_players = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_score_quiz);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tab[nb_players - 1] = new quizPlayerBar();
        tab[nb_players - 1].increaseButton = (Button) findViewById(R.id.add1);
        tab[nb_players - 1].reduceButton = (Button) findViewById(id.sous1);
        tab[nb_players - 1].playerName = (EditText) findViewById(R.id.joueur1);
        tab[nb_players - 1].scoreText = (TextView) findViewById(R.id.score1);
        tab[nb_players - 1].valueScore = 0;
        addPlayerButton = (Button) findViewById(id.addPlayer);
        removePlayerButton = (Button) findViewById(id.removePlayer);

        tab[nb_players - 1].setOnClickIncreaseButton();
        tab[nb_players - 1].setOnClickReduceButton();

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        addPlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
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
}