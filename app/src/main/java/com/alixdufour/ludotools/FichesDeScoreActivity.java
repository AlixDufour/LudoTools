package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class FichesDeScoreActivity extends AppCompatActivity {

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

        Spinner dropdown = findViewById(R.id.spinner);

        String[] nomsFiches = new String[]{"Yams", "Belote", "Personnalisée"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomsFiches);
        dropdown.setAdapter(adapter);
    }
}