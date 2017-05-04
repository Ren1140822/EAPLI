/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.TimePeriod2;
import eapli.util.DateTime;
import java.util.Calendar;
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
    
    private DishType peixe;
    private Meal meal;
    private MealType mealType;
    private final Designation prego = Designation.valueOf("Prego");
    private Dish d;
    private NutricionalInfo aNutricionalInfo;
    private TimePeriod2 timePeriod2;
    private Calendar start;
    private Calendar end;
    
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
        peixe = new DishType("Peixe", "Peixe");
        aNutricionalInfo = new NutricionalInfo(10, 11);
        d = new Dish( peixe, prego, aNutricionalInfo, Money.euros(8));
        mealType = new MealType(MealType.MealTypes.ALMOCO);
        start = DateTime.now();
        end = DateTime.tomorrow();
        timePeriod2 = new TimePeriod2(start, end);
        meal = new Meal(d, mealType, timePeriod2); 
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalStateException.class)
    public void ensureMaterialIsNotNull() {
        MaterialUsed m = new MaterialUsed(meal, null, new BatchNumber("abc23"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureBatchNumberIsNotNull() {
        MaterialUsed m = new MaterialUsed(meal,new Material("abc", "description"), null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void ensureMealIsNotNull() {
        MaterialUsed m = new MaterialUsed(null,new Material("abc", "description"), new BatchNumber("abc23"));
    }
    
    @Test
    public void testMaterialUsedOk() {
        
        MaterialUsed m = new MaterialUsed(meal, new Material("abc", "description"), new BatchNumber("abc23"));
    }
}
