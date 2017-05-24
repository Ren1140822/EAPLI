/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @FIXME javadoc
 * @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva
 * (1151088@isep.ipp.pt)
 */
@Embeddable
public class BatchNumber implements ValueObject, Serializable {

    private String lotCode;

    public BatchNumber(String lotCode) {
        if (Strings.isNullOrWhiteSpace(lotCode)) {
            throw new IllegalStateException("Invalid lot code");
        }

        this.lotCode = lotCode;
    }

    protected BatchNumber() {
        // for ORM
    }

    public String lotCode() {
        return this.lotCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BatchNumber)) {
            return false;
        }

        final BatchNumber that = (BatchNumber) o;

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
