/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.framework.domain.Money;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Sofia
 */
public class BalanceLimitTest {

    private static Balance balance50;
    private static Money money60;
    private static Money money4;

    public BalanceLimitTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        balance50 = new Balance(Money.euros(50));
        money60 = Money.euros(60);
        money4 = Money.euros(4);

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
     * Test of isViolated method, of class BalanceLimit.
     */
    @Test
    public void testIsViolated() {
        System.out.println("isViolated");
        Balance b = balance50;
        Money average = money4;
        BalanceLimit instance = new BalanceLimit();
        boolean result = instance.isViolated(b, average);
        assertFalse(result);

        average = money60;
        result = instance.isViolated(b, average);
        System.out.println(money60.amount() + " " + result);
        assertTrue(result);
    }
}
