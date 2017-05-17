/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
public class MecanographicNumber implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String number;
    
    public MecanographicNumber(String mecanographicNumber) {
        if (Strings.isNullOrEmpty(mecanographicNumber)) {
            throw new IllegalStateException("Mecanographic Number should neither be null nor empty");
        }
        this.number = mecanographicNumber;
    }

    protected MecanographicNumber() {
        // for ORM
    }

    /**
     * It indicates if the Mecanographic Number respects the strategy validations
     * 
     * @param strategy The strategy to examine compliance.
     * @return It returns "true" if the mecanographic number is valid or "false" otherwise.
     */
    public boolean compliesWith(MecanographicNumberStrategy strategy) {
        return strategy.validate(number);
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

        return this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return this.number.hashCode();
    }

    @Override
    public String toString() {
        return this.number;
    }
}
