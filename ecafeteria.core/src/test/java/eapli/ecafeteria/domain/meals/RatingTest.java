/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
public class RatingTest {

    private static Integer rateNegative;
    private static Integer rateAbove5;
    private static Integer rate4;
    private static Integer rate3;

    public RatingTest() {
        rateNegative = -1;
        rateAbove5 = 10;
        rate4 = 4;
        rate3 = 3;
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
    public void ensureRatingIs1to5() {
        new Rating(rateAbove5);
        new Rating(rateNegative);
    }

    /**
     * Test of toString method, of class Rating.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Rating instance = new Rating(rate4);
        String expResult = "Rate: 4";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Rating.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Rating obj = new Rating(rate4);
        Rating instance = new Rating(rate4);
        boolean result = instance.equals(obj);
        assertTrue(result);
        
        obj = new Rating(rate3);
        result = instance.equals(obj);
        assertFalse(result);
    }
}
