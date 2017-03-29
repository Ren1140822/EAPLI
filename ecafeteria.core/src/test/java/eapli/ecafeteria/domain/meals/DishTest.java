/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eapli.ecafeteria.application.meals.ChangeDishTypeController;
import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author AntónioRocha
 */
public class DishTest {

    DishType peixe;
    NutricionalInfo aNutricionalInfo;

    public DishTest() {
    }

    @Before
    public void setUp() {
        peixe = new DishType("Peixe", "Peixe");
        aNutricionalInfo = new NutricionalInfo(10, 11);
    }

    @Test(expected = IllegalStateException.class)
    public void testDishTypeMustNotBeNull() {
        System.out.println("must have an Dish type");
        Dish instance = new Dish(null, new Designation("Prego"), aNutricionalInfo, Money.euros(8));
    }

    @Test(expected = IllegalStateException.class)
    public void testNameMustNotBeNull() {
        System.out.println("must have an name");
        Dish instance = new Dish(peixe, null, aNutricionalInfo, Money.euros(5));
    }

    @Test(expected = IllegalStateException.class)
    public void testNutricionalInfoMustNotBeNull() {
        System.out.println("must have an Nutricional Info");
        Dish instance = new Dish(peixe, new Designation("Tosta"), null, Money.euros(7));
    }

    /**
     * Test of is method, of class Dish.
     */
    @Test
    public void testIs() {
        System.out.println("Attest 'is' method - Normal Behaviour");
        Designation tosta = new Designation("Tosta");
        Dish instance = new Dish(peixe, tosta, aNutricionalInfo, Money.euros(5));
        boolean expResult = true;
        boolean result = instance.is(tosta);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of changeNutricionalInfoTo method, of class Dish.
     * 
     * PRP - 29.mar.2017
     */
    @Test(expected = IllegalArgumentException.class)
    public void testchangeNutricionalInfoToMustNotBeNull() {
        System.out.println("ChangeNutricionalInfoTo -New nutricional info must not be null");

        final Dish Dishinstance=new Dish(peixe, new Designation("Tosta"), new NutricionalInfo(1,1), Money.euros(7));
        Dishinstance.changeNutricionalInfoTo(null);
    }
   
    /**
     * Tests of changePriceTo method, of class Dish.
     * 
     * PRP - 29.mar.2017
     */
    @Test(expected = IllegalArgumentException.class)
    public void testchangePriceToMustNotBeNull() {
        System.out.println("ChangePriceTo -New price info must not be null");

        final Dish Dishinstance=new Dish(peixe, new Designation("Tosta"), new NutricionalInfo(1,1), Money.euros(7));
        Dishinstance.changePriceTo(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testchangePriceToCanNotBeNegative() {
        System.out.println("ChangePriceTo -New price can nt be negativel");
        
        final Dish Dishinstance=new Dish(peixe, new Designation("Tosta"), new NutricionalInfo(1,1), Money.euros(1));
        Dishinstance.changePriceTo(Money.euros(-1));
    }
}
