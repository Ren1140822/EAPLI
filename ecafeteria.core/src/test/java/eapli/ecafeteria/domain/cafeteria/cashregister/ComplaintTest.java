/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import java.util.Currency;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ComplaintTest {
    
    
    public ComplaintTest() {
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
    public void ensureComplaintCanNotBeEmpty() {
         Complaint c = new Complaint("");
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureComplaintCanNotContainSpacesOnly() {
         Complaint c = new Complaint("  ");
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureIfUserIsInsertedThisCanNotBeNull() {
         Complaint c = new Complaint("I did not like the meal!", (MecanographicNumber) null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensurteIfDishIsInsertedThisCanNotBeNull() {
         Complaint c = new Complaint("I did not like the meal!", (Dish) null);
    }
    
    
    

    
}
