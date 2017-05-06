package eapli.ecafeteria.domain.meals;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ This is the allergenic information of a dish, it has the allergens contained in the respective dish
 * @FIXME is this an entity, a value object or an aggregate? Created by k4rd050
 * on 04-05-2017.
 */
@Entity
public class AllergenicInfo implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Allergen> allergens = new HashSet<>();

    public AllergenicInfo(Set<Allergen> allergens){
        if(allergens==null || allergens.isEmpty()){
            throw new IllegalStateException();
        }
        this.allergens = allergens;
    }


    public Set<Allergen> allergens(){ return allergens;}

    protected AllergenicInfo() {
        // for ORM only
    }
}
