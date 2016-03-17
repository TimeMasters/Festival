package ihm;

import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import programme.Date;
import programme.ExceptionDate;
import programme.ExceptionFestival;
import programme.Festival;

/**
 * Interface permettant l'ajout de nouveaux festivals
 * @author jgoodwin
 */
public class PanelInitialisation extends JPanel implements ActionListener
{
    private JLabel titre = new JLabel ("Veuillez compléter les champs ci-dessous pour saisir un festival");
    
    //Nom festival
    private JLabel nom = new JLabel ("Nom du festival :");
    private JTextField saisieNom = new JTextField (20);
    
    //Genre
    private JLabel genre = new JLabel ("Genre : ");
    private JComboBox choixGenre ; 
    
    //LES DATES-----------------------------------------------
    //date debut 
    private JLabel dateDeb = new JLabel ("Date du debut : ");
    private JTextField jourDebutField = new JTextField ("jj",2);
    private JTextField moisDebutField =  new JTextField("mm",2);
    private JTextField anneeDebutField = new JTextField("aaaa",4);
    
    //date fin 
    private JLabel dateFin = new JLabel ("Date de Fin: ");  
    private JTextField jourFinField = new JTextField ("jj",2);
    private JTextField moisFinField =  new JTextField("mm",2);
    private JTextField anneeFinField = new JTextField("aaaa",4);

    private JLabel [] separateurDate = new JLabel [4];
    //---------------------------------------------------------
    
    // Lieu 
    private JLabel lieu = new JLabel ("Lieu :");
    private JTextField saisieLieu = new JTextField (20);
    
    //Prix
    private JLabel prix = new JLabel ("Prix :");
    private JTextField saisiePrix = new JTextField (10);
    
    //Places
    private JLabel places =  new JLabel ("Places :");
    private JTextField saisiePlaces = new JTextField (10);
    
    //Image
    private JLabel image = new JLabel ();
   
    //bouton valider
    private JButton valider = new JButton("Valider");
    
    //bouton annuler 
    private JButton annuler = new JButton("Annuler");
    
    //Boite de dialogues
    private JOptionPane pop1 , pop2, pop3, pop4,pop5,pop6; 
    //pop4 et 5 correspondent aux messages d'erreurs pr les acionPerformed des Dates
    //pop 6 correspond au message d'erreur si la date de fin est inferieure a la date de debut
   
    //implementation d'un bouton et d'un label pour choisir l'image d'un festival et l'afficher
    private JButton select = new JButton ("Selectionner une image");
    private JLabel imageLabel;
    private GridBagConstraints contrainte_image;
    private FileNameExtensionFilter filter;
    
    
    /**
     * Constructeur par défaut
     */
    public PanelInitialisation(){
        
        for (int i = 0; i<separateurDate.length; i++){
           separateurDate[i]= new JLabel("/");  
        }
        
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints contrainte = new GridBagConstraints();
        this.setLayout(layout);
        
        //ajout de l'indication
        contrainte.gridx = 1; contrainte.gridy = 1; contrainte.anchor = GridBagConstraints.WEST;
        contrainte.gridheight=1; contrainte.insets= new Insets(10, 10, 10, 10);contrainte.gridwidth = 7; 
        this.add(titre,contrainte);
        
        //ajout du nom du festival 
        contrainte.gridx = 1; contrainte.gridy = 2;contrainte.gridwidth=1;
        this.add(nom,contrainte);
        contrainte.gridx = 2; contrainte.gridy = 2;contrainte.gridwidth = 5;
        this.add(saisieNom,contrainte);
        
        //ajout genre
        contrainte.gridx=1;contrainte.gridy= 3;
        this.add(genre,contrainte);
        contrainte.gridx=2;contrainte.gridy=3;contrainte.gridwidth = 5;
        choixGenre = new JComboBox(constantes.Constantes.GENRE_ITEMS);
        //choixGenre.setSelectedItem(null);
        this.add(choixGenre,contrainte);
        choixGenre.addActionListener(this);
        
        //ajout dateDeb
        contrainte.gridx=1;contrainte.gridy=4;
        this.add(dateDeb,contrainte);
        contrainte.gridx=2;contrainte.gridy=4;contrainte.gridwidth=1;
        this.jourDebutField.setToolTipText("Entrez un jour");
        this.add(jourDebutField,contrainte);
        contrainte.gridx=3;contrainte.gridy=4;contrainte.gridwidth = 1;
        this.moisDebutField.setToolTipText("Entrez un mois");
        this.add(separateurDate[0],contrainte);
        contrainte.gridx=4;contrainte.gridy=4;contrainte.gridwidth = 1;
        this.anneeDebutField.setToolTipText("Entrez une année");
        this.add(moisDebutField,contrainte);
        contrainte.gridx=5;contrainte.gridy=4;contrainte.gridwidth = 1;
        this.add(separateurDate[1],contrainte);
        contrainte.gridx=6;contrainte.gridy=4;contrainte.gridwidth = 1;
        this.add(anneeDebutField,contrainte);
        
        //ajout dateFin
        contrainte.gridx=1;contrainte.gridy=5;
        this.add(dateFin,contrainte);
        contrainte.gridx=2;contrainte.gridy=5;contrainte.gridwidth=1;
        this.jourFinField.setToolTipText("Entrez un jour");
        this.add(jourFinField,contrainte);
        contrainte.gridx=3;contrainte.gridy=5;contrainte.gridwidth = 1;
        this.moisFinField.setToolTipText("Entrez un mois");
        this.add(separateurDate[2],contrainte);
        contrainte.gridx=4;contrainte.gridy=5;contrainte.gridwidth = 1;
        this.anneeFinField.setToolTipText("Entrez une année");
        this.add(moisFinField,contrainte);
        contrainte.gridx=5;contrainte.gridy=5;contrainte.gridwidth = 1;
        this.add(separateurDate[3],contrainte);
        contrainte.gridx=6;contrainte.gridy=5;contrainte.gridwidth = 1;
        this.add(anneeFinField,contrainte);
        
    
        //ajout lieu
        contrainte.gridx=1;contrainte.gridy=6;
        this.add(lieu,contrainte);
        contrainte.gridx=2;contrainte.gridy=6;contrainte.gridwidth = 5;
        this.add(saisieLieu,contrainte);
        
        //ajout prix
        contrainte.gridx=1;contrainte.gridy=7;
        this.add(prix,contrainte);
        contrainte.gridx=2;contrainte.gridy=7;contrainte.gridwidth = 5;
        this.add(saisiePrix,contrainte);
        
        //ajout places totales
        contrainte.gridx=1;contrainte.gridy=8;
        this.add(places,contrainte);
        contrainte.gridx=2;contrainte.gridy=8;contrainte.gridwidth = 5;
        this.add (saisiePlaces,contrainte);
        
        
        //ajout boutons
        contrainte.gridx=3;contrainte.gridy =9;
        annuler.addActionListener(this);
        this.add(annuler,contrainte);
        contrainte.gridx=6;contrainte.gridy=9;
        valider.addActionListener(this);
        this.add(valider,contrainte);
        
        //ajout d'un label et d'un bouton pour selectionner une image
        contrainte.gridx=7;contrainte.gridy= 7;contrainte.gridwidth=3;contrainte.anchor= GridBagConstraints.EAST;
        this.add(select,contrainte);
        select.addActionListener(this);
        
        contrainte_image = new GridBagConstraints();
        contrainte_image.gridx=8;contrainte_image.gridy = 3; contrainte_image.gridwidth=4;contrainte_image.gridheight= 4;
        imageLabel = new JLabel(new ImageIcon(),JLabel.CENTER);
        this.add(imageLabel,contrainte_image);
        
    }

    /**
     * Répond à un évenement lié à l'IHM
     * @param e Evenement à gérer
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //Si l'on clique sur le bouton annuler , tout les champs reviennent dans leur etat par defaut
        if (e.getSource() == this.annuler)
        {
            this.saisieNom.setText("");
            this.choixGenre.setSelectedItem("Rock");
            this.jourDebutField.setText("jj");
            this.jourFinField.setText("jj");
            this.moisDebutField.setText("mm");
            this.moisFinField.setText("mm");
            this.anneeDebutField.setText("aaaa");
            this.anneeFinField.setText("aaaa");
            this.saisieLieu.setText("");
            this.saisiePrix.setText("");
            this.saisiePlaces.setText("");
            this.imageLabel.setIcon(new ImageIcon());
            updateUI();
        }//fin Action Performed du Bouton Annuler
        
        //
        if (e.getSource()== this.valider)
        {
            if (this.saisieNom.getText().equals("")){
                pop1.showMessageDialog(null,"Nom du festival manquant","Erreur",JOptionPane.ERROR_MESSAGE);
                //System.out.println("Nom de Festival Vide");
            }
            else if (this.saisieLieu.getText().equals("")){
                pop2.showMessageDialog(null,"Lieu manquant !","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else if (this.saisiePrix.getText().equals("")){
                pop3.showMessageDialog(null,"Prix non indiqué !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (this.saisiePlaces.getText().equals("")){
                pop6.showMessageDialog(null,"Places totales non indiquées !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                //Events sur les dates
                //Date de debut
                String jourDebut = this.jourDebutField.getText();
                String moisDebut = this.moisDebutField.getText();
                String anneeDebut = this.anneeDebutField.getText();
                Date dateDebut = new Date();
                try{
                    int jour = Integer.parseInt(jourDebut);
                    int mois = Integer.parseInt(moisDebut);
                    int annee = Integer.parseInt(anneeDebut);
                    dateDebut = new Date(jour,mois,annee);
                }
                catch(NumberFormatException exception){
                    pop4.showMessageDialog(null,"Entrez un format de Date Debut valide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                } 
                catch (ExceptionDate exception){
                    pop5.showMessageDialog(null,"Date Debut invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                //Date de fin
                String jourFin = this.jourFinField.getText();
                String moisFin = this.moisFinField.getText();
                String anneeFin = this.anneeFinField.getText();
                Date dateFin = new Date();
                try{
                    int jour = Integer.parseInt(jourFin);
                    int mois = Integer.parseInt(moisFin);
                    int annee = Integer.parseInt(anneeFin);
                    dateFin = new Date(jour,mois,annee);

                }
                catch(NumberFormatException exception){
                    pop4.showMessageDialog(null,"Entrez un format de Date Fin valide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                } 
                catch (ExceptionDate exception){
                    pop5.showMessageDialog(null,"Date Fin invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                if (dateFin.precede(dateDebut)== 1){
                    pop6.showMessageDialog(null,"La date de fin est inférieure à la date de debut","Erreur",JOptionPane.ERROR_MESSAGE);
                }
                else //Si la date de fin est égale ou supérieure à la date début
                {
                    if (!programme.Programme.getListeFestival().containsKey(this.saisieNom.getText()))
                    {
                        try{
                            programme.Programme.getListeFestival().put(this.saisieNom.getText(), 
                                new Festival(this.saisieNom.getText(), (String)this.choixGenre.getSelectedItem(), 
                                        dateDebut, dateFin, this.saisieLieu.getText(), Integer.parseInt(this.saisiePrix.getText()), 
                                        (ImageIcon)imageLabel.getIcon(), Integer.parseInt(this.saisiePlaces.getText())));
                        sauvegarde.MethodesPourFichier.ecriture(new File("programme.save"),programme.Programme.getListeFestival());
                        new JOptionPane().showMessageDialog(null, "Festival ajouté", "Festival ajouté", JOptionPane.INFORMATION_MESSAGE);
                        this.annuler.doClick();
                        }catch(NumberFormatException exception){
                            new JOptionPane().showMessageDialog(null, "Nombre de places ou prix doivent etre sous format numériques","Erreur",JOptionPane.ERROR_MESSAGE);
                        } catch (ExceptionFestival ex) {
                            new JOptionPane().showMessageDialog(null, ex.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        new JOptionPane().showMessageDialog(null, "Un festival avec le meme nom existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }//Fin Action Performed pour le bouton Valider
        
        
        if (e.getSource() == select ){
            JFileChooser imagePath = new JFileChooser();
            imagePath.setDialogTitle("Choix d'une image");
            imagePath.setAcceptAllFileFilterUsed(false);
            filter = new FileNameExtensionFilter("Images","bmp","jpg","png","jpeg","ico");
            imagePath.addChoosableFileFilter(filter);
            int resultat = imagePath.showOpenDialog(null);
            if (resultat == JFileChooser.APPROVE_OPTION){
                File file = imagePath.getSelectedFile();
                String chemin = file.getAbsolutePath();
                ImageIcon icone = new ImageIcon(chemin);
                icone.getImage().flush();
                imageLabel.setIcon(icone);
                updateUI(); 
            }
        }//Fin Action Performed pour le bouton Select
    }
}

