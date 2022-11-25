package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortActivity extends AppCompatActivity {

    Button buttonAdd;
    Button buttonSort;
    Button buttonRemove;
    List player = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Joueurs");
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SortActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        buttonAdd = (Button) findViewById(R.id.AddButton);
        buttonSort = (Button) findViewById(R.id.SortButton);
        buttonRemove = (Button) findViewById(R.id.RemoveButton);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameEntered = findViewById(R.id.Name);
                String name = nameEntered.getText().toString();
                player.add(name);
                TextView nameList = findViewById(R.id.ListNames);
                String text = "";
                int lenght = player.size();
                for (int i = 0; i < lenght; i++) {
                    text += (i+1) + "- " + player.get(i) + "\n";
                }
                nameList.setText(text);
                nameEntered.setText("Joueur " + (lenght + 1));
            }
        });

        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameList = findViewById(R.id.ListNames);
                String text = "";
                int lenght = player.size();
                List indice = new ArrayList();
                for (int i = 0; i < lenght; i++) {
                    Random rng = new Random();
                    int i1 = rng.nextInt(lenght);
                    while (indice.contains(i1)){
                        i1 = rng.nextInt(lenght);
                    }
                    indice.add(i1);
                    text += (i+1) + "- " + player.get(i1) + "\n";
                }
                nameList.setText(text);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameEntered = findViewById(R.id.Name);
                TextView nameList = findViewById(R.id.ListNames);
                String text = "";
                int lenght = player.size();
                player.remove(lenght - 1);
                lenght = player.size();
                for (int i = 0; i < lenght; i++) {
                    text += (i+1) + "- " + player.get(i) + "\n";
                }
                nameList.setText(text);
                nameEntered.setText("Joueur " + (lenght + 1));
            }
        });
    }
}

