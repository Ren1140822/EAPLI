/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */

@Embeddable
public class OrganicUnit implements ValueObject, Serializable
{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String  organicUnit;

    public OrganicUnit(String organicUnit) {
        if (Strings.isNullOrEmpty(organicUnit)) {
            throw new IllegalStateException("Organic Unit should neither be null nor empty");
        }
        // FIXME validate invariants, i.e., account regular expression
        this.organicUnit = organicUnit;
    }

    protected OrganicUnit() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganicUnit)) {
            return false;
        }

        final OrganicUnit that = (OrganicUnit) o;

        return this.organicUnit.equals(that.organicUnit);

    }

    @Override
    public int hashCode() {
        return this.organicUnit.hashCode();
    }

    @Override
    public String toString() {
        return this.organicUnit;
    }
}

