/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Pedro Fernandes
 */
public class AllotmentTest {
    
    public AllotmentTest() {
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

    @Test
    public void testAllotmentOk() {
        Allotment a = new Allotment("A34");
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureCodeIsNotEmpty() {
        Allotment a = new Allotment("");
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureCodeHasNotWhiteSpaces() {
        Allotment a = new Allotment(" ");
    }
}
