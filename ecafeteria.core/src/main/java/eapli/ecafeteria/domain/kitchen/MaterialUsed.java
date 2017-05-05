/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @FIXME javadoc
 * @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva
 * (1151088@isep.ipp.pt)
 */
@Entity
public class MaterialUsed implements AggregateRoot<BatchNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;
    private Meal meal;
    private Material material;
    private BatchNumber batchNumber;

    public MaterialUsed(Meal meal, Material material, String lotCode) {
        if (meal == null || material == null) {
            throw new IllegalStateException();
        }
        this.meal = meal;
        this.material = material;
        this.batchNumber = new BatchNumber(lotCode);

    }

    protected MaterialUsed() {
        // for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        // TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is(BatchNumber t) {
        // TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BatchNumber id() {
        // TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
