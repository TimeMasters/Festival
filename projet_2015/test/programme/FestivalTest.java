/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programme;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Classe de test de la classe Festival
 * @author jpgoodwin
 */
public class FestivalTest 
{
    Exception exception;
    
    /**
     * Test de la création de festival numéro 1
     * @throws ExceptionDate 
     */
    @Test
    public void createFestival1() throws ExceptionDate
    {
        try {
            new Festival("HellFest", "Metal", new Date(26,06,2015), new Date(27,07,2016), "Paris", 90, null, 90);
        } catch (ExceptionFestival ex) {
            exception = ex;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de festival numéro 2
     * @throws ExceptionDate
     * @throws ExceptionFestival 
     */
    @Test(expected = ExceptionFestival.class)
    public void createFestival2() throws ExceptionDate, ExceptionFestival
    {
        new Festival("HellFest", "Metal", new Date(27,07,2016), new Date(26,06,2015), "Paris", 90, null, 90);
    }
    
    /**
     * Test de la création de festival numéro 3
     * @throws ExceptionDate 
     */
    @Test
    public void createFestival3() throws ExceptionDate
    {
        try {
            new Festival("HellFest", "Metal", new Date(10,06,2015), new Date(20,07,2016), "Paris", 90, null, 90);
        } catch (ExceptionFestival ex) {
            exception = ex;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de festival numéro 4
     * @throws ExceptionDate 
     */
    @Test
    public void createFestival4() throws ExceptionDate
    {
        try {
            new Festival("HellFest", "Metal", new Date(20,06,2015), new Date(10,07,2016), "Paris", 90, null, 90);
        } catch (ExceptionFestival ex) {
            exception = ex;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de festival numéro 5
     * @throws ExceptionDate 
     */
    @Test
    public void createFestival5() throws ExceptionDate
    {
        try {
            new Festival("HellFest", "Metal", new Date(20,07,2015), new Date(10,06,2016), "Paris", 90, null, 90);
        } catch (ExceptionFestival ex) {
            exception = ex;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de festival numéro 6
     * @throws ExceptionDate
     * @throws ExceptionFestival 
     */
    @Test(expected = ExceptionFestival.class)
    public void createFestival6() throws ExceptionDate, ExceptionFestival
    {
        new Festival("HellFest", "Metal", new Date(20,07,2016), new Date(10,06,2016), "Paris", 90, null, 90);
    }
    
    /**
     * Test de la création de festival numéro 7
     * @throws ExceptionDate
     * @throws ExceptionFestival 
     */
    @Test(expected = ExceptionFestival.class)
    public void createFestival7() throws ExceptionDate, ExceptionFestival
    {
        new Festival("HellFest", "Metal", new Date(10,06,2016), new Date(20,07,2015), "Paris", 90, null, 90);
    }
    
    /**
     * Test de la création de festival numéro 8
     * @throws ExceptionDate
     * @throws ExceptionFestival 
     */
    @Test(expected = ExceptionFestival.class)
    public void createFestival8() throws ExceptionDate, ExceptionFestival
    {
        new Festival("HellFest", "Metal", new Date(10,07,2016), new Date(20,06,2015), "Paris", 90, null, 90);
    }
    
    /**
     * Test de la création de festival numéro 9
     * @throws ExceptionDate 
     */
    @Test
    public void createFestival9() throws ExceptionDate
    {
        try {
            new Festival("HellFest", "Metal", new Date(25,06,2015), new Date(25,06,2015), "Paris", 90, null, 90);
        } catch (ExceptionFestival ex) {
            exception = ex;
        }
        assertEquals(null, exception);
        exception = null;
    }
}