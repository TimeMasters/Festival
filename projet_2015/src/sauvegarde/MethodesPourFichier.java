package sauvegarde;

/**
 * Classe possédant des fonctions permettant de sauvegarder des objets dans des fichiers
 * @author jgoodwin
 */
import java.io.*;

/**
 *
 * @author jgoodwin
 */
public class MethodesPourFichier 
{
    /**
     * Permet de lire/d'écrire une sauvegarde
     * @param parFichier emplacement du fichier à lire
     * @return Objet lu
     * @throws FileNotFoundException Si le fichier n'est pas trouvé
     * @throws IOException Si il y a une erreur lors de la lecture
     */
    public static Object lecture (File parFichier) throws FileNotFoundException, IOException
    {
        ObjectInputStream flux;
        Object objetLu = null ;
        //Ouverture du fichier en mode lecture
        try {
            flux = new ObjectInputStream (new FileInputStream (parFichier));
            objetLu = (Object)flux.readObject();
            flux.close();
        }//try
        catch (ClassNotFoundException parException)
        {
            System.err.println(parException.toString());
            System.exit(1);
        } // catch
        catch (IOException parException)
        {
            System.err.println("Erreur lecture du fichier" + parException.toString());
        } //catch
        return objetLu;
    }//lecture()
    
    /**
     * Permet d'écrire dans un fichier
     * @param parFichier emplacement du fichier à écrire
     * @param parObjet objet à sauvegarder
     */
    public static void ecriture (File parFichier, Object parObjet)
    {
        ObjectOutputStream flux = null;
        
        //Ouverture du fichier en mode écriture
        try {
            flux = new ObjectOutputStream (new FileOutputStream (parFichier));
            flux.writeObject(parObjet);
            flux.flush();
            flux.close();
        }   //try
        catch (IOException parException) 
        {
            System.err.println ("Problème a l'écriture\n" + parException.toString());
            System.exit(1);
        } //catch()
    }//ecriture
}
