package com.alixdufour.ludotools;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    Button buttonRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        buttonRoll  = (Button) findViewById(R.id.StartDice);

        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast= petite pop up de texte
                Toast.makeText(getApplicationContext(), "Dice Rolled!", Toast.LENGTH_SHORT).show();
                //Affichage du résultat du dé
                TextView resultDiceText = findViewById(R.id.result);
                resultDiceText.setText(Integer.toString(rollDice(6)));
            }
        });

    }

    private int rollDice(int size){
        Random rng = new Random();
        return rng.nextInt(size)+1;
    }
}