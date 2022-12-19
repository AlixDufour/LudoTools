package com.alixdufour.ludotools.objects;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alixdufour.ludotools.R;

public class quizPlayerBar {
    public TextView scoreText;
    public int valueScore;
    public EditText playerName;
    public Button increaseButton, reduceButton;

    public quizPlayerBar(){}

    @SuppressLint("ResourceAsColor")
    public quizPlayerBar(View view) {
       scoreText = (TextView) view.findViewById(R.id.score1);
       playerName = (EditText) view.findViewById(R.id.joueur1);
       increaseButton = (Button) view.findViewById(R.id.add1);
       increaseButton.setTextColor(0xFFFFFFFF);
       reduceButton = (Button) view.findViewById(R.id.sous1);
       reduceButton.setTextColor(0xFFFFFFFF);
       valueScore = 0;
    }

    public void setOnClickIncreaseButton(int var) {
        this.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valueScore < 100 - var) {
                    System.out.println("var = " + var);
                    valueScore += var;
                    scoreText.setText(Integer.toString(valueScore));
                }
            }
        });
    }

    public void setOnClickReduceButton(int var) {
        this.reduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valueScore >= 0 + var) {
                    valueScore -= var;
                    scoreText.setText(Integer.toString(valueScore));
                }
            }
        });
    }

    public void setPlayerNameHint(int nbPlayers) {
        String toDisplay = "Joueur " + Integer.toString(nbPlayers);
        this.playerName.setHint(toDisplay);
    }
}
