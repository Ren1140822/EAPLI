package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.framework.domain.TimePeriod2;

import javax.persistence.*;

/**
 * Created by pyska on 26-04-2017.
 */
@Entity
public class Meal {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @OneToOne
    private Dish dish;
 
    @Embedded
    private MealType mealType;
 
    @Embedded
    private TimePeriod2 timePeriod;

    protected Meal() {} //for ORM

    public Meal(Dish dish, MealType mealType, TimePeriod2 timePeriod) {
        if(dish == null || mealType == null || timePeriod == null){
            throw new IllegalStateException();
        }

        this.dish = dish;
        this.mealType = mealType;
        this.timePeriod = timePeriod;
    }

    public Long pk(){
        return pk;
    }
    
    public TimePeriod2 timePeriod(){
        return this.timePeriod;
    }
    
    public Dish dish(){
        return this.dish;
    }
    
    public MealType mealType(){
        return this.mealType();
    }
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (!pk.equals(meal.pk)) return false;
        if (!version.equals(meal.version)) return false;
        if (!dish.equals(meal.dish)) return false;
        if (!mealType.equals(meal.mealType)) return false;
        return timePeriod.equals(meal.timePeriod);
    }

    @Override
    public int hashCode() {
        int result = dish.hashCode();
        result = 31 * result + mealType.hashCode();
        result = 31 * result + timePeriod.hashCode();
        return result;
    }
}
