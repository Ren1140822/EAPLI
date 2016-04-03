package eapli.ecafeteria.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.AggregateRoot;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * An Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. An Cafeteria User contains an User in it
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>,  Serializable {

    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.PERSIST)
    private SystemUser systemUser;
    private Account account;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private OrganicUnit OrganicUnit;

    @Id
    private MecanographicNumber mecanographicNumber;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public CafeteriaUser(SystemUser user, String account, OrganicUnit OrganicUnit,
            String mecanographicNumber) {
        // FIXME validate parameters
        this.systemUser = user;
        this.account = new Account(account);
        this.OrganicUnit = OrganicUnit;
        this.mecanographicNumber = new MecanographicNumber(mecanographicNumber);
        // by default
        this.status = Status.APPROVAL_PENDING;
    }

    protected CafeteriaUser() {
        // for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemUser)) {
            return false;
        }

        final CafeteriaUser cafeteriaUser = (CafeteriaUser) o;

        if (!this.mecanographicNumber.equals(cafeteriaUser.mecanographicNumber)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int result = this.mecanographicNumber.hashCode();

        return result;
    }

    public boolean sameAs(CafeteriaUser cafeteriaUser) {
        if (this == cafeteriaUser) {
            return true;
        }
        if (!this.mecanographicNumber.equals(cafeteriaUser.mecanographicNumber)) {
            return false;
        }

        if (!this.systemUser.equals(cafeteriaUser.systemUser)) {
            return false;
        }

        if (!this.account.equals(cafeteriaUser.account)) {
            return false;
        }
        if (!this.OrganicUnit.equals(cafeteriaUser.OrganicUnit)) {
            return false;
        }

        return true;
    }


    @Override
    public boolean is(MecanographicNumber id) {
        return id().equals(id);
    }

    public MecanographicNumber mecanographicNumber() {
        return this.mecanographicNumber;
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }
}
