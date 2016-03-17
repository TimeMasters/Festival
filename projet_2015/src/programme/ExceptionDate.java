package programme;

/**
 * Classe gérant les exceptions de la classe Agenda
 * @author jgoodwin
 */
public class ExceptionDate extends Exception
{
    /**
     * Constructeur par défaut
     * @param parMessage message à renvoyer au constructeur de la classe Exception
     */
    public ExceptionDate (String parMessage)
    {
        super (parMessage);
    } 
}