/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

/**
 * The interface alert.
 *
 * @author Sofia
 */
public interface Alert {

    /**
     * Method of alert that is going to say the property alert message is going
     * to be showed. It is showed if the balance limit is violated from average.
     */
    void alert();
}
