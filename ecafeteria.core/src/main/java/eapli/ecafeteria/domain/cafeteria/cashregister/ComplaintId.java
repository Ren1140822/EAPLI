/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Embeddable;

/**
 * Represents a unique identifier to identify a complaint.
 * 
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
@Embeddable
public class ComplaintId implements ValueObject, Serializable {
    
     /**
     * The unique identifier for the complaint.
     */
    private UUID id;
   
    /**
     * Constructs a unique identifier for the complaint.
     */
    public ComplaintId(){
        this.id = UUID.randomUUID();
    }

   /**
    * {@inheritDoc }
    */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
    * {@inheritDoc }
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComplaintId other = (ComplaintId) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
