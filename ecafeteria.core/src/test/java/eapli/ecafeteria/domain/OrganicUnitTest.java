/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain;

import eapli.util.DateTime;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arocha
 */
public class OrganicUnitTest {
    
    public OrganicUnitTest() {
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

    /**
     * Test of id method, of class OrganicUnit.
     */
//    @Test
//    public void testId() {
//        System.out.println("id");
//        OrganicUnit instance = new OrganicUnit();
//        String expResult = "";
//        String result = instance.id();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testAcronymMustNotBeEmpty() {
            System.out.println("must have non-empty acronym");
            OrganicUnit instance = new OrganicUnit("", "Instituto", "Porto");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAcronymMustNotBeNull() {
            System.out.println("must have an acronym");
            OrganicUnit instance = new OrganicUnit(null, "Instituto", "Porto");
    }

    /**
     * Test of is method, of class OrganicUnit.
     */
    @Test
    public void testIs() {
        System.out.println("Attest 'is' method - Normal Behaviour");
        String id = "ISEP";
        OrganicUnit instance = new OrganicUnit(id, "Instituto", "Porto");
        boolean expResult = true;
        boolean result = instance.is(id);
        assertEquals(expResult, result);
    }
    
}
