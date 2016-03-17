package ihm;

import constantes.Constantes;
import java.util.Iterator;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import programme.Festival;

/**
 *  Classe héritant de DefaultTableModel afin de gérer la table de consultation des festivals
 * @author jgoodwin
 */
public class ModeleTable extends DefaultTableModel
{
    /**
     * Constructeur par défaut
     */
    public ModeleTable()
    {
        this.setColumnIdentifiers(constantes.Constantes.GENRE_ITEMS);
        this.setRowCount(programme.Programme.getMaxFestivalGenre());
        Set <String> key = programme.Programme.getListeFestival().keySet();
        Iterator iterateur = key.iterator();
        int[] nbrLigne = new int[Constantes.GENRE_ITEMS.length];
        for (int i = 0; i< nbrLigne.length; i++)
            nbrLigne[i] = 0;
        while (iterateur.hasNext())
        {
            String cle = (String)iterateur.next();
            Festival festival = (Festival)programme.Programme.getListeFestival().get(cle);
            int colonne = 0;
            int i = 0;
            while (i < Constantes.GENRE_ITEMS.length && festival.getGenre().compareTo(Constantes.GENRE_ITEMS[i])!=0)
            {
                i++;
            }
            if (festival.getGenre().compareTo(Constantes.GENRE_ITEMS[i])==0)
            {
                colonne=i;
            }
            setValueAt(festival.toStringHTML(), nbrLigne[colonne], colonne);
            nbrLigne[colonne]++;
        }
    }
    
    /**
     * Methode surchargé permettant de rendre une cellule non éditable
     * @param indiceLigne
     * @param indiceColonne
     * @return 
     */
    @Override
    public boolean isCellEditable(int indiceLigne, int indiceColonne)
    {
        return false;
    }
    
    /**
     * Methode permettant d'appeler le cellRenderer
     * @param indiceColonne indice de la colonne
     * @return classe de l'objet de la cellule
     */
    @Override
    public Class getColumnClass(int indiceColonne)
    {
        return String.class;
    }
}
