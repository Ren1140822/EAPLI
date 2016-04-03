/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.util.DateTime;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

    public Iterable<SystemUser> listUsers() {
        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) {
            // TODO check which exception to throw
            throw new IllegalStateException("User is not authorized to perform this action");
        }
        final ListUsersController listUsersController = new ListUsersController();
        return listUsersController.listUsers();
    }
    
        public SystemUser deactivateUser(SystemUser user) {
        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) {
            // TODO check which exception to throw
            throw new IllegalStateException("User is not authorized to perform this action");
        }
        user.deactivate(DateTime.now());
        
        final UserRepository userRepository = PersistenceContext.repositories().users();
        userRepository.save(user);
        return user;
    }
}
