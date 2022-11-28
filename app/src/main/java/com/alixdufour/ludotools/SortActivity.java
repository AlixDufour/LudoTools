package com.alixdufour.ludotools;

import androidx.appcompat.app.AlertDialog;
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
    Button buttonGroup;
    List player = new ArrayList();
    Integer nbGroupes = 1;

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
        buttonGroup = (Button) findViewById(R.id.GroupButton);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameEntered = findViewById(R.id.Name);
                String name = nameEntered.getText().toString();
                if (name.isEmpty()) name= "Joueur "+ Integer.toString(player.size()+1);
                int lenght = player.size();

                if(lenght < 10){
                    player.add(name);
                    lenght += 1;
                }
                else {
                    CharSequence text = "10 joueurs maximum";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }

                TextView nameList = findViewById(R.id.ListNames);
                String text = "";
                for (int i = 0; i < lenght; i++) {
                    text += (i+1) + "- " + player.get(i) + "\n";
                }
                nameList.setText(text);
                nameEntered.setText("");
                nameEntered.setHint("Joueur " + (lenght + 1));
            }
        });

        buttonGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView groupEntered = findViewById(R.id.Group);
                String s = groupEntered.getText().toString();
                if (s.isEmpty()) nbGroupes=1;
                else nbGroupes = Integer.parseInt(s);
                groupEntered.setHint(nbGroupes.toString());
                groupEntered.setText("");
                CharSequence text = "Nombre de groupe défini à " + nbGroupes;

                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameList = findViewById(R.id.ListNames);
                if (nameList.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Aucun joueur", Toast.LENGTH_SHORT).show();
                   return;
                }
                String text = "";
                int lenght = player.size();
                List indice = new ArrayList();
                /*for (int i = 0; i < lenght; i++) {
                    Random rng = new Random();
                    int i1 = rng.nextInt(lenght);
                    while (indice.contains(i1)){
                        i1 = rng.nextInt(lenght);
                    }
                    indice.add(i1);
                    text += (i+1) + "- " + player.get(i1) + "\n";
                }*/
                if (nbGroupes <= 1){
                    nbGroupes = lenght;
                }
                int alpha = lenght / nbGroupes;
                int beta = lenght % nbGroupes;
                int gamma;
                for(int i=0; i < nbGroupes; i++){
                    if(i < beta){
                        gamma = alpha + 1;
                    }else {
                        gamma = alpha;
                    }
                    for(int j=0; j < gamma; j++){
                        Random rng = new Random();
                        int i1 = rng.nextInt(lenght);
                        while (indice.contains(i1)){
                            i1 = rng.nextInt(lenght);
                        }
                        indice.add(i1);
                        text += (i+1) + "- " + player.get(i1) + "\n";
                    }

                    if(nbGroupes != lenght){
                        text += "------------------------ \n";
                    }
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
                nameEntered.setText("");
                nameEntered.setHint("Joueur " + (lenght + 1));
            }
        });
    }
}

