package ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import programme.Festival;

/**
 * Interface permettant de voir les festivals à reserver pour ensuite reserver des places pour le dit festival
 * @author jgoodwin
 */
public class PanelListeReservation extends JPanel implements ActionListener
{
    private Dimension dimensionPanel;
    /**
     * Constructeur par défaut
     */
    public PanelListeReservation()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        dimensionPanel = new Dimension(new Dimension(constantes.Constantes.DIMENSION_FENETRE.width-20, constantes.Constantes.DIMENSION_FENETRE.height-60));
        
        JPanel panelBoutons = new JPanel(new GridLayout(0, 3));
        panelBoutons.setBackground(constantes.Constantes.BLEU_CIEL_COLOR);
        
        JScrollPane scrollPane = new JScrollPane(panelBoutons,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(dimensionPanel);
        
        Set <String> key = programme.Programme.getListeFestival().keySet();
        Iterator iterator = key.iterator();
        while (iterator.hasNext())
        {
            String cle = (String)iterator.next();
            Festival festival = (Festival)programme.Programme.getListeFestival().get(cle);
            BoutonFestival boutonFestival = new BoutonFestival(festival,dimensionPanel);
            boutonFestival.addActionListener(this);
            int nbrPlace = 0;
            for (int i = 0; i<festival.getNbrPlaces().length; i++)
                nbrPlace += festival.getNbrPlaces()[i];
            if (nbrPlace == 0)
                panelBoutons.setEnabled(false);
            panelBoutons.add(boutonFestival);
        }
        add(scrollPane);
    }

    /**
     * Methode s'éxécutant à la suite d'un évenement
     * @param e Evenement auquel la méthode répondra
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource().getClass() == BoutonFestival.class)
        {
            BoutonFestival boutonFestival = (BoutonFestival)e.getSource();
            boutonFestival.setCouleurBouton(constantes.Constantes.BLEU_CIEL_COLOR);
            JDialog dialog = new JDialog();
            dialog.setModal(true);
            Point positionSouris = getMousePosition();
            dialog.setSize(450,300);dialog.setLocation(positionSouris);
            dialog.setTitle("Reservation - "+boutonFestival.getFestival().getNom());
            dialog.setContentPane(new PanelReservation(boutonFestival.getFestival()));
            dialog.setVisible(true);
            boutonFestival.setCouleurBouton(constantes.Constantes.BLEU_VIOLET_COLOR);
            
            Festival festival = boutonFestival.getFestival();
            int nbrPlace = 0;
            for (int i = 0; i<festival.getNbrPlaces().length;i++)
                nbrPlace+=festival.getNbrPlaces()[i];
            if (nbrPlace == 0)
            {
                boutonFestival.setEnabled(false);
                boutonFestival.setCouleurBouton(constantes.Constantes.GRIS_BLEU_COLOR);
            }
            String festivalString;
            if (festival.getDateDebut().getAnnee()==festival.getDateFin().getAnnee())
                festivalString = "<html>"+ festival.getNom()+"<br>" + festival.getGenre()+"<br>"+ festival.getDateDebut().toString()+ " au " + festival.getDateFin().toString() +" "+ festival.getDateFin().getAnnee()+ "<br>à " + festival.getLieu() + "<br>" + festival.getPrix() + "€<br>" + nbrPlace + " places diponibles";
            else
                festivalString = "<html>"+ festival.getNom()+"<br>" + festival.getGenre()+"<br>"+ festival.getDateDebut().toString()+" "+ festival.getDateDebut().getAnnee()+ " au " + festival.getDateFin().toString() +" "+ festival.getDateFin().getAnnee()+ "<br>à " + festival.getLieu() + "<br>" + festival.getPrix() + "€<br>" + nbrPlace + " places diponibles";
            festivalString += "<br><br><i>Reservez</i></html>";
            boutonFestival.getLabelInfo().setText(festivalString);
            //Attend 1/2 seconde avant de redonner la main afin d'éviter... 
            //...un bug ou l'on relancer une fenetre avant que l'autre ne fut completement fermée
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelListeReservation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
