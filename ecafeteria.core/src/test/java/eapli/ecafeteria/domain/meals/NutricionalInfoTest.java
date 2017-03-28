/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eapli.ecafeteria.domain.meals.NutricionalInfo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AntónioRocha
 */
public class NutricionalInfoTest {
    
    public NutricionalInfoTest() {
    }
   
    /**
     * Test of calories method, of class NutricionalInfo.
     */
    
    @Test(expected = IllegalStateException.class)
    public void testCaloriesMustNotBeNull() {
        System.out.println("calories must not be null");
        NutricionalInfo instance = new NutricionalInfo(null,0);
    }

    @Test(expected = IllegalStateException.class)
    public void testCaloriesMustNotBeNegative() {
        System.out.println("calories must not be negative");
        NutricionalInfo instance = new NutricionalInfo(-1,0);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSaltMustNotBeNull() {
        System.out.println("Salt must not be null");
        NutricionalInfo instance = new NutricionalInfo(0,null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSaltMustNotBeNegative() {
        System.out.println("Salt must not be negative");
        NutricionalInfo instance = new NutricionalInfo(0,-6);
    }
     
    
    @Test
    public void testNormalBehaviour() {
        System.out.println("normal ehaviour");
        NutricionalInfo instance = new NutricionalInfo(3,5);
        Integer expResult = 3;
        assertEquals(expResult, instance.calories());
    }
}
