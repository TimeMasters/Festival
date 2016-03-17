package programme;

/**
 * Classe gérant les exceptions de la classe Fesival
 * @author jpgoodwin
 */
public class ExceptionFestival extends Exception
{
    /**
     * Constructeur par défaut
     * @param parMessage message à renvoyer au constructeur de la classe Exception
     */
    public ExceptionFestival(String parMessage)
    {
        super(parMessage);
    }
}
