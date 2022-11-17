package com.alixdufour.ludotools;

public class Joueur {

    private String name;
    private int score;

    public Joueur(String n) {
        this.name = n;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore() { score ++;}
}
