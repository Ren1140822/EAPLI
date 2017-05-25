package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.Strings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * A Material Used
 *
 *
 * @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva
 * (1151088@isep.ipp.pt)
 */
@Entity
public class MaterialUsed implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;
    //@OneToMany(fetch=FetchType.LAZY, mappedBy="XXXX")
    private Meal meal;
    private BatchNumber batchNumber;
    private Material material;

    public MaterialUsed(Meal meal, Material material, String lotCode) {
        if (meal == null || material == null || lotCode == null || Strings.isNullOrEmpty(lotCode)) {
            throw new IllegalStateException();
        }
        this.meal = meal;
        this.material = material;
        this.batchNumber = new BatchNumber(lotCode);

    }

    protected MaterialUsed() {
        // for ORM
    }
    
    public BatchNumber batchNumber(){
        return this.batchNumber;
    }
    
    public Meal meal(){
        return this.meal;
    }
    
    public Material material(){
        return this.material;
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof MaterialUsed)) {
            return false;
        }

        final MaterialUsed that = (MaterialUsed) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && material.equals(that.material)
                && meal.equals(that.meal) && batchNumber.equals(that.batchNumber);
    }

    @Override
    public boolean is(Long t) {
        return id().equals(t);
    }

    @Override
    public Long id() {
        return this.pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MaterialUsed)) {
            return false;
        }

        final MaterialUsed other = (MaterialUsed) o;
        return this.batchNumber.equals(other.batchNumber);
    }

    @Override
    public int hashCode() {
        return this.material.hashCode() + this.batchNumber.hashCode();
    }

}
