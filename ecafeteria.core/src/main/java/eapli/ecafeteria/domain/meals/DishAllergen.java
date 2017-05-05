package eapli.ecafeteria.domain.meals;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by k4rd050 on 04-05-2017.
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

    public DishAllergen(Dish dish, Allergen allergen){
        this.dish = dish;
        this.allergen = allergen;
    }

    protected DishAllergen() {
        // for ORM only
    }
}
