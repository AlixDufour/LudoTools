package com.alixdufour.ludotools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.alixdufour.ludotools.objects.Dice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    public int diceSize=6;
    public List<Integer> listCol; //liste des couleurs des boutons
    public List<Button> listBut; //liste des boutons
    public List<Dice> listDice; //liste des boutons représentant les dés
    public int nbDice=0;
    protected int maxDice=6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_text = (TextView) findViewById(R.id.text_toolbar);
        toolbar_text.setText("Dés");
        ImageButton toolbar_back = (ImageButton) findViewById(R.id.back_toolbar);

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiceActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        ImageButton toolbar_info = (ImageButton) findViewById(R.id.info_toolbar);

        toolbar_info.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(DiceActivity.this).create();
                alertDialog.setTitle("Comment lancer des dés ?");
                alertDialog.setMessage("Cette section permet de lancer de 1 à 6 dés.\n\nVous pouvez en ajouter un en appuyant sur la valeur du dé que vous souhaitez ou en rentrant une valeur à la place de \"Personnalisé\" et en appuyant sur +. \n\nEn appuyant sur lancer, tous les dès seront relancés. Vous pouvez lire la valeur d'un dé sur l'icone qui le représente ou l'addition de la valeur des dés au centre de l'écran. \n\nVous pouvez également ne relancer qu'un dé en appuyant légèrement sur celui-ci. En appuyant longuement, vous avez la possibilité de le supprimer. \n\nLe bouton \"Reset\" vous permettra de supprimer tous les dés.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Compris !",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }));

        create_listButt();
        Button buttonRoll  = (Button) findViewById(R.id.StartDice);
        TextView resultDiceText = findViewById(R.id.result);

        Button bReset = (Button) findViewById(R.id.bReset);

        Button bAdd = (Button) findViewById(R.id.bAdd);
        EditText editValue = (EditText) findViewById(R.id.editValue);

        bAdd.setEnabled(false);

        editValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                bAdd.setEnabled(!s.toString().isEmpty() && Integer.parseInt((String) s.toString())!=0);
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //buttonRoll.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.yellow_pastel), PorterDuff.Mode.MULTIPLY);
                int value = Integer.parseInt((String) editValue.getText().toString());
                addDice(getResources().getColor(R.color.yellow_pastel),value);
            }
        });


        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast= petite pop up de texte à décomenter si voulue
                //Toast.makeText(getApplicationContext(), "Dice Rolled!", Toast.LENGTH_SHORT).show();

                //Affichage du résultat du dé
                if (nbDice == 0 ) resultDiceText.setText("0");
                else  rollAll();
            }
        });

        for ( Button b : listBut) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //buttonRoll.getBackground().setColorFilter(listCol.get(2), PorterDuff.Mode.MULTIPLY);

                    addDice(listCol.get(listBut.indexOf(b)), Integer.parseInt((String) b.getText()));
                }
            });
        }

        for ( Dice d : listDice ) {
            d.getButton().setVisibility(View.INVISIBLE);
            d.getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    d.rollDice();
                    refreshResult();
                }
            });

            d.getButton().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    d.reset();
                    refreshVisibleDice();
                    nbDice=getNbDice();
                    refreshResult();
                    return true;
                }
            });
        }

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Dice d : listDice) {
                    d.reset();
                    nbDice=0;
                }
                resultDiceText.setText("0");
            }
        });
    }

    private void addDice(String s) {
        if (nbDice < maxDice) {
            Dice d = listDice.get(nbDice);
            d.getButton().setVisibility(View.VISIBLE);
            d.getButton().setText(s);

        }
        nbDice++;
    }

    //Précision de la couleur
    private void addDice(Integer color, int maxValue) {
        if (nbDice < maxDice) {
            Dice d = listDice.get(nbDice);
            d.setMaxValue(maxValue);
            d.getButton().setVisibility(View.VISIBLE);
            d.getButton().getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            d.setColor(color);
            d.rollDice();
            refreshResult();
        }
        nbDice++;
    }

    private int rollDice(int size){
        Random rng = new Random();
        return rng.nextInt(size)+1;
    }

    private void rollAll(){
        int i=0;
        for (Dice d : listDice){
            if (d.isVisible()){
                i += d.rollDice();
            }
        }
        TextView resultDiceText = findViewById(R.id.result);
        resultDiceText.setText(Integer.toString(i));
    }

    private void refreshResult(){
        int i=0;
        for (Dice d : listDice){
            if (d.isVisible()){
                i += d.getValue();
            }
        }
        TextView resultDiceText = findViewById(R.id.result);
        resultDiceText.setText(Integer.toString(i));
    }

    private void refreshVisibleDice() {
        ListIterator<Dice> i = listDice.listIterator();
        while(i.hasNext()){
            Dice d = i.next();
                if (!d.isVisible() && i.hasNext()){
                    d.copyDice(listDice.get(i.nextIndex()));
                    d.getButton().getBackground().setColorFilter(getColorFromNumber(d.getMaxValue()), PorterDuff.Mode.MULTIPLY);
                    listDice.get(i.nextIndex()).reset();
                }
        }
    }

    private int getNbDice(){
        int i =0;
        for (Dice d : listDice) {
            if (d.isVisible()) i++;
        }
        return i;
    }

    private void create_listButt(){
        listBut = new ArrayList<Button>();
        listBut.add((Button) findViewById(R.id.button2));
        listBut.add((Button) findViewById(R.id.button4));
        listBut.add((Button) findViewById(R.id.button6));
        listBut.add((Button) findViewById(R.id.button10));
        listBut.add((Button) findViewById(R.id.button20));
        listBut.add((Button) findViewById(R.id.button100));

        listDice = new ArrayList<>();
        listDice.add(new Dice(findViewById(R.id.b1)));
        listDice.add(new Dice(findViewById(R.id.b2)));
        listDice.add(new Dice(findViewById(R.id.b3)));
        listDice.add(new Dice(findViewById(R.id.b4)));
        listDice.add(new Dice(findViewById(R.id.b5)));
        listDice.add(new Dice(findViewById(R.id.b6)));

        listCol= new ArrayList<Integer>();
        listCol.add(getResources().getColor(R.color.purple_pastel));
        listCol.add(getResources().getColor(R.color.pink_pastel));
        listCol.add(getResources().getColor(R.color.orange_pastel));
        listCol.add(getResources().getColor(R.color.green_pastel));
        listCol.add(getResources().getColor(R.color.blue_pastel));
        listCol.add(getResources().getColor(R.color.purple_500));
        listCol.add(getResources().getColor(R.color.yellow_pastel));

    }

    public Integer getColorFromNumber(int i){
        if (i==2) return listCol.get(0);
        if (i==4) return listCol.get(1);
        if (i==6) return listCol.get(2);
        if (i==10) return listCol.get(3);
        if (i==20) return listCol.get(4);
        if (i==100) return listCol.get(5);
        else return listCol.get(6);
    }
}