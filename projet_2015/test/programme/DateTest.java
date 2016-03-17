/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programme;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Classe de test de la classe Date
 * @author jpgoodwin
 */
public class DateTest 
{
    Exception exception;
    
    /**
     * Test de la création de date numéro 1
     * @throws programme.ExceptionDate
     */
    @Test(expected = ExceptionDate.class)
    public void testCreateDate1() throws ExceptionDate
    {
        Date date = new Date(0,2,2015);
    }
    
    /**
     * Test de la création de date numéro 2
     * @throws ExceptionDate 
     */
    @Test(expected = ExceptionDate.class)
    public void testCreateDate2() throws ExceptionDate
    {
        Date date = new Date(32,5,2015);
    }
    
    /**
     * Test de la création de date numéro 3
     */
    @Test
    public void testCreateDate3()
    {
        try {
            Date date = new Date(15,8,2015);
        } catch (ExceptionDate ex1) {
            exception = ex1;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de date numéro 4
     */
    @Test
    public void testCreateDate4()
    {
        try {
            Date date = new Date(15,9,2015);
        } catch (ExceptionDate ex1) {
            exception = ex1;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de date numéro 5
     */
    @Test
    public void testCreateDate5()
    {
        try {
            Date date = new Date(28,2,2015);
        } catch (ExceptionDate ex1) {
            exception = ex1;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de date numéro 6
     */
    @Test
    public void testCreateDate6()
    {
        try {
            Date date = new Date(29,2,2012);
        } catch (ExceptionDate ex1) {
            exception = ex1;
        }
        assertEquals(null, exception);
        exception = null;
    }
    
    /**
     * Test de la création de date numéro 7
     * @throws ExceptionDate 
     */
    @Test(expected = ExceptionDate.class)
    public void testCreateDate7() throws ExceptionDate
    {
        Date date = new Date(33,3,2015);
    }
    
    /**
     * Test de la création de date numéro 8
     * @throws ExceptionDate 
     */
    @Test(expected = ExceptionDate.class)
    public void testCreateDate8() throws ExceptionDate
    {
        Date date = new Date(31,4,2015);
    }
    
    /**
     * Test de la création de date numéro 9
     * @throws ExceptionDate 
     */
    @Test(expected = ExceptionDate.class)
    public void testCreateDate9() throws ExceptionDate
    {
        Date date = new Date(29,2,2015);
    }
    
    /**
     * Test de la création de date numéro 10
     * @throws ExceptionDate 
     */
    @Test(expected = ExceptionDate.class)
    public void testCreateDate10() throws ExceptionDate
    {
        Date date = new Date(30,2,2015);
    }
}
