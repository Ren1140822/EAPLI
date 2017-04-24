package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.framework.actions.Action;
import java.util.HashSet;
import java.util.Set;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstraper implements Action {

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = {new UsersBootstraper(),
            new DishTypesBootstraper(),
            new OrganicUnitBootstraper(),
            new CafeteriaUserBootstraper(),
            new DishBootstraper(),
            new MaterialsBootstraper(),};

        // authenticate a super user to be able to register new users, ...
        // in this case we will inject the session but we shouldn't do this
        // AuthenticationService authz = new AuthenticationService();
        // Session adminSession = authz.authenticate(new Username("admin"), new
        // Password("admin"));
        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.ADMIN);
        roles.add(RoleType.MENU_MANAGER);
        roles.add(RoleType.KITCHEN_MANAGER);
        final UserSession adminSession = new UserSession(
                new SystemUser("poweruser", "poweruserA1", "joe", "doe", "joe@email.org", roles));
        Application.session().setSession(adminSession);

        // execute all bootstrapping returning true if any of the bootstraping
        // actions returns true
        boolean ret = false;
        for (final Action boot : actions) {
            ret |= boot.execute();
        }
        return ret;
    }
}
