package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @FIXME javadoc
 * @FIXME is this an entity, a value object or an aggregate? Created by k4rd050
 * on 04-05-2017.
 */
@Entity
public class DishAllergen implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Dish dish;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Allergen allergen;

    public DishAllergen(Dish dish, Allergen allergen) {
        this.dish = dish;
        this.allergen = allergen;
    }

    protected DishAllergen() {
        // for ORM only
    }
}
