package programme;

import programme.ExceptionDate;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Abstraction d'une date
 * @author jgoodwin
 */
public class Date implements Serializable
{
    private int chJour;	//attribut jour
    private int chMois;	//attribut mois
    private int chAnnee;	//attribut annee
    private int chJourSemaine;  //Attribut jour de la semaine
    
    /**
     * Renvoie une chaine de caractère de la date
     * @return chaine de caractère de la date
     */
    public String toString()
    {
        String retour;
        String jourSemaine[] = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
        String nomMois[]={"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre"};
        return chJour + " " + nomMois[chMois-1];
    }
    
    /**
     * Renvoie la date sous format jj/mm
     * @return date sous format jj/mm
     */
    public String toNumeric()
    {
        if (chJour<10)
            return "0"+chJour+"/"+chMois+"/"+chAnnee;
        return chJour+"/"+chMois+"/"+chAnnee;
    }
    
    /**
     * Constructeur quui créé une date a partir d'une chaine de caractères de format jj/mm/aaaa
     * @param toNumeric date sous format jj/mm/aaaa
     */
    public Date(String toNumeric)
    {
        StringTokenizer aSeparer = new StringTokenizer(toNumeric , "/");
        this.chJour = Integer.parseInt(aSeparer.nextToken());
        chMois = Integer.parseInt(aSeparer.nextToken());
        chAnnee = Integer.parseInt(aSeparer.nextToken());
    }

    /**
     * Constructeur par défaut de la date d'aujourd'hui
     */
    public Date()
    {
        GregorianCalendar dateAuj = new GregorianCalendar ();
        chAnnee = dateAuj.get (Calendar.YEAR);
        chMois = dateAuj.get (Calendar.MONTH)+1;
        chJour = dateAuj.get (Calendar.DAY_OF_MONTH);
        chJourSemaine = dateAuj.get (Calendar.DAY_OF_WEEK);
    }	//Constructeur avec paramètres
    
    /**
     * Constructeur à partir d'une date
     * @param date 
     */
    public Date(Date date)
    {
        chAnnee = date.getAnnee();
        chMois = date.getMois();
        chJour = date.getJour();
        chJourSemaine = date.getJourSemaine();
    }

    /**
     * Constructeur d'une date spécifiée par l'utilisateur
     * @param parJour jour à donner a la date
     * @param parMois mois à donner a la date
     * @param parAnnee annee à donner a la date
     * @throws ExceptionDate 
     */
    public Date(int parJour, int parMois, int parAnnee) throws ExceptionDate
    {
        if (parJour > this.dernierJourDuMois(parMois, parAnnee) || parJour < 1)
            throw new ExceptionDate("Jour non valide");
        else if (parMois < 1 || parMois > 12)
            throw new ExceptionDate("Mois non valide");
        else if (parAnnee < 1583)
            throw new ExceptionDate("Annee non valide");
        else
        {

            chJour = parJour;
            chMois = parMois;
            chAnnee = parAnnee;
            GregorianCalendar calendar = new GregorianCalendar (chAnnee,chMois-1,chJour);
            chJourSemaine = calendar.get(Calendar.DAY_OF_WEEK);
        }
    }	//Constructeur avec param�tres
    
    /**
     * Vérfie si la date est bissextile
     * @return booléen certifiant la bissextalité de la date
     */
    private boolean estBissextile()
    {
            return (chAnnee%4 == 0 && (chAnnee%100 != 0) || (chAnnee%400==0));
    }
    
    /**
     * Vérifie si l'annee est bissextile
     * @param parAnnee annee a verifier
     * @return booléen certifiant la bissextalité de l'annee
     */
    public static boolean anneeEstBissextile(int parAnnee)
    {
        return (parAnnee%4 == 0 && (parAnnee%100 != 0) || (parAnnee%400==0));
    }

    /**
     * Vérifie si la date est valide
     * @return booléen vérifiant la validité de la date
     */
    public boolean estValide()
    {
            if ((chAnnee>=1582) && (chMois <= 12) && (chMois >=1))
            {
                    int longueur_mois[] = {31,29,31,30,31,30,31,31,30,31,30,31};
                    return ((chMois==2) && (chJour == 29) && (this.estBissextile())) || (chJour <= longueur_mois[chMois-1]);
            }
            return false;
    } // methode estValide

    /**
     * vérifie si une date est antérieur à une autre
     * @param parDate seconde date à vérifier
     * @return booléen certifiant l'antériorité d'une date (0 : égales / -1 : this plus ancienne / 1 : parDate plus ancienne)
     */
    public int precede (Date parDate)
    {
            if (chAnnee < parDate.chAnnee)
                    return 1;
            if (chAnnee > parDate.chAnnee)
                    return -1;
            if (chMois < parDate.chMois)
                    return 1;
            if (chMois > parDate.chMois)
                    return -1;
            if (chJour < parDate.chJour)
                    return 1;
            if (chJour > parDate.chJour)
                    return -1;
            return 0;
    }
     
    /**
     * renvoie le nombre de jour que le mois comporte
     * @param parMois mois à vérifier
     * @param parAnnee année à vérfier
     * @return nombre de jour que le mois comporte
     */
    public static int dernierJourDuMois (int parMois, int parAnnee)
    {
        int longueur_mois[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (anneeEstBissextile(parAnnee) == true && parMois == 2)
        {
            return longueur_mois[parMois-1]+1;
        }
        else
        {
            return longueur_mois[parMois-1];
        }
    }
    
    /**
     * accesseur de l'année de la date
     * @return année de la date
     */
    public int getAnnee()
    {
        return this.chAnnee;
    }
    
    /**
     * Accesseur du mois de la date
     * @return mois de la date
     */
    public int getMois()
    {
        return this.chMois;
    }
    
    /**
     * Accesseur du jour de la date
     * @return jour de la date
     */
    public int getJour()
    {
        return this.chJour;
    }
    
    /**
     * Accesseur de jour de la semaine de la date
     * @return  jour de la semaine de la date
     */
    public int getJourSemaine()
    {
        return this.chJourSemaine;
    }
    
    /**
     * Setteur de l'année de la date
     * @param parAnnee année après mise a jour
     */
    public void setAnnee(int parAnnee)
    {
        this.chAnnee=parAnnee;
    }
    
    /**
     * setteur du mois de la date
     * @param parMois mois après annee
     * @throws ExceptionDate erreur si mois non valide
     */
    public void setMois(int parMois) throws ExceptionDate
    {
        if (parMois<1 || parMois >12)
            throw new ExceptionDate("Impossible");
        else
            chMois=parMois;
    }
    
    /**
     * setteur du jour de la date
     * @param parJour jour après mise a jour
     * @throws ExceptionDate erreur si jour non valide
     */
    public void setJour(int parJour) throws ExceptionDate
    {
        if (parJour > dernierJourDuMois(this.chMois,this.chAnnee) || parJour < 1)
            throw new ExceptionDate ("Impossible");
        else
            chJour=parJour;
    }
    /**
     * setteur du jour de la semaine de la date
     * @param parJourSemaine jour de la semaine après mise a jour
     * @throws ExceptionDate erreur si jour de la semaine non valide
     */
    public void setJourSemaine(int parJourSemaine) throws ExceptionDate
    {
        if (parJourSemaine<1 || parJourSemaine>7)
            throw new ExceptionDate ("Impossible");
        else
            chJourSemaine=parJourSemaine;
    }
    
    /**
     * Renvoie le jour de la semaine en toutes lettres correspondant
     * @return jour de la semaine en toutes lettres
     */
    public String jourSemaineString()
    {
        String jourSemaine[] = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
        return jourSemaine[this.getJourSemaine()-1];
    }
    
    /**
     * Renvoie le nombre de jour entre deux dates
     * @param date date à comparer
     * @return nombre de jours entre deux dates
     */
    public int ecartDate(Date date)
    {
        Date precedente;
        Date suivante;
        int retour = 0;
        
        if (this.precede(date) == 0)
            return 0;
        else if (this.precede(date)==-1)
        {
            precedente =new Date(this);
            suivante = new Date(date);
        }
        else
        {
            precedente = new Date(date);
            suivante = new Date(this);
        }
        while (precedente.precede(suivante)!=0)
        {
            precedente = precedente.dateHier();
            retour++;
        }
        return retour;
    }
    
    /**
     * Renvoie la date d'hier
     * @return date d'hier
     */
    public Date dateHier()
    {
        Date date = new Date(this);
        if(date.chJour==1 && date.chMois==1)
        {
            date.chAnnee--;
            date.chMois = 12;
            date.chJour = dernierJourDuMois(chMois, chAnnee);
            if (chJourSemaine == 1)
                date.chJourSemaine=7;
            else
                date.chJourSemaine--;
        }
        else if(date.chJour==1 && date.chMois != 1)
        {
            date.chMois--;
            date.chJour = dernierJourDuMois(chMois, chAnnee);
            if (chJourSemaine == 1)
                date.chJourSemaine=7;
            else
                date.chJourSemaine--;
        }
        else
        {
            date.chJour--;
            if (chJourSemaine == 1)
                date.chJourSemaine=7;
            else
                date.chJourSemaine--;
        }
        return date;
        
    }
    
    /**
     * Renvoie la date de demain
     * @return date de demain
     */
    public Date dateDemain()
    {
        Date date= new Date(this);
        if(date.chJour==dernierJourDuMois(chMois, chAnnee) && date.chMois==12)
        {
            date.chAnnee++;
            date.chMois = 1;
            date.chJour = 1;
            if (chJourSemaine == 7)
                date.chJourSemaine=1;
            else
                date.chJourSemaine++;
        }
        else if(date.chJour==dernierJourDuMois(chMois, chAnnee) && date.chMois != 12)
        {
            date.chMois++;
            date.chJour = 1;
            if (chJourSemaine == 7)
                date.chJourSemaine=1;
            else
                date.chJourSemaine++;
        }
        else
        {
            date.chJour++;
            if (chJourSemaine == 7)
                date.chJourSemaine=1;
            else
                date.chJourSemaine++;
        }
        return date;
    }
}