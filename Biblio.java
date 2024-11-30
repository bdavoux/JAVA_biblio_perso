/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet;

/**
 *
 * @author Davoux
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Biblio implements Serializable {
    ArrayList<Livre> livres = new ArrayList<Livre>();
    ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    String nom;
    Utilisateur uco;
    private static final String API_GOOGLE_BOOKS = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public Biblio(String n) {
        this.nom = n;
    }

    public String[] recupererInfosauto(String isbn) {
    try {
        URL url = new URL(API_GOOGLE_BOOKS + isbn);
        HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
        connexion.setRequestMethod("GET");

        BufferedReader lecteur = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
        StringBuilder contenu = new StringBuilder();
        String ligne;
        while ((ligne = lecteur.readLine()) != null) {
            contenu.append(ligne);
        }
        lecteur.close();

        // Parser le contenu JSON
        JSONObject jsonResponse = new JSONObject(contenu.toString());
        JSONArray items = jsonResponse.getJSONArray("items");
        if (items.length() > 0) {
            JSONObject volumeInfo = items.getJSONObject(0).getJSONObject("volumeInfo");

            // Extraction des données
            String titre = volumeInfo.getString("title");
            String auteur = volumeInfo.getJSONArray("authors").getString(0); // Premier auteur
            String datePublication = volumeInfo.getString("publishedDate");
            String resume = volumeInfo.has("description") ? volumeInfo.getString("description") : "Pas de résumé disponible";

            // Retourner les informations sous forme de tableau
            return new String[] {titre, auteur, datePublication, resume};
        } 

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Erreur lors de la récupération des informations du livre.");
    }

    // En cas d'erreur, retourner un tableau vide ou un tableau avec des messages par défaut
    return new String[] {"Titre non trouvé", "Auteur non trouvé", "Date non trouvée", "Résumé non trouvé"};
}


    // Charger tous les livres depuis un fichier
    public void chargerLivres(String nomFichier) {
        try (FileInputStream fis = new FileInputStream(nomFichier);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.livres = (ArrayList<Livre>) ois.readObject();
            System.out.println("Tous les livres ont été chargés avec succès.");
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des livres : " + e.getMessage());
        }
    }
    
        // Sauvegarder tous les livres dans un fichier
    public void sauvegarderLivres(String nomFichier) {
        try (FileOutputStream fos = new FileOutputStream(nomFichier);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this.livres);
            System.out.println("Tous les livres ont été sauvegardés avec succès.");
        }
        catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des livres : " + e.getMessage());
        }
    }

    // Ajouter un livre à la collection
    public void ajouterLivre(Livre l) {
        this.livres.add(l);
        
    }
    
    public ArrayList<Livre> obtenirLivres() {
       return this.livres;
    }
    public ArrayList<String> obtenirAuteurs() {
       ArrayList<String> auteurs = new ArrayList<>();
       
       if (livres.size()>0){
       for (int i =0;i<livres.size();i++){
           String a = livres.get(i).auteur;
           if(!auteurs.contains(a)){
               auteurs.add(a);
           }           
       }
       return auteurs;
       }
       else{
           return null;
       }   
        
    }
    
    public ArrayList<String> obtenirAmis(){
        ArrayList<String> amis = new ArrayList<>();
        boolean bool = false;
        if (livres.size()>0){
        for (int i =0;i<livres.size();i++){
            if(livres.get(i).a != null){
                String a = livres.get(i).a.nom;
                if(!amis.contains(a)){
                    amis.add(a);
                    bool = true;
                
                } 
            }         
        }
        }
        if (bool){
            return amis;
        }
        else{
           return null;
        }   
        
    }
    
    public void chargerUtilisateurs(String nomFichier) {
        try (FileInputStream fis = new FileInputStream(nomFichier);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.utilisateurs = (ArrayList<Utilisateur>) ois.readObject();
            System.out.println("Tous les utilisateurs ont été chargés avec succès.");
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
    }
    
    public boolean verifierConnexion(String login, String motDePasse) {
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur u = utilisateurs.get(i);
            if (u.obtenirLogin().equals(login) && u.obtenirMdp().equals(motDePasse)) {
                this.uco = u;
                return true;
            }
        }
        return false;
    }
    
    public void sauvegarderUtilisateurs(String nomFichier) {
    try (FileOutputStream fos = new FileOutputStream(nomFichier);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(this.utilisateurs);
        System.out.println("Tous les utilisateurs ont été sauvegardés avec succès.");
    }
    catch (IOException e) {
        System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
    }
}
    
    public void ajouterUtilisateur(Utilisateur u) {
        this.utilisateurs.add(u);
    }
    
    public int nbreLivres(){
        return this.livres.size();
    }
    
    public int nbreLuU(){
        int n = 0;
        if (uco.livreslus.size()>0){
            for(int i =0;i<uco.livreslus.size();i++){
                if(uco.livreslus.get(i).lu){
                    n++;
                }
            }
            return n;
        }
        else{
            return n;
        }
    }
    
    public int recupBonnesNotes(){
        int b = 0;
        if (uco.livreslus.size()>0){
            for(int i=0;i<uco.livreslus.size();i++){
                if(uco.livreslus.get(i).note == 5){
                    b++;
                }
            }
            return b;
            
        }
        else{
            return b;
        }
    }
    
    public String retourneLoginUtiliCo(){
        return uco.login;
    }
    
    public void ajouterLectureU(Livre l,int n,String c){
        if (uco != null) {
            uco.ajouterLecture(l,n,c);
        System.out.println("Lecture ajoutée pour l'utilisateur " + uco.obtenirLogin());
        }
        else {
        System.out.println("Aucun utilisateur connecté.");
        }
    }
    
    public boolean supprimerLivre(String isbn) {
    
    for (int i = 0; i < livres.size(); i++) {
        Livre livre = livres.get(i);
       
        if (livre.num_isbn.equals(isbn)) {
            
            livres.remove(i);
            return true; 
        }
    }
    return false; 
}

}
