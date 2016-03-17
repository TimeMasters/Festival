package ihm;


import constantes.Constantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import programme.Festival;
import programme.Programme;

/**
 * Fenetre principale du programme dans laquelle la plupart des écran s'afficheront
 * @author jgoodwin
 */
public class FenetreProgramme extends JFrame implements ActionListener
{    
    
    private JMenuBar menuBar;
    private JMenuItem[] menuItem = new JMenuItem[3];
    /**
     * FenetreProgramme est la FenetreMere
     * Constructeur par défaut
     */
    public FenetreProgramme()
    {
        super("Projet Tutoré Ahmed/Goodwin");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(constantes.Constantes.DIMENSION_FENETRE.width,Constantes.DIMENSION_FENETRE.height); 
        setVisible(true); setLocation(200,300); setResizable(false);
        
        //Ajout du panel initialisation a la fenetre mere
        //PanelConsultationFestival contentPane = new PanelConsultationFestival();
        //this.setContentPane(contentPane);
        
        
        //Ajout d'une barre de Menu à la fenêtre mère
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        //Initialistation des menus items
        for (int i = 0; i<constantes.Constantes.MENU_ITEMS.length; i++)
        {
            menuItem[i] = new JMenuItem(constantes.Constantes.MENU_ITEMS[i]);
            menuItem[i].addActionListener(this);
            menuBar.add(menuItem[i]);
        }
        //Création ou lecture du programme
        if (new File("programme.save").exists())
            new Programme("programme.save");
        else
            new Programme();
        setContentPane(new PanelConsultationFestival());
        validate();
    }
    
    /**
     * Méthode d'éxécution du programme
     * @param args 
     */
    public static void main (String args[])
    {
        new FenetreProgramme();
    }

    /**
     * Répond à un évenement lié à l'IHM
     * @param e Evenement à gérer
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == menuItem[0])
        {   
            //Affiche le PanelInitialisation si l'on clique sur le menu Initialisation
            setContentPane(new PanelInitialisation());
            validate();
        }
        if (e.getSource() == menuItem[1])
        {   
            //Affiche le Panneau permettant de visualiser les festivals
            setContentPane(new PanelConsultationFestival());
            validate();
        }
        if (e.getSource() == menuItem[2]) 
        {
            //Affiche la Panel Reservation
            setContentPane(new PanelListeReservation());
            validate();
        }
    }

}
