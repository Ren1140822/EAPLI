/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        MealsPrepared prMeals = new MealsPrepared(new Meal(), 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureQuantityIsNonNegative() {
        MealsPrepared prMeals = new MealsPrepared(new Meal(), -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMealsPreparedHasMeal() {
        MealsPrepared prMeals = new MealsPrepared(null, 100);
    }
}
