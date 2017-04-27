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
public class MaterialUsedTest {
    
    public MaterialUsedTest() {
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

    @Test(expected = IllegalStateException.class)
    public void ensureMaterialIsNotNull() {
        MaterialUsed m = new MaterialUsed(null, new Allotment("abc23"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureAllotmentIsNotNull() {
        MaterialUsed m = new MaterialUsed(new Material("abc", "description"), null);
    }
    
    @Test
    public void testMaterialUsedOk() {
        MaterialUsed m = new MaterialUsed(new Material("abc", "description"), new Allotment("abc23"));
    }
}
