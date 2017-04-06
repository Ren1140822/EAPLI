/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.SystemUserBuilder;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUserBuilder;
import eapli.ecafeteria.domain.cafeteria.SignupRequest;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * Created by AJS on 08/04/2016.
 *
 * @FIXME this controller has lots of logic that should be moved to a domain
 * service
 *
 * @TODO there is some code duplication to create and add the system user
 *
 * @TODO following the guideline that a controller should only change one
 * Aggregate, we shouldn't be changing all these entities here, but should
 * instead use asynchronous events. However in this case we will take advantage
 * of TransactionalContext
 */
public class AcceptRefuseSignupRequestController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users(false);
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(false);
    private final SignupRequestRepository signupRequestsRepository = PersistenceContext.repositories().signupRequests(false);

    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        if (theSignupRequest == null) {
            throw new IllegalStateException();
        }

        //
        // explicitly begin a transaction
        //
        userRepository.beginTransaction();

        //
        // add system user
        //
        final SystemUserBuilder userBuilder = new SystemUserBuilder();
        userBuilder.withUsername(theSignupRequest.username()).withPassword(theSignupRequest.password())
                .withName(theSignupRequest.name()).withEmail(theSignupRequest.email()).withRole(RoleType.CAFETERIA_USER);
        // TODO error checking if the username is already in the persistence
        // store
        final SystemUser newUser = this.userRepository.save(userBuilder.build());

        //
        // add cafeteria user
        //
        final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();
        cafeteriaUserBuilder.withMecanographicNumber(theSignupRequest.mecanographicNumber())
                .withOrganicUnit(theSignupRequest.organicUnit()).withSystemUser(newUser);
        this.cafeteriaUserRepository.save(cafeteriaUserBuilder.build());

        //
        // modify Signup Request to accepted
        //
        theSignupRequest.accept();
        theSignupRequest = this.signupRequestsRepository.save(theSignupRequest);

        //
        // explicitly commit the transaction
        userRepository.commit();

        return theSignupRequest;
    }

    public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest)
            throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        if (theSignupRequest == null) {
            throw new IllegalStateException();
        }

        theSignupRequest.refuse();
        return this.signupRequestsRepository.save(theSignupRequest);
    }

    /**
     *
     * @return
     */
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return this.signupRequestsRepository.pendingSignupRequests();
    }
}
