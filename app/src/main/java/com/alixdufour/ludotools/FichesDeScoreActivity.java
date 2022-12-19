package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alixdufour.ludotools.objects.quizPlayerBar;

import java.util.ArrayList;
import java.util.List;

public class FichesDeScoreActivity extends AppCompatActivity {

    final int max_nb_players = 5;
    EditText[] tab = new EditText[max_nb_players];
    View[] savedViews = new View[max_nb_players];
    int nb_players = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiches_de_score);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Fiche de score");
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FichesDeScoreActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        ImageButton toolbar_info = (ImageButton) findViewById(R.id.info_toolbar);

        Spinner dropdown = findViewById(R.id.spinner);

        String[] nomsFiches = new String[]{"5 manches", "10 manches", "Yams", "Belote"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomsFiches);
        dropdown.setAdapter(adapter);

        Button addPlayerButton = (Button) findViewById(R.id.bAdd);
        Button removePlayerButton = (Button) findViewById(R.id.bSupp);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        addPlayerToView(); //Joueur1

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayerToView();
            }
        });

        removePlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (nb_players > 1) {
                    View view = savedViews[nb_players - 1];
                    LinearLayout container = (LinearLayout) findViewById(R.id.linearListPlayer);
                    container.removeView(view);
                    nb_players --;
                }
            }
        });

        Button bChoisir = findViewById(R.id.bChoisir);

        bChoisir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FichesDeScoreActivity.this, YamsFiche.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("namesList", getNames());
                bundle.putInt("nbJoueurs", nb_players);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }

    private ArrayList<String> getNames(){
        ArrayList<String> listeN = new ArrayList<>();
        for (int i=0; i<nb_players; i++){
            String s = tab[i].getText().toString();
            if (s.isEmpty())
                listeN.add("J"+ Integer.toString(i+1));
            else
                listeN.add(s);
        }
        return listeN;
    }

    private void addPlayerToView(){
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        if(nb_players < max_nb_players) {
            nb_players ++;
            View view = inflater.inflate(R.layout.player_fiche, null);
            LinearLayout container = (LinearLayout) findViewById(R.id.linearListPlayer);
            container.addView(view);
            EditText edit = (EditText) view.findViewById(R.id.joueurEditText);
            tab[nb_players - 1] = edit;
            tab[nb_players - 1].setHint("Joueur "+ nb_players);
            savedViews[nb_players - 1] = view;
        }

    }

}