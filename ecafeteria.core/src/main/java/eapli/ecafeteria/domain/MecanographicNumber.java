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
public class MecanographicNumber implements ValueObject, Serializable
{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String  mecanographicNumber;

    public MecanographicNumber(String account) {
        if (Strings.isNullOrEmpty(account)) {
            throw new IllegalStateException("Mecanographic Number should neither be null nor empty");
        }
        // FIXME validate invariants, i.e., account regular expression
        this.mecanographicNumber = mecanographicNumber;
    }

    protected MecanographicNumber() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MecanographicNumber)) {
            return false;
        }

        final MecanographicNumber that = (MecanographicNumber) o;

        return this.mecanographicNumber.equals(that.mecanographicNumber);

    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public String toString() {
        return this.mecanographicNumber;
    }
}

