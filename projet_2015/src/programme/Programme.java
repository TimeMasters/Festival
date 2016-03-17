package programme;

import static constantes.Constantes.GENRE_ITEMS;
import ihm.FenetreProgramme;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import sauvegarde.MethodesPourFichier;

/**
 * Classe représentant le programme de festival d'une année et stockant donc tout les festivals
 * @author jgoodwin
 */
public class Programme implements Serializable
{
    private static HashMap <String,Festival> listeFestival;
    
    /**
     * Constructeur par défaut
     */
    public Programme()
    {
        listeFestival = new HashMap<>();
        try {
            this.listeFestival.put("HellFest", new Festival("HellFest", GENRE_ITEMS[1], new Date(19, 06, 2015), new Date(21, 06, 2015), "Clisson", 70, new ImageIcon("affiches/hellfest.png"),500));
            this.listeFestival.put("Solidays", new Festival("Solidays", GENRE_ITEMS[5], new Date(26, 06, 2015), new Date(28, 06, 2015), "Paris", 60, new ImageIcon("affiches/solidays.png"),400));
            this.listeFestival.put("Musilac", new Festival("Musilac", GENRE_ITEMS[3], new Date(10, 07, 2015), new Date(13, 07, 2015), "Aix-Les-Bains", 50, new ImageIcon("affiches/musilac.png"),600));
            this.listeFestival.put("Garorock", new Festival("Garorock", GENRE_ITEMS[0], new Date(26, 06, 2015), new Date(28, 06, 2015), "Marmande", 80, new ImageIcon("affiches/garorock.png"),250));
            this.listeFestival.put("Estivales de Musique en Medoc", new Festival("Estivales de Musique en Medoc", GENRE_ITEMS[4], new Date(1, 07, 2015), new Date(10, 07, 2015), "Pauillac", 10,new ImageIcon("affiches/estivales_de_musique_en_medoc.png"),800));
            this.listeFestival.put("Fesival des Vieilles Charues", new Festival("Festival des Vieilles Charrues", GENRE_ITEMS[2], new Date(16, 07, 2015), new Date(19, 07, 2015), "Carhaix Plouguer", 50, new ImageIcon("affiches/vieilles_charues.png"), 100));
        }catch(ExceptionDate | ExceptionFestival e){
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Constructeur à partir d'un fichier de sauvegarde
     * @param nomFichier emplacement du fichier de sauvegarde
     */
    public Programme(String nomFichier)
    {
        File sauvegarde = new File(nomFichier);
        listeFestival = new HashMap<>();
        try{
            listeFestival = (HashMap<String, Festival>)MethodesPourFichier.lecture(sauvegarde);
        }catch(IOException exception){
            System.err.println(exception.getMessage());
            exit(300);
        }
    }
    
    /**
     * sauvegarde le programme dans un fichier
     * @param nomFichierSauvegarde fichier à charger pour acquérir l'ancienne sauvegarde
     */
    public void sauvegardeProgramme(String nomFichierSauvegarde)
    {
        File sauvegarde = new File(nomFichierSauvegarde);
        MethodesPourFichier.ecriture(sauvegarde, this);
    } 
    
    /***
     * Renvoie la liste des festivals du programme
     * @return liste des festivals du programme
     */
    public static HashMap getListeFestival()
    {
        return listeFestival;
    }
    
    /**
     * Renvoie le nombre maximum de festival par genre
     * @return nombre maximum de festival par genre
     */
    public static int getMaxFestivalGenre()
    {
        int nbrFestivalGenre[] = new int[constantes.Constantes.GENRE_ITEMS.length];
        Set <String> key = listeFestival.keySet();
        for (int i = 0; i<nbrFestivalGenre.length; i++)
            nbrFestivalGenre[i] = 0;
        for (String cle : key)
        {
            for (int i = 0; i<constantes.Constantes.GENRE_ITEMS.length; i++)
                if (listeFestival.get(cle).getGenre().equals(constantes.Constantes.GENRE_ITEMS[i]))
                    nbrFestivalGenre[i]+=1;
        }
        int max = 0;
        for (int i = 0; i<nbrFestivalGenre.length; i++)
        {
            if (nbrFestivalGenre[i]>max)
                max = nbrFestivalGenre[i];
        }
        return max;
    }
}
