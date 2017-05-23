/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.domain.cafeteria.account.Alert;
import eapli.ecafeteria.domain.cafeteria.account.BalanceLimit;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;

/**
 * Controller from Balance Alert.
 *
 * @author Sofia Gonçalves & Pedro Chilro
 */
public class BalanceAlertController implements Controller, Alert {

    private final CafeteriaUserService usersService = new CafeteriaUserService();
    private final AccountCardRepository accountRep = PersistenceContext.repositories().accountCards(null);
    private final MenuRepository menuRep = PersistenceContext.repositories().menus();
    private Username username = Application.session().session().authenticatedUser().username();
    private CafeteriaUser client = usersService.findCafeteriaUserByUsername(username);
    private BalanceLimit balanceLimit = new BalanceLimit();

    /**
     * Method of alert that is going to say the property alert message is going
     * to be showed. It is showed if the balance limit is violated from average.
     */
    @Override
    public void alert() {
        double averageCost = 0;
        try {
            averageCost = balanceLimit.currentWeekAverageCost(client, menuRep);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        Money average = Money.euros(averageCost);
        AccountCard account = accountRep.findByMecanographicNumber(client.mecanographicNumber());
        if (balanceLimit.isViolated(account.balance(), average)) {
            System.out.println("\033[31m" + "Current balance is less than valid balance limit." + "\033[0m");
        }
    }

}
