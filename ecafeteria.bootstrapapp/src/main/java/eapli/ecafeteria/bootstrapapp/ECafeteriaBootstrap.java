package eapli.ecafeteria.bootstrapapp;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.Session;
import eapli.ecafeteria.domain.users.SystemUser;
import eapli.framework.actions.Action;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrap implements Action {

    public static void main(String[] args) {
        System.out.println("Bootstrapping eCafeteria 2016(c) data");

        new ECafeteriaBootstrap().execute();
    }

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = { new UsersBootstrap(), new DishTypesBootstrap(), new OrganicUnitBootsrap() };

        // authenticate a super user to be able to register new users, ...
        // in this case we will inject the session but we shouldn't do this
        // AuthenticationService authz = new AuthenticationService();
        // Session adminSession = authz.authenticate(new Username("admin"), new
        // Password("admin"));
        final List<RoleType> roles = new ArrayList<RoleType>();
        roles.add(RoleType.Admin);
        final Session adminSession = new Session(
                new SystemUser("poweruser", "poweruser", "joe", "doe", "joe@email.org", roles));
        AppSettings.instance().setSession(adminSession);

        // execute all bootstrapping returning true if any of the bootstraping
        // actions returns true
        boolean ret = false;
        for (final Action boot : actions) {
            ret |= boot.execute();
        }
        return ret;
    }
}
