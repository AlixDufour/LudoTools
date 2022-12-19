package com.alixdufour.ludotools;

import static com.alixdufour.ludotools.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alixdufour.ludotools.objects.quizPlayerBar;

public class ScoreQuizActivity extends AppCompatActivity {
    final int max_nb_players = 8;
    Button addPlayerButton, removePlayerButton;
    quizPlayerBar[] tab = new quizPlayerBar[max_nb_players];
    View[] savedViews = new View[max_nb_players];
    int nb_players = 0;
    int var = 1;
    Button add1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_score_quiz);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Score Quizz");
        toolbar_text.setPadding(0,0,80,0);
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);
        ImageButton toolbar_info = (ImageButton) findViewById(R.id.info_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScoreQuizActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        toolbar_info.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(ScoreQuizActivity.this).create();
                alertDialog.setTitle("Comment fonctionne Score Quizz ?");
                alertDialog.setMessage("Cette section permet de gérer les scores des joueurs simplement.\n\nVous pouvez ajouter un joueur en appuyant sur AJOUTER et supprimer le dernier en appuyant sur SUPPRIMER. \n\nVous pouvez changer le nom d'un joueur et lui ajouter ou lui enlever des points en cliquant sur les boutons vert et rouge. Le score total est affiché à gauche du pseudo. \n\nA l'aide de l'icone à côté du ? vous pouvez régler le nombre de points ajoutés et soustraits à chaque fois.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Compris !",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }));

        ImageButton param = (ImageButton) findViewById(R.id.param_toolbar);
        param.setVisibility(View.VISIBLE);

        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScoreQuizActivity.this);
                final EditText input = new EditText(ScoreQuizActivity.this);
                String varText = String.valueOf(var);
                input.setText(varText);
                builder.setView(input);
                builder.setTitle("Choisissez de combien augmenter et diminuer le score :");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        //Traitement texte
                        var = Integer.parseInt(text);
                        setButtonText(var);
                        System.out.println(var);
                        dialog.dismiss();
                    }
                });



                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });

        addPlayerButton = (Button) findViewById(id.addPlayer);
        removePlayerButton = (Button) findViewById(id.removePlayer);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        addPlayerToView(); //Joueur1

        addPlayerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                addPlayerToView();
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

    private void addPlayerToView(){
        System.out.println(("ici var = " + var));
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        if(nb_players < max_nb_players) {
            nb_players += 1;
            View view = inflater.inflate(R.layout.player_quizz, null);
            LinearLayout container = (LinearLayout) findViewById(id.linearLayout);
            container.addView(view);
            int player = nb_players - 1;
            tab[nb_players - 1] = new quizPlayerBar(view);
            tab[nb_players - 1].increaseButton.setText("+" + var);
            tab[nb_players - 1].reduceButton.setText("-" + var);
            tab[nb_players - 1].increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tab[player].valueScore < 100 - var) {
                        System.out.println("var = " + var);
                        tab[player].valueScore += var;
                        tab[player].scoreText.setText(Integer.toString(tab[player].valueScore));
                    }
                }
            });
            tab[nb_players - 1].reduceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tab[player].valueScore >= 0 + var) {
                        tab[player].valueScore -= var;
                        tab[player].scoreText.setText(Integer.toString(tab[player].valueScore));
                    }
                }
            });;
            tab[nb_players - 1].setPlayerNameHint(nb_players);

            savedViews[nb_players - 1] = view;
        }

    }

    public void setButtonText(int var){
        for(int i=0; i < nb_players; i++){
            tab[i].increaseButton.setText("+" + var);
            tab[i].reduceButton.setText("-" + var);
        }
    }
}