package com.alixdufour.ludotools.objects;

import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Dice {
    private Button button;
    private int maxValue;
    private int value;

    public Dice(Button b, int max){
        button = b;
        maxValue = max;
        value=0;

    }

    public Dice(Button b){
        button = b;
        maxValue = 0;
        value=0;
    }

    public Button getButton() {
        return button;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int actuelValue) {
        this.value = actuelValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isVisible() {
        if (button.getVisibility()==View.VISIBLE) return true;
        return false;
    }

    public void reset(){
        maxValue=0;
        value=0;
        addValueToButton();
        button.setVisibility(View.INVISIBLE);
    }

    public int rollDice(){
        Random rng = new Random();
        value = rng.nextInt(maxValue)+1;
        addValueToButton();
        return value;
    }

    private void addValueToButton(){
        button.setText(Integer.toString(value));
    }
}
