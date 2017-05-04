/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteria.AcceptRefuseSignupRequestController;
import eapli.ecafeteria.application.cafeteria.SignupController;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.cafeteria.SignupRequest;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.logging.Logger;

/**
 *
 * @author Paulo Sousa
 */
public class CafeteriaUserBootstraper implements Action {

    @Override
    public boolean execute() {
	final OrganicUnitRepository unitRepo = PersistenceContext.repositories().organicUnits();
	final OrganicUnit unit = unitRepo.findByAcronym("ISEP");

        String mecanographicNumber1 = "900330";
        signupAndApprove("900330", "Password1", "John", "Smith", "john@smith.com", unit, mecanographicNumber1);
        createAccountCard(mecanographicNumber1);

        String mecanographicNumber2 = "900331";
        signupAndApprove("900331", "Password1", "Mary", "Smith", "mary@smith.com", unit, mecanographicNumber2);
        createAccountCard(mecanographicNumber2);

	return false;
    }

    /**
     *
     */
    private SignupRequest signupAndApprove(final String username, final String password, final String firstName,
	    final String lastName, final String email, OrganicUnit organicUnit, String mecanographicNumber) {
	final SignupController signupController = new SignupController();
	final AcceptRefuseSignupRequestController acceptController = new AcceptRefuseSignupRequestController();
	SignupRequest request = null;
	try {
	    request = signupController.signup(username, password, firstName, lastName, email, organicUnit,
		    mecanographicNumber);
	    acceptController.acceptSignupRequest(request);
	} catch (final DataConcurrencyException | DataIntegrityViolationException e) {
	    // assume it just a question of trying to insert duplicate record
	    Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
		    .info("EAPLI-DI001: Exception during bootstrapping: assuming existing record.");
	}
	return request;
    }

    /**
     *
     */
    private void createAccountCard(String mecanographicNumber) {

        // FIXME : Refactor account card bootstrap

        final AccountCardRepository accountCardRepo = PersistenceContext.repositories().accountCards();

        final MecanographicNumber aMecanographicNumber = new MecanographicNumber(mecanographicNumber);

        // TODO : Create account card controller

        final AccountCard aAccountCard = new AccountCard(aMecanographicNumber);

        try {
            accountCardRepo.save(aAccountCard);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            // assume it just a question of trying to insert duplicate record
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: Exception during bootstrapping: assuming existing record.");
        }


    }
}
