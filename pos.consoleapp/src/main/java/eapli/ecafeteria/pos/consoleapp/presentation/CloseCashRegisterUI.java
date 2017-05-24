/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cashregister.CloseCashRegisterController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class CloseCashRegisterUI extends AbstractUI {
    private final CloseCashRegisterController ctrl = new CloseCashRegisterController();
    @Override
    protected boolean doShow() {
        try {
            Application.ensurePermissionOfLoggedInUser(ActionRight.SALE);
            ctrl.closeCashRegister(Application.session().session().authenticatedUser());
            System.out.println("Cash register was closed sucessfully");
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CloseCashRegisterUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CloseCashRegisterUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NoResultException ex)
        {
            System.out.println("No opened cash registers found for this cashier");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Cash Register Closing";
    }
    
}
