package com.alixdufour.ludotools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pour lancer DiceActivity pour que je teste vous pouvez supp pour remplacer par menu
        b  = (Button) findViewById(R.id.b1);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SortActivity.class);
                startActivity(i);

            }
        });


    }
}