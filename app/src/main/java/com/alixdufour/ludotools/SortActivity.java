package com.alixdufour.ludotools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortActivity extends AppCompatActivity {

    Button buttonAdd;
    Button buttonSort;
    List player = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        buttonAdd = (Button) findViewById(R.id.AddButton);
        buttonSort = (Button) findViewById(R.id.SortButton);

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
                    text += player.get(i) + "\n";
                }
                nameList.setText(text);
                nameEntered.setText("");
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
                    text += player.get(i1) + "\n";
                }
                nameList.setText(text);
            }
        });
    }
}