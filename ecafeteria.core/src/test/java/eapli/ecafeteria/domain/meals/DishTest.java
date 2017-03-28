/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AntónioRocha
 */
public class DishTest {
    DishType aDishType;
    NutricionalInfo aNutricionalInfo;
    
    public DishTest() {
    }
    
    @Before
    public void setUp() {
        aDishType = new DishType("Peixe", "Peixe");
        aNutricionalInfo = new NutricionalInfo(10,11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDishTypeMustNotBeNull() {
        System.out.println("must have an Dish type");
        Dish instance = new Dish(null, new Designation("Prego"), aNutricionalInfo,Money.euros(8));
     }
     
        @Test(expected = IllegalArgumentException.class)
    public void testNameMustNotBeNull() {
        System.out.println("must have an name");
        Dish instance = new Dish(aDishType, null, aNutricionalInfo,Money.euros(5));
     }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNutricionalInfoMustNotBeNull() {
        System.out.println("must have an Nutricional Info");
        Dish instance = new Dish(aDishType, new Designation("Tosta"), null,Money.euros(7));
     }
    
    /**
     * Test of is method, of class Dish.
     */
    @Test
    public void testIs() {
        System.out.println("Attest 'is' method - Normal Behaviour");
        Designation id = new Designation("Tosta");
        DishType dishtype = new DishType("Peixe", "Peixe");
        NutricionalInfo nutricional = new NutricionalInfo(10,11);
        Dish instance = new Dish(dishtype, id, nutricional, Money.euros(5));
        boolean expResult = true;
        boolean result = instance.is(id);
        assertEquals(expResult, result);

    }
  
}
