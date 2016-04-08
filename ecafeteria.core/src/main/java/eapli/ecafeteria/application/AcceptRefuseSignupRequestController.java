/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.application.Controller;

/**
 *
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestController implements Controller {

    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        if (theSignupRequest == null) {
            throw new IllegalStateException();
        }

        //TODO create CAFETERIA USER and SYSTEM USER
        theSignupRequest.changeToAcceptedStatus();

        final SignupRequestRepository repo = PersistenceContext.repositories().signupRequests();
        return repo.save(theSignupRequest);
    }

    public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest) {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        if (theSignupRequest == null) {
            throw new IllegalStateException();
        }

        theSignupRequest.changeToRefusedStatus();

        final SignupRequestRepository repo = PersistenceContext.repositories().signupRequests();
        return repo.save(theSignupRequest);
    }

    /**
     *
     * @return
     */
    public Iterable<SignupRequest> listSignupRequestsPending() {
        final SignupRequestRepository repo = PersistenceContext.repositories().signupRequests();
        return repo.listSignupRequestsPending();
    }
}
