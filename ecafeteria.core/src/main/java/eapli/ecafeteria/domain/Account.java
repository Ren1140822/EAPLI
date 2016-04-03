/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
// FIXME Account is almost certainly an entity (part of the CafeteriaUser
// aggregate) and not a value object
@Embeddable
public class Account implements ValueObject, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String account;

    public Account(String account) {
        if (Strings.isNullOrEmpty(account)) {
            throw new IllegalStateException("Account should neither be null nor empty");
        }
        // FIXME validate invariants, i.e., account regular expression
        this.account = account;
    }

    protected Account() {
        // for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }

        final Account that = (Account) o;

        return this.account.equals(that.account);

    }

    @Override
    public int hashCode() {
        return this.account.hashCode();
    }

    @Override
    public String toString() {
        return this.account;
    }
}
