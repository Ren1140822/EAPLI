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
public class CommentTest {

    private static String comment;
    private static String commentCorrect;
    private static String commentCorrect2;

    public CommentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        comment = "44444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444";
        commentCorrect = "Good";
        commentCorrect2 = "Nice";
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
    public void ensureCommentLenghtIsLessThan240Characters() {
        new Comment(comment);
    }

    /**
     * Test of toString method, of class Comment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Comment instance = new Comment(commentCorrect);
        String expResult = "Comment: Good";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Comment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Comment obj = new Comment(commentCorrect);
        Comment instance = new Comment(commentCorrect);
        boolean result = instance.equals(obj);
        assertTrue(result);
        
        obj = new Comment(commentCorrect2);
        result = instance.equals(obj);
        assertFalse(result);
    }

}
