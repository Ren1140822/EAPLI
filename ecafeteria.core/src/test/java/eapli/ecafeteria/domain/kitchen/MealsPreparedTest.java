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
 * @author Diogo Santos
 */
public class MealsPreparedTest {

    public MealsPreparedTest() {
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
    public void ensureMealsPreparedIsInAValidState() {
        MealsPrepared prMeals = new MealsPrepared(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureQuantityIsNonNegative() {
        MealsPrepared prMeals = new MealsPrepared( -1);
    }

}
