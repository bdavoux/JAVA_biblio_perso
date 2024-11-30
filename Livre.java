/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet;
import java.io.*;


/**
 *
 * @author Davoux
 */
public class Livre implements Serializable{
    String titre;
    String auteur;
    String genre; // Pas dispo dans l'Api
    String date_publication;
    String num_isbn;
    String resume;
    boolean lu = false;
    String commentaire = "";
    int note = -1;
    String [] tab_mot_cle = new String [5];
    boolean pret;
    Ami a;
    
    public Livre (String t, String a, String g, String d, String n, String r, String [] tab) {
        this.titre = t;
        this.auteur = a;
        this.genre = g;
        this.date_publication = d;
        this.num_isbn = n;
        this.resume= r;
        this.tab_mot_cle = new String[tab.length];
        for(int i = 0; i < tab.length; i++) {
            this.tab_mot_cle[i] = tab[i];
        }
        this.pret=false;

    }
    public String donne_titre(){
        return this.titre;
    }
    
    public String infos(){
        return this.titre+","+this.auteur+","+this.resume+","+this.genre;
    }
    
    public void preter(Ami am){
        this.pret = true;
        this.a = am;
    }
    
    public void retourner() {
        this.pret = false;
        this.a = null;
    }
    
    @Override
    public String toString(){
        return titre;
    }
    
    
    
}
