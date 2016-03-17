package programme;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Classe représantant un festival et stockant les données lui étant liées
 * @author jgoodwin
 */
public class Festival implements Serializable
{
    private String nomFestival;
    private String genreFestival;
    private Date dateDebutFestival;
    private Date dateFinFestival;
    private String lieuFestival;
    private int prixFestival;
    private ImageIcon imageFestival;
    private int [] nbrPlacesParJour;
    
    /**
     * Constructeur par défaut
     * @param parNom nom du festival
     * @param parGenre genre du festival
     * @param parDateDebut date de début du festival
     * @param parDateFin date de fin du festival
     * @param parLieu lieu du festival
     * @param parPrix prix du festival
     * @param parImage affiche du festival
     * @param parNbrPlaces nombre de place du festival
     * @throws programme.ExceptionFestival
     */
    public Festival(String parNom, String parGenre, Date parDateDebut, Date parDateFin, String parLieu, int parPrix, ImageIcon parImage, int parNbrPlaces) throws ExceptionFestival
    {
        if (parDateDebut.precede(parDateFin)==-1)
            throw new ExceptionFestival("Dates de fin antérieur à la date de début");
        else if (parNbrPlaces<0)
            throw new ExceptionFestival("Nombre de places négatif");
        else if (parPrix<0)
            throw new ExceptionFestival("Prix négatif");
        else
        {
            nomFestival = parNom;
            genreFestival = parGenre;
            dateDebutFestival = parDateDebut;
            dateFinFestival = parDateFin;
            lieuFestival = parLieu;
            prixFestival = parPrix;
            imageFestival = parImage;
            nbrPlacesParJour = new int[dateDebutFestival.ecartDate(dateFinFestival)+1];
            for (int i = 0; i<nbrPlacesParJour.length; i++)
                nbrPlacesParJour[i] = parNbrPlaces/nbrPlacesParJour.length;
            int nbrPlacesTotal = 0;
            for (int i = 0; i<nbrPlacesParJour.length;i++)
                nbrPlacesTotal+=nbrPlacesParJour[i];
            if(nbrPlacesTotal != parNbrPlaces)
            {
                for (int i = 0; i<parNbrPlaces-nbrPlacesTotal;i++)
                {
                    nbrPlacesParJour[i]++;
                }
            }
        }
    }

    /**
     * Ajoute le festival à un programme passé en paramètre
     * @param programme programme auquel ajouter le festival
     */
    public void ajoutFestival(Programme programme)
    {
        programme.getListeFestival().put(genreFestival, this);
    }
    
    /**
     * Renvoie une chaine de caractères résumant les attributs de la classe Festival
     * @return chaine de caractères résumant les attributs de la classe Date
     */
    @Override
    public String toString()
    {
        int nbrPlace = 0;
        for (int i = 0; i<nbrPlacesParJour.length ; i++)
            nbrPlace+=nbrPlacesParJour[i];
        return nomFestival + " "+ dateDebutFestival.toString() + " au " + dateFinFestival.toString() + " à " + lieuFestival + " " + prixFestival + "€ " + nbrPlace + " places diponibles";
    }
    
    /**
     * Renvoie une chaine de caractères résumant les attributs de la classe Festival sous forme HTML
     * @return chaine de caractères résumant les attributs de la classe Date sous forme HTML
     */
    public String toStringHTML()
    {
        int nbrPlace = 0;
        for (int i = 0; i<nbrPlacesParJour.length ; i++)
            nbrPlace+=nbrPlacesParJour[i];
        if (dateDebutFestival.getAnnee() == dateFinFestival.getAnnee())
            return "<html>"+nomFestival + "<br>"+ dateDebutFestival.toString() + " au " + dateFinFestival.toString() +" "+ dateFinFestival.getAnnee()+ "<br>à " + lieuFestival + "<br>" + prixFestival + "€<br>" + nbrPlace + " places diponibles</html>";
        else
            return "<html>"+nomFestival + "<br>"+ dateDebutFestival.toString() + " "+ dateFinFestival.getAnnee() + " au " + dateFinFestival.toString() +" "+ dateFinFestival.getAnnee()+ "<br>à " + lieuFestival + "<br>" + prixFestival + "€<br>" + nbrPlace + " places diponibles</html>";
    }
    
    /**
     * renvoie le nom du festival
     * @return nom du festival
     */
    public String getNom()
    {
        return this.nomFestival;
    }
    
    /**
     * Renvoie l'affiche du festival
     * @return affichage du festival
     */
    public ImageIcon getAffiche()
    {
        return this.imageFestival;
    }
    
    /**
     * Renvoie le genre du festival
     * @return genre du festival
     */
    public String getGenre()
    {
        return this.genreFestival;
    }
    
    /**
     * Renvoie la date du debut du Festival
     * @return date du début du festival
     */
    public Date getDateDebut()
    {
        return dateDebutFestival;
    }

    /**
     * Renvoie la date de fin du festival
     * @return date de fin du festival
     */
    public Date getDateFin()
    {
        return dateFinFestival;
    }
    
    /**
     * renvoie le lieu du festival
     * @return lieu du festival
     */
    public String getLieu()
    {
        return lieuFestival;
    }
    
    /**
     * Renvoie le prix du festival
     * @return prix du festival
     */
    public int getPrix()
    {
        return prixFestival;
    }
    
    /**
     * Renvoie le nombre de places disponibles du festival
     * @return nombre de place disponible du festival
     */
    public int[] getNbrPlaces()
    {
        return nbrPlacesParJour;
    }
    
    /**
     * Change la date du début du festival
     * @param date date du début du festival après mise a jour
     */
    public void setDateDebut(Date date)
    {
        this.dateDebutFestival = date;
    }
    
    /**
     * Change le nombre de place du jour indice
     * @param nbr nombre de place après mise a jour
     * @param indice indice du jour auquel le nombre de place sera changé
     */
    public void setNbrPlaces(int nbr, int indice)
    {
        this.nbrPlacesParJour[indice]=nbr;
    }
}