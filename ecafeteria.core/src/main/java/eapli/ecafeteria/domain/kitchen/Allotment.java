/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Pedro Fernandes
 */
@Embeddable
public class Allotment implements ValueObject, Serializable{
    
    private String lotCode;
    
    public Allotment(String lotCode){
        if ( Strings.isNullOrWhiteSpace(lotCode)){
            throw new IllegalStateException("Invalid lot code");
        }
        
        this.lotCode = lotCode;        
    }
    
    protected Allotment() {
        // for ORM
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Allotment)) {
            return false;
        }

        final Allotment that = (Allotment) o;

        return this.lotCode.equals(that.lotCode);
    }

    @Override
    public int hashCode() {
        return this.lotCode.hashCode();
    }

    @Override
    public String toString() {
        return this.lotCode;
    }
    
}
