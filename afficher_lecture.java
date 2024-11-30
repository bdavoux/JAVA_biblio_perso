/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Davoux
 */
public class afficher_lecture extends javax.swing.JFrame {
    private Biblio maBiblio;

    /**
     * Creates new form afficher_lecture
     */
    public afficher_lecture(Biblio b) {
        this.maBiblio = b;
        initComponents();
        setTitle("Liste des Lectures");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        remplirFiltres();
        
         // Ajout de jPanel1 en haut de la fenêtre
        add(jPanel1, BorderLayout.NORTH);

        // Configuration de jPanel2 pour accepter les composants dynamiques
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        
        // ScrollPane pour permettre de scroller la liste si nécessaire
        JScrollPane scrollPane = new JScrollPane(jPanel2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Ajout du scrollPane au centre de la fenêtre (il contient jPanel2)
        add(scrollPane, BorderLayout.CENTER);
        
        // Appel de la méthode pour afficher les livres, tous par défault
        afficherToutesLectures(jPanel2, null,null);
    }
    
    private void afficherToutesLectures(JPanel panel, String genre, String auteur) {
    // Effacer tous les composants existants du panneau
    panel.removeAll();
    
    // Obtenir la liste complète des lecture
    ArrayList<Lecture> lec = maBiblio.uco.obtenirLectures();
    
    // Créer une liste pour stocker les livres filtrés
    ArrayList<Lecture> lecturesFiltrés = new ArrayList<>();
    
    // Appliquer les filtres aux livres
    for (int i = 0; i < lec.size(); i++) {
        Lecture l = lec.get(i);
        Livre livre = l.l;
        boolean correspondGenre = (genre == null || genre.equals("Tous les genres") || livre.genre.equals(genre));
        boolean correspondAuteur = (auteur == null || auteur.equals("Tous les auteurs") || livre.auteur.equals(auteur));
        
        // Ajouter le livre à la liste filtrée si tous les critères sont satisfaits
        if (correspondGenre && correspondAuteur) {
            lecturesFiltrés.add(l);
        }
    }
    
    // Vérifier si la liste filtrée est vide
    if (lecturesFiltrés.isEmpty()) {
        // Ajouter un message indiquant qu'aucun livre ne correspond aux critères
        JLabel message = new JLabel("Aucun livre ne correspond aux critères.");
        panel.add(message);
    } else {
        // Ajouter les livres filtrés au panneau
        for (int i = 0; i < lecturesFiltrés.size(); i++) {
            Lecture l=lecturesFiltrés.get(i);
            Livre livre = l.l;
            
            // Créer un panneau pour chaque livre
            JPanel livrePanel = new JPanel();
            livrePanel.setLayout(new BoxLayout(livrePanel, BoxLayout.Y_AXIS));
            livrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            // Ajouter les détails du livre au panneau
            livrePanel.add(new JLabel("Titre           : " + livre.titre));
            livrePanel.add(new JLabel("Auteur          : " + livre.auteur));
            livrePanel.add(new JLabel("Genre           : " + livre.genre));
            livrePanel.add(new JLabel("Date de publication : " + livre.date_publication));
            livrePanel.add(new JLabel("Numéro ISBN     : " + livre.num_isbn));
            livrePanel.add(new JLabel("Résumé          : " + livre.resume));
            livrePanel.add(new JLabel("Mots clés       : " + String.join(", ", livre.tab_mot_cle)));
            livrePanel.add(new JLabel("Lu              : " + (l.lu ? "Oui" : "Non")));
            livrePanel.add(new JLabel("Note            : " + (l.note >= 0 ? l.note : "Non noté")));
            livrePanel.add(new JLabel("Commentaire     : " + l.commentaire));
            
            
            // Ajouter le panneau du livre au panneau principal
            panel.add(livrePanel);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Ajouter un espace entre les livres
        }
    }
    
    // Revalider et repeindre le panneau pour afficher les changements
    panel.revalidate();
    panel.repaint();
}


     
    private void remplirFiltres(){
        jComboBox2.addItem("Tous les auteurs");
        
        ArrayList<String> auteurs = maBiblio.obtenirAuteurs();
        for (int i = 0; i < auteurs.size(); i++) {
            jComboBox2.addItem(auteurs.get(i));
        }
    } 
    
    private void appliqueFiltres() {
    
    String genreSelectionne = (String) jComboBox1.getSelectedItem();
    String auteurSelectionne = (String) jComboBox2.getSelectedItem();
    String genreFiltre;
    if ("Tous les genres".equals(genreSelectionne)) {
        genreFiltre = null; // Aucun filtre sur le genre
    } else {
        genreFiltre = genreSelectionne; // Filtrer par genre sélectionné
    }

    // Déterminer le filtre d'auteur à appliquer
    String auteurFiltre;
    if ("Tous les auteurs".equals(auteurSelectionne)) {
        auteurFiltre = null; // Aucun filtre sur l'auteur
    } else {
        auteurFiltre = auteurSelectionne; // Filtrer par auteur sélectionné
    }

    // Appliquer les filtres et afficher les livres
    afficherToutesLectures(jPanel2, genreFiltre, auteurFiltre);

}
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtres", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 153, 0))); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tous les genres", "Fiction", "Non-fiction", "Policier / Thriller", "Histoire", "Romance", "Biographie", "Fantastique", "Autres" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Genre:");

        jLabel3.setText("Auteur:");

        jButton1.setText("Réinitialiser les filtres");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(432, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(419, 419, 419))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        jMenu1.setText("Menu");

        jMenuItem1.setText("Revenir au menu principal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        appliqueFiltres();   // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        appliqueFiltres();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        appliqueFiltres();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Fenetre fenetrePrincipale = new Fenetre(maBiblio);
    
    
        fenetrePrincipale.setVisible(true);
    
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(afficher_lecture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(afficher_lecture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(afficher_lecture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(afficher_lecture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
