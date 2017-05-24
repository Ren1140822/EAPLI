/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteria.AcceptRefuseSignupRequestController;
import eapli.ecafeteria.application.cafeteria.SignupController;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.cafeteria.SignupRequest;
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
        String mecanographicNumber2 = "900331";
        signupAndApprove("900331", "Password1", "Mary", "Smith", "mary@smith.com", unit, mecanographicNumber2);
        signupAndApprove("900332", "Password1", "Albert", "Smith", "albert@smith.com", unit, "900332");
        signupAndApprove("900333", "Password1", "John", "Doe", "john@doe.com", unit, "900333");
        signupAndApprove("900334", "Password1", "Donald", "Trump", "donald@trump.com", unit, "900334");
        signupAndApprove("900335", "Password1", "Barack", "Obama", "barack@obama.com", unit, "900335");
        signupAndApprove("900336", "Password1", "Michel", "Platini", "michel@platini.com", unit, "900336");
        signupAndApprove("900337", "Password1", "Salvador", "Sobral", "salvador@sobral.com", unit, "900337");
        signupAndApprove("900338", "Password1", "Angelina", "Jolie", "angelina@jolie.com", unit, "900338");
        signupAndApprove("900339", "Password1", "Rosa", "Matos", "rosa@matos.com", unit, "900339");
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
}
