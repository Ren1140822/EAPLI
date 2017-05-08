package eapli.ecafeteria.domain.meals;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @FIXME javadoc
 * @FIXME is this an entity, a value object or an aggregate? Created by pyska on
 * 26-04-2017.
 */
@Entity
public class Meal {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @ManyToOne
    private Dish dish;

    @Embedded
    private MealType mealType;
    @Temporal(TemporalType.DATE)
    private Calendar date;

    protected Meal() {
    } //for ORM

    public Meal(Dish dish, MealType mealType, Calendar date) {
        if (dish == null || mealType == null || date == null) {
            throw new IllegalStateException();
        }

        this.dish = dish;
        this.mealType = mealType;
        this.date = date;
    }

    public Long pk() {
        return pk;
    }

    public Calendar getDate() {
        return date;
    }

    public Dish dish() {
        return this.dish;
    }

    public MealType mealType() {
        return this.mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Meal meal = (Meal) o;

        //FIXME aggregates/entities are only compared by identity
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
        return date.equals(meal.date);
    }

    @Override
    public int hashCode() {
        int result = dish.hashCode();
        result = 31 * result + mealType.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
