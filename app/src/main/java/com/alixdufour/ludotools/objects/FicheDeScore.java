package com.alixdufour.ludotools.objects;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FicheDeScore {
    //private List<CategoriePoints> listePoints;
    private List<String> joueurs;
    private List<String> categories;
    private Context context;
    private int[][] tableauScore; // [index joueurs] [index categorie]
    TableLayout tableauAffichage;
    private ArrayList<TableRow> listeTableRow = new ArrayList<>(); //que celles des catégories

    public FicheDeScore(ArrayList<String> categories, ArrayList<String> joueurs, TableLayout tableAffichage, Context context){
        this.tableauAffichage = tableAffichage;
        this.context = context;
        this.joueurs = joueurs;
        this.categories = categories;
        tableauScore = new int[joueurs.size()][categories.size()];
        initializeTableauScore();
        creerTableauAffichage();
    }

    public void initializeTableauScore(){
        for (int i=0; i< joueurs.size(); i++){
            for (int j=0; j<categories.size(); j++){
                tableauScore[i][j] = 0;
            }
        }
    }

    public TableRow creerLigneNoms(){
        TableRow tableRow = new TableRow(context);
        tableRow.addView(new TextView(context));
        for (String name : joueurs){
            TextView t = new TextView(context);
            t.setText(name);
            t.setTextColor(Color.BLACK);
            t.setTypeface(null, Typeface.BOLD);
            tableRow.addView(t);
        }
        return tableRow;
    }

    public ArrayList<TableRow> creerLignesScore(){
        ArrayList<TableRow> tableRows = new ArrayList<>();
        if (categories==null || joueurs==null) {
            System.out.println("catnull");
            return null;
        }
        for ( int j=0; j<categories.size(); j++){
            int jj= j;
            TableRow tr = new TableRow(context);
            System.out.println(categories.get(j));
            TextView name = new TextView(context);
            name.setText(categories.get(j));
            name.setTextColor(Color.BLACK);
            name.setTextSize(16);
            name.setTypeface(null, Typeface.BOLD);
            tr.addView(name);

            for (int i=0; i<joueurs.size(); i++){
                EditText et = new EditText(context);
                et.setInputType(InputType.TYPE_CLASS_NUMBER);
                et.setText("0");
                int ii=i;
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (et.getText() == null) tableauScore[ii][jj] = 0;
                        else tableauScore[ii][jj] = Integer.parseInt(et.getText().toString());
                    }
                });
                tr.addView(et);
            }

            tableauAffichage.addView(tr);
            tableRows.add(tr);
        }
        return tableRows;
    }

    public void creerTableauAffichage(){
        if (joueurs==null) return;
        tableauAffichage.addView(creerLigneNoms());
        listeTableRow = creerLignesScore();
    }

    public String printScore(){
        String s = "";
        for (int i=0; i< joueurs.size(); i++){
            int score=0;
            for (int j=0; j<categories.size();j++){
                score += tableauScore[i][j];
            }
            s += joueurs.get(i) +" : " + score +"\n";
        }
        return s;
    }



}

/*
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
*/