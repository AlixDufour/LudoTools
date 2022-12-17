package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class YamsFiche extends AppCompatActivity {

    int nbJoueurs;
    ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yams_fiche);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Fiche de score");
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);
        ImageButton toolbar_info = (ImageButton) findViewById(R.id.info_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(YamsFiche.this, FichesDeScoreActivity.class);
                startActivity(i);
            }
        });

        Bundle bundle = getIntent().getExtras();
        nbJoueurs = bundle.getInt("nbJoueurs");
        nameList = bundle.getStringArrayList("namesList");

        TextView textTest = (TextView) findViewById(R.id.textTest);
        String s = "Fiche de score pour le YAMS en travaux. \n Il y a "+nbJoueurs+" joueurs : ";
        for (String name : nameList){
            s= s.concat(" \n" + name);
        }
        textTest.setText(s);

    }



}