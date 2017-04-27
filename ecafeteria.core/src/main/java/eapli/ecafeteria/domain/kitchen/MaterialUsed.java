/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Pedro Fernandes
 */
@Entity
public class MaterialUsed implements AggregateRoot<String>, Serializable {
    
    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;
    
    private Material material;
    private Allotment allotment;
    
    public MaterialUsed(Material material, Allotment allotment){
        if (material == null || allotment == null){
            throw new IllegalStateException();
        }
        
        this.material = material;
        this.allotment = allotment;        
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
    public boolean is(String id) {
        // TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String id() {
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
        return id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.material.hashCode() + this.allotment.hashCode();
    }
    
}
