/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import org.junit.After;
import org.junit.AfterClass;
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

    public CommentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        comment = "44444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444"
                + "444444444444444444444444444444444444444444444444444";
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

}
