/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mcn
 */
public class DishTypeTest {
    
    public DishTypeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

        
    @Test(expected = IllegalArgumentException.class)
    public void testAcronymMustNotBeEmpty() {
            System.out.println("must have non-empty acronym");
            DishType instance = new DishType("", "vegetarian dish");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAcronymMustNotBeNull() {
            System.out.println("must have an acronym");
            DishType instance = new DishType(null, "vegetarian dish");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDescriptionMustNotBeEmpty() {
            System.out.println("must have non-empty description");
            DishType instance = new DishType("veg1", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescriptionMustNotBeNull() {
            System.out.println("must have a description");
            DishType instance = new DishType("veg1", null);
    }
    /**
     * Test of isActive method, of class DishType.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive - normal behaviour");
        
        System.out.println("Attest 'is' method - Normal Behaviour");
        String acronym = "vege001";
        DishType instance = new DishType(acronym, "vegetarian dish");
        boolean expResult = true;
        boolean result = instance.is(acronym);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeDishTypeState method, of class DishType.
     */
    @Test
    public void testChangeDishTypeState() {
        System.out.println("changeDishTypeState");
        DishType instance = new DishType("vege005", "vegetarian dish");
        instance.changeDishTypeState();
        boolean expResult = false;
        boolean result = instance.isActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeDescriptionTo method, of class DishType.
     */
    
    @Test(expected = IllegalArgumentException.class)
    public void testchangeDescriptionToMustNotBeNull() {
        System.out.println("ChangeDescriptionTo -New description must not be null");
        DishType instance = new DishType("vege005", "vegetarian dish");
        instance.changeDescriptionTo(null); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testchangeDescriptionToMustNotBeEmpty() {
        System.out.println("ChangeDescriptionTo -New description must not be empty");
        DishType instance = new DishType("vege005", "vegetarian dish");
        instance.changeDescriptionTo(""); 
    }
    
    
    
    @Test
    public void testchangeDescriptionTo() {
        System.out.println("attest changeDescriptionTo");
        DishType instance = new DishType("vege005", "vegetarian dish");
        String newDescription="new description";
        instance.changeDescriptionTo(newDescription);
        String expResult = newDescription;
        String result = instance.description();
        assertEquals(expResult, result);
    }

    /**
     * Test of id method, of class DishType.
     */
    @Test
    public void testId() {
        System.out.println("id");
        String acronym="veg";
        DishType instance = new DishType(acronym, "vegetarian dish");
        boolean expResult = true;
        boolean result = acronym.equals(instance.id());
        assertEquals(expResult, result);

    }

    /**
     * Test of is method, of class DishType.
     */
    @Test
    public void testIs() {
        System.out.println("Attest is method");
        String id = "veg";
        String description="vegetarian dish";
        DishType instance = new DishType(id,description);
        boolean expResult = true;
        boolean result = instance.is(id);
        assertEquals(expResult, result);
    }
    
}
