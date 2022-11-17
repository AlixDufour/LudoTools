package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizzLike extends AppCompatActivity {

    List<Joueur> joueurs = new ArrayList<Joueur>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_like);

        Button bAdd  = (Button) findViewById(R.id.bAdd);
        GridLayout gridPlayer = (GridLayout) findViewById(R.id.playerGrid);
        EditText editName = (EditText) findViewById(R.id.editName);

        bAdd.setEnabled(false);

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                bAdd.setEnabled(!s.toString().isEmpty());
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Joueur j = new Joueur(editName.getText().toString());
                editName.setText("");
                joueurs.add(j);
                Button bPlayer = new Button(getApplicationContext());
                bPlayer.setText(j.getName() + " " + Integer.toString(j.getScore()));
                bPlayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        j.incScore();
                        bPlayer.setText(j.getName() + " " + Integer.toString(j.getScore()));
                    }
                });
                gridPlayer.addView(bPlayer);
            }
        });




    }
}