/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.cashregister;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ComplaintIdTest {
    
    public ComplaintIdTest() {
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
     * Tests if a ComplaintId is equals to itself.
     */
    @Test
    public void ensureTheSameInstanceOfTransactionIdIsEqualsToItself() {
        ComplaintId aComplaintId = new ComplaintId();
        ComplaintId theSameComplaintId = aComplaintId;

        assertTrue(aComplaintId.equals(theSameComplaintId));
    }

    /**
     * Tests if different ComplaintIds are not equals.
     */
    @Test
    public void ensureDifferentTransactionsIdsAreNotEquals() {
        ComplaintId aComplaintId = new ComplaintId();
        ComplaintId anotherComplaintId = new ComplaintId();

        assertFalse(aComplaintId.equals(anotherComplaintId));
    }
    
}
