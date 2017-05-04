package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.TimePeriod2;
import javax.persistence.*;

/**
 * @TODO is this an entity, a value object or an aggregate?
 * @TODO what is the relationship between this concept and Meal?
 *
 * Created by pyska on 26-04-2017.
 */
@Entity
public class MenuEntry {

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

    protected MenuEntry() {
    } //for ORM

    public MenuEntry(Dish dish, MealType mealType, TimePeriod2 timePeriod) {
        if (dish == null || mealType == null || timePeriod == null) {
            throw new IllegalStateException();
        }

        this.dish = dish;
        this.mealType = mealType;
        this.timePeriod = timePeriod;
    }

    public Dish dish() { return dish;}
    public MealType mealType() { return mealType;}
    public TimePeriod2 timePeriod(){return timePeriod;}

    public Long pk(){
        return pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuEntry meal = (MenuEntry) o;

        if (!pk.equals(meal.pk)) {
            return false;
        }
        if (!version.equals(meal.version)) {
            return false;
        }
        if (!dish.equals(meal.dish)) {
            return false;
        }
        if (!mealType.equals(meal.mealType)) {
            return false;
        }
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
