/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserBuilder;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUserBuilder;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestController implements Controller {

    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) throws DataIntegrityViolationException {
        ensurePermissionOfLoggedInUser(ActionRight.Administer);

        if (theSignupRequest == null) {
            throw new IllegalStateException();
        }

        // TODO this controller has some logic that could be moved to a domain
        // service

        // TODO there are some code duplication to create and add the system
        // user

        //
        // add system user
        //
        final UserBuilder userBuilder = new UserBuilder();
        userBuilder.withUsername(theSignupRequest.username());
        userBuilder.withPassword(theSignupRequest.password());
        userBuilder.withName(theSignupRequest.name());
        userBuilder.withEmail(theSignupRequest.email());
        final SystemUser newUser = userBuilder.build();

        final UserRepository userRepository = PersistenceContext.repositories().users();
        // TODO error checking if the username is already in the persistence
        // store
        userRepository.add(newUser);

        //
        // add cafeteria user
        //
        final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();
        cafeteriaUserBuilder.withMecanographicNumber(theSignupRequest.mecanographicNumber());
        cafeteriaUserBuilder.withOrganicUnit(theSignupRequest.organicUnit());
        cafeteriaUserBuilder.withSystemUser(newUser);

        // FIXME add the cafeteria user to the repository

        //
        // modify Signup Request to accepted
        //
        theSignupRequest.changeToAcceptedStatus();

        final SignupRequestRepository repo = PersistenceContext.repositories().signupRequests();
        repo.add(theSignupRequest);
        return theSignupRequest;
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
    public Iterable<SignupRequest> listPendingSignupRequests() {
        final SignupRequestRepository repo = PersistenceContext.repositories().signupRequests();
        return repo.listPendingSignupRequests();
    }
}
