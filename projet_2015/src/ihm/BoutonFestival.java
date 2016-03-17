package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import programme.Date;
import programme.Festival;

/**
 * Bouton permettant d'afficher l'affiche du festival ainsi que ses informations
 * @author jgoodwin
 */
public class BoutonFestival extends JButton
{
    private Festival festival;
    private JPanel panelBouton;
    private JLabel labelInfo;
    private JLabel labelAffiche;
    
    /**
     * Constructeur par défaut
     * @param parFestival festival auquel le bouton est lié
     * @param panelDimension dimension du panel père
     */
    public BoutonFestival(Festival parFestival,Dimension panelDimension)
    {
        super();
        
        festival = parFestival;
        
        panelBouton = new JPanel();
        panelBouton.setLayout(new BorderLayout(8, 0));
        
        labelAffiche = new JLabel(festival.getAffiche());
        Dimension dim = new Dimension(festival.getAffiche().getIconWidth()+5, festival.getAffiche().getIconHeight()+5);
        labelAffiche.setPreferredSize(dim);
        panelBouton.add(labelAffiche,BorderLayout.WEST);
        
        int nbrPlace = 0;
        String festivalString;
        for (int i = 0; i<festival.getNbrPlaces().length;i++)
            nbrPlace+=festival.getNbrPlaces()[i];
        if (festival.getDateDebut().getAnnee()==festival.getDateFin().getAnnee())
            festivalString = "<html>"+ festival.getNom()+"<br>" + festival.getGenre()+"<br>"+ festival.getDateDebut().toString()+ " au " + festival.getDateFin().toString() +" "+ festival.getDateFin().getAnnee()+ "<br>à " + festival.getLieu() + "<br>" + festival.getPrix() + "€<br>" + nbrPlace + " places diponibles";
        else
            festivalString = "<html>"+ festival.getNom()+"<br>" + festival.getGenre()+"<br>"+ festival.getDateDebut().toString()+" "+ festival.getDateDebut().getAnnee()+ " au " + festival.getDateFin().toString() +" "+ festival.getDateFin().getAnnee()+ "<br>à " + festival.getLieu() + "<br>" + festival.getPrix() + "€<br>" + nbrPlace + " places diponibles";
        festivalString += "<br><br><i>Reservez</i></html>";
        
        labelInfo = new JLabel(festivalString);
        panelBouton.add(labelInfo,BorderLayout.EAST);
        
        this.setCouleurBouton(constantes.Constantes.BLEU_VIOLET_COLOR);
        this.setPreferredSize(new Dimension(panelDimension.width/3-3*3, panelDimension.height/4-3*3));
        if (nbrPlace == 0 || (festival.getDateFin().precede(new Date())==1))
        {
            this.setEnabled(false);
            this.setCouleurBouton(constantes.Constantes.GRIS_BLEU_COLOR);
        }
        add(panelBouton);
    }
    
    /**
     * Change la couleur du bouton
     * @param color couleur du bouton après mise à jour
     */
    public void setCouleurBouton(Color color)
    {
        setBackground(color);
        labelAffiche.setBackground(color);
        labelInfo.setBackground(color);
        panelBouton.setBackground(color);
    }
    
    /**
     * Renvoie le festival auquel le bouton est lié
     * @return festival auquel le bouton est lié
     */
    public Festival getFestival()
    {
        return this.festival;
    }
    
    /**
     * Renvoie le label informatif du bouton
     * @return label informatif du bouton
     */
    public JLabel getLabelInfo()
    {
        return  this.labelInfo;
    }
}