/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import java.util.ArrayList;
import java.util.List;

/**
 * It represents an object that has all the observers and triggers it.
 *
 * @author Sofia Gonçalves & Pedro Chilro
 */
public class AlertWatchDog {

    /**
     * A list of observers.
     */
    private List observers;

    /**
     * The constructor.
     */
    public AlertWatchDog() {
        observers = new ArrayList();
    }

    /**
     * It adds the alert in the observers list.
     *
     * @param alert
     */
    public void add(Alert alert) {
        observers.add(alert);
    }

    /**
     * It sounds the alert from all the observers.
     */
    public void soundTheAlert() {
        for (int i = 0; i < observers.size(); i++) {
            ((Alert) observers.get(i)).alert();
        }
    }
}
