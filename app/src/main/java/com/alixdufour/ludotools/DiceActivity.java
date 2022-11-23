package com.alixdufour.ludotools;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    public int diceSize=6;
    public List<Integer> listCol; //liste des couleurs des boutons
    public List<Button> listBut; //liste des boutons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        create_listButt();
        Button buttonRoll  = (Button) findViewById(R.id.StartDice);
        TextView resultDiceText = findViewById(R.id.result);

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
                buttonRoll.setBackgroundColor(getResources().getColor(R.color.yellow_pastel));
                int value = Integer.parseInt((String) editValue.getText().toString());
                diceSize = value;
                buttonRoll.setText("Roll "+value);
            }
        });


        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast= petite pop up de texte à décomenter si voulue
                //Toast.makeText(getApplicationContext(), "Dice Rolled!", Toast.LENGTH_SHORT).show();

                //Affichage du résultat du dé
                if (diceSize == 0 ) resultDiceText.setText("0");
                else  resultDiceText.setText(Integer.toString(rollDice(diceSize)));
            }
        });

        for ( Button b : listBut) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast= petite pop up de texte
                    //Toast.makeText(getApplicationContext(), "Dice value changed to "+b.getText(), Toast.LENGTH_SHORT).show();

                    //Je change la couleur du dé roll par la couleur du dé sur lequel on a appuyé
                    buttonRoll.setBackgroundColor(listCol.get(listBut.indexOf(b)));

                    //Je change la valeur max du dé qui peut être roll
                    diceSize = Integer.parseInt((String) b.getText());
                    buttonRoll.setText("Roll "+b.getText());
                }
            });
        }


    }

    private int rollDice(int size){
        Random rng = new Random();
        return rng.nextInt(size)+1;
    }

    private void create_listButt(){
        listBut = new ArrayList<Button>();
        listBut.add((Button) findViewById(R.id.button2));
        listBut.add((Button) findViewById(R.id.button4));
        listBut.add((Button) findViewById(R.id.button6));
        listBut.add((Button) findViewById(R.id.button10));
        listBut.add((Button) findViewById(R.id.button20));
        listBut.add((Button) findViewById(R.id.button100));

        listCol= new ArrayList<Integer>();

        listCol.add(getResources().getColor(R.color.purple_pastel));
        listCol.add(getResources().getColor(R.color.pink_pastel));
        listCol.add(getResources().getColor(R.color.orange_pastel));
        listCol.add(getResources().getColor(R.color.green_pastel));
        listCol.add(getResources().getColor(R.color.blue_pastel));
        listCol.add(getResources().getColor(R.color.purple_500));

    }
}