package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.Account;
import eapli.ecafeteria.domain.CafeteriaUser;
import eapli.ecafeteria.domain.CafeteriaUserBuilder;
import eapli.ecafeteria.domain.MecanographicNumber;
import eapli.ecafeteria.domain.OrganicUnit;
import eapli.ecafeteria.domain.Status;
import eapli.ecafeteria.domain.users.ActionRight;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.SystemUser;
import eapli.ecafeteria.domain.users.UserBuilder;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupCafeteriaUserController implements Controller {

    public CafeteriaUser addUser(SystemUser user, String account, OrganicUnit organicUnit, String mecanographicNumber, Status status) {

        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) {
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }

        final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();

        cafeteriaUserBuilder.withSystemUser(user);
        cafeteriaUserBuilder.withAccount(account);
        cafeteriaUserBuilder.withOrganicUnit(organicUnit);
        cafeteriaUserBuilder.withMecanographicNumber(mecanographicNumber);
        cafeteriaUserBuilder.withStatus(status);

        final CafeteriaUser newUser = cafeteriaUserBuilder.build();
        final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
        // TODO error checking if the username is already in the persistence
        // store
        cafeteriaUserRepository.add(newUser);
        return newUser;
    }

}
