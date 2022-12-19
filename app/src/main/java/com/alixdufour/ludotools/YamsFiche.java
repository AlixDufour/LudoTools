package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.alixdufour.ludotools.objects.FicheDeScore;

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
        Button button = (Button) findViewById(R.id.buttonCalcul);
        TextView textScore = (TextView) findViewById(R.id.textScore);

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

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        ArrayList<String> manches = new ArrayList<>();
        manches.add("Manche 1");
        manches.add("Manche 2");
        manches.add("Manche 3");
        manches.add("Manche 4");
        manches.add("Manche 5");


        FicheDeScore fiche = new FicheDeScore(manches, nameList, tableLayout, this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textScore.setText(fiche.printScore());
            }
        });


    }



}