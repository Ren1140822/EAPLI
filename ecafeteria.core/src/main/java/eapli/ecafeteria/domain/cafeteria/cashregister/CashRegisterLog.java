/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
@Entity
public class CashRegisterLog implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @ManyToOne
    private CashRegister cashRegister;
    private Shift shift;
    private SystemUser cashier;
    private CashRegisterState cashRegisterAction;

    protected CashRegisterLog() {
        //for ORM
    }

    public CashRegisterLog(CashRegister cashRegister, Shift shift, SystemUser cashier,CashRegisterState cashRegisterAction) {
        this.cashRegister = cashRegister;
        this.shift = shift;
        this.cashier = cashier;
        this.cashRegisterAction = cashRegisterAction;
    }

    @Override
    public boolean sameAs(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if ((otherObject == null) || !(otherObject instanceof CashRegister)) {
            return false;
        }

        CashRegister otherCashRegister = (CashRegister) otherObject;
        return true;
    }

    @Override
    public boolean is(Long id) {
        return id().equals(id);
    }

    @Override
    public Long id() {
        return this.pk;
    }

    public CashRegisterId cashRegisterID() {
        return cashRegister.id();
    }

}
