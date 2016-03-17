package ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import programme.Date;
import programme.Festival;

/**
 * Panel permettant de reserver un festival
 * @author jgoodwin
 */
public class PanelReservation extends JPanel implements ActionListener
{
    private Festival festival;
    private JLabel nomClientLabel;
    private JTextField nomClientField;
    private JButton boutonReserver;
    private JButton boutonAnnuler;
    private JLabel labelNbrPlace;
    private JSlider choixNbrPlace;
    private JTextField choixNbrPlaceField;
    private JLabel labelDate;
    private JComboBox<String> choixDate;
    private Date[] datesFestival;
    
    /**
     * Constructeur par défaut
     * @param parfestival festival auquel le panel sera lié
     */
    public PanelReservation(Festival parfestival)
    {
        festival = parfestival;
        setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.gridx=1; contrainte.gridy=1; contrainte.insets = new Insets(8, 8, 8, 8);
        
        nomClientLabel = new JLabel("Nom du client : ");
        contrainte.anchor = GridBagConstraints.EAST;
        add(nomClientLabel,contrainte);
        contrainte.anchor = GridBagConstraints.CENTER;
        
        nomClientField = new JTextField(16);
        contrainte.gridwidth = 3; contrainte.gridx = 2;
        add(nomClientField, contrainte);
        contrainte.gridwidth=1;
        
        boutonReserver = new JButton("Réserver : "+festival.getPrix()+"€");
        
        int ecartDates = festival.getDateDebut().ecartDate(festival.getDateFin());
        datesFestival = new Date[ecartDates+1];
        datesFestival[0] = festival.getDateDebut();
        for (int i = 1; i<=datesFestival.length-1; i++)
            datesFestival[i] = datesFestival[i-1].dateDemain();
        festival.setDateDebut(datesFestival[0]);
        
        labelDate = new JLabel("Choisir date : ");
        contrainte.gridx = 1; contrainte.gridy = 2;contrainte.anchor = GridBagConstraints.EAST;
        add(labelDate,contrainte);
        
        
        String[] dateNumeric = new String[datesFestival.length];
        for (int i = 0; i<datesFestival.length;i++)
            dateNumeric[i]=datesFestival[i].toNumeric();
        choixDate = new JComboBox<>(dateNumeric);
        int indice = 0;
        while (indice<dateNumeric.length && festival.getNbrPlaces()[indice]==0)
        {
            choixDate.setSelectedItem(dateNumeric[++indice]);
        }
        contrainte.gridx = 2; contrainte.anchor = GridBagConstraints.WEST;
        choixDate.addActionListener(this);
        add(choixDate,contrainte);

        int jour = 0;
        for (int i = 0; i<datesFestival.length; i++)
        {
            if (choixDate.getSelectedItem().equals(datesFestival[i].toNumeric()))
                jour = i;
        }

        choixNbrPlaceField = new JTextField(3);
        choixNbrPlaceField.setText("1");
        contrainte.gridx=3; contrainte.gridy=3; contrainte.anchor= GridBagConstraints.WEST;
        choixNbrPlaceField.addActionListener(this);
        add(choixNbrPlaceField, contrainte);
        
        labelNbrPlace = new JLabel(" place ");
        contrainte.gridx=4; contrainte.gridy = 3 ;//contrainte.anchor = GridBagConstraints.WEST;
        add(labelNbrPlace, contrainte);
        contrainte.anchor = GridBagConstraints.CENTER;
        choixNbrPlace = new JSlider(JSlider.HORIZONTAL, 1, festival.getNbrPlaces()[jour], 1);
        contrainte.gridx = 1; contrainte.gridy = 3; contrainte.gridwidth = 2; 
        contrainte.anchor = GridBagConstraints.EAST;
        choixNbrPlace.addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e)
            {
                if (choixNbrPlace.getValue()>1)
                    labelNbrPlace.setText(" places");
                else
                   labelNbrPlace.setText(" place ");
                choixNbrPlaceField.setText(Integer.toString(choixNbrPlace.getValue()));
                boutonReserver.setText("Réserver : "+festival.getPrix()*choixNbrPlace.getValue()+"€");
            }
        });
        add(choixNbrPlace, contrainte);
        
        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(this);
        contrainte.gridx = 1; contrainte.gridy = 4; contrainte.gridwidth=1;
        contrainte.anchor = GridBagConstraints.CENTER;
        add(boutonAnnuler,contrainte);
        
        contrainte.gridx = 2; contrainte.gridy=4; contrainte.gridwidth =1;
        contrainte.anchor = GridBagConstraints.EAST;
        boutonReserver.addActionListener(this);
        add(boutonReserver, contrainte);
    }

    /**
     * Répond à un évenement
     * @param e Evenement à gérer
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if (e.getSource() == this.choixDate)
        {
            int jour = 0;
            for (int i = 0; i<datesFestival.length; i++)
            {
                if (choixDate.getSelectedItem().equals(datesFestival[i].toNumeric()))
                    jour = i;
            }
            if (festival.getNbrPlaces()[jour]==0)
            {
                new JOptionPane().showMessageDialog(null, "Il n'y a plus de place disponibles pour ce jour", "Erreur",JOptionPane.ERROR_MESSAGE);
                int indice = 0;
                choixDate.setSelectedItem(datesFestival[0].toNumeric());
                while (indice<datesFestival.length && festival.getNbrPlaces()[indice]==0)
                {
                    choixDate.setSelectedItem(datesFestival[++indice].toNumeric());
                }
            }
            else
            {
                this.choixNbrPlace.setValue(1);
                this.choixNbrPlace.setMaximum(festival.getNbrPlaces()[jour]);
            }
        }
        else if(e.getSource() == this.choixNbrPlaceField)
        {
            choixNbrPlace.setValue(Integer.parseInt(choixNbrPlaceField.getText()));
            this.boutonReserver.setText("Réserver : "+festival.getPrix()*choixNbrPlace.getValue()+"€");
        }
        else if (e.getSource() == boutonAnnuler)
        {
            JDialog parent = (JDialog)getParent().getParent().getParent();
            parent.dispose();
        }
        else if (e.getSource() == boutonReserver)
        {
            if (nomClientField.getText().equals(""))
                new JOptionPane().showMessageDialog(null, "Vous devez entrer un nom de client", "Erreur",JOptionPane.ERROR_MESSAGE);
            else
            {
                Date date = new Date((String)choixDate.getSelectedItem());
                int jour = 0;
                for (int i = 0; i<datesFestival.length; i++)
                {
                    if (choixDate.getSelectedItem().equals(datesFestival[i].toNumeric()))
                        jour = i;
                }
                this.festival.setNbrPlaces(festival.getNbrPlaces()[jour]-this.choixNbrPlace.getValue(), jour);
                sauvegarde.MethodesPourFichier.ecriture(new File("programme.save"),programme.Programme.getListeFestival());
                //Fin de la fenetre
                JDialog parent = (JDialog)getParent().getParent().getParent();
                parent.dispose();
            }
        }
    }
}