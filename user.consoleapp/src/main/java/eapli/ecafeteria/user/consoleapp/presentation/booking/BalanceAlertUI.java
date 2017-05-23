/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.booking;

import eapli.ecafeteria.application.booking.BalanceAlertController;
import eapli.ecafeteria.domain.cafeteria.account.Alert;
import eapli.ecafeteria.domain.cafeteria.account.AlertWatchDog;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI from balance alert
 *
 * @author Sofia Gonçalves & Pedro Chilro
 */
public class BalanceAlertUI extends AbstractUI {

    private final AlertWatchDog alert = new AlertWatchDog();

    private Alert controller;

    public BalanceAlertUI() {
        this.controller = new BalanceAlertController();
    }

    @Override
    protected boolean doShow() {
        alert.add(controller);
        alert.soundTheAlert();
        return false;
    }

    @Override
    public String headline() {
        return "\033[31m" + "ALERT!" + "\033[0m";
    }

}
