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
public class Ami implements Serializable {
    String prenom;
    String nom;
    String email;
    
    public Ami(String p, String n, String e){
        this.prenom = p;
        this.nom= n;
        this.email = e;
    }
    
    
}
