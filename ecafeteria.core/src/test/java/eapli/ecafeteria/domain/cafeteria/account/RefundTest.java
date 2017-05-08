/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Money;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Meireles
 */
public class RefundTest {
    
    private MecanographicNumber number;
    private double quantity;
    private Money amount;
    private Calendar mealDate;
    private Calendar cancellationDate;
    private MealType lunch;
    private MealType dinner;
    
    public RefundTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        number = new MecanographicNumber("900330");
        quantity = 20;
        amount = Money.euros(quantity);
        mealDate = Calendar.getInstance();
        cancellationDate = Calendar.getInstance();
        mealDate.set(Calendar.YEAR, 2017);
        cancellationDate.set(Calendar.YEAR, 2017);
        mealDate.set(Calendar.MONTH, 5);
        cancellationDate.set(Calendar.MONTH, 5);
        mealDate.set(Calendar.DAY_OF_MONTH, 5);
        cancellationDate.set(Calendar.DAY_OF_MONTH, 5);
        lunch = new MealType(MealType.MealTypes.ALMOCO);
        dinner = new MealType(MealType.MealTypes.JANTAR);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ensureNoPenaltyIsAppliedBeforeCancellationLimitOnLunch(){
        RefundBuilder refund = new RefundBuilder();
        refund.withMecanographicNumber(number);
        refund.withMoney(amount);
        cancellationDate.set(Calendar.AM_PM, Calendar.AM);
        cancellationDate.set(Calendar.HOUR, 9);
        refund.withPenalty(mealDate, lunch, cancellationDate);
        assertTrue(amount.equals(refund.build().value()));
    }

    @Test
    public void ensurePenaltyIsAppliedAfterCancellationLimitOnLunch(){
        RefundBuilder refund = new RefundBuilder();
        refund.withMecanographicNumber(number);
        refund.withMoney(amount);
        cancellationDate.set(Calendar.AM_PM, Calendar.AM);
        cancellationDate.set(Calendar.HOUR, 11);
        refund.withPenalty(mealDate, lunch, cancellationDate);
        assertFalse(amount.equals(refund.build().value()));
    }

    @Test
    public void ensureNoPenaltyIsAppliedBeforeCancellationLimitOnDinner(){
        RefundBuilder refund = new RefundBuilder();
        refund.withMecanographicNumber(number);
        refund.withMoney(amount);
        cancellationDate.set(Calendar.AM_PM, Calendar.PM);
        cancellationDate.set(Calendar.HOUR, 3);
        refund.withPenalty(mealDate, dinner, cancellationDate);
        assertTrue(amount.equals(refund.build().value()));
    }

    @Test
    public void ensurePenaltyIsAppliedAfterCancellationLimitOnDinner(){
        RefundBuilder refund = new RefundBuilder();
        refund.withMecanographicNumber(number);
        refund.withMoney(amount);
        cancellationDate.set(Calendar.AM_PM, Calendar.PM);
        cancellationDate.set(Calendar.HOUR, 5);
        refund.withPenalty(mealDate, dinner, cancellationDate);
        assertFalse(amount.equals(refund.build().value()));
    }

}
