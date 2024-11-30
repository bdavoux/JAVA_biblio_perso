/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davoux
 */
public class Utilisateur implements Serializable {
    String login;
    String motDePasse;
    ArrayList<Lecture> livreslus = new ArrayList<Lecture>();

    public Utilisateur(String l, String mdp) {
        this.login = l;
        this.motDePasse = mdp;
    }

    public String obtenirLogin() {
        return login;
    }

    public String obtenirMdp() {
        return motDePasse;
    }
    
    public void ajouterLecture(Livre l,int n,String c){
        Lecture lec = new Lecture(l,n,c);
        livreslus.add(lec);
        
    }
    
    public ArrayList<Lecture> obtenirLectures() {
        return livreslus;
    }
    
}
