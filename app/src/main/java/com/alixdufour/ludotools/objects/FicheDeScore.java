package com.alixdufour.ludotools.objects;

import android.graphics.Color;

import java.util.List;

public class FicheDeScore {
    private List<CategoriePoints> listePoints;
    private List<Player> listePlayer;

    public FicheDeScore(){

    }


}

class CategoriePoints {
    private String name;
    private int score;
    private Boolean isBoolean; //si true c'est un élément qui ne comptabilise pas les points mais est juste vrai ou faux
    private int multiplicateur;
    private Color color; //si aucune c'est background
    private Boolean toAdditionner; //si true est additionné au score, si faux est multiplié
    private Boolean bonus; //si true bonus, sinon malus (soustraction ou division), si pas défini bonus

    CategoriePoints(String n, int multi, Color c, Boolean isBool ){

    }


}

class Player {
    private String name;
    private int scoreTotal;
}