/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet;
import java.io.Serializable;

/**
 *
 * @author Davoux
 */
public class Lecture implements Serializable {
    Livre l;
    boolean lu = true;
    int note;
    String commentaire;
    
    public Lecture(Livre liv, int n,String c){
        
        this.l = liv;
        this.commentaire = c;
        this.note = n;
    }
    
}
