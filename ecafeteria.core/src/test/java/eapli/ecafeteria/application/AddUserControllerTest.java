package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.users.InvalidPasswordException;
import eapli.ecafeteria.domain.users.InvalidUserException;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.Session;
import eapli.ecafeteria.domain.users.SystemUser;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nuno Bettencourt [NMB] on 24/03/16.
 */
public class AddUserControllerTest {

    @BeforeClass
    public static void setUp() throws InvalidUserException, InvalidPasswordException {
        // in this case we will inject the session but we shouldn't do this
        //AuthenticationService authz = new AuthenticationService();
        //Session adminSession = authz.authenticate(new Username("admin"), new Password("admin"));
        List<RoleType> roles = new ArrayList<RoleType>();
        roles.add(RoleType.Admin);
        Session adminSession = new Session(new SystemUser("admin", "admin", "joe", "doe", "joe@email.org", roles));
        AppSettings.instance().setSession(adminSession);
    }

    @Test
    public void attestNormalBehaviour() throws Exception {

        String userName = "john";
        String password = "johndoe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@email.com";
        List<RoleType> roles = new ArrayList<>();
        roles.add(RoleType.Admin);
        roles.add(RoleType.Cashier);

        Calendar createdOn = DateTime.now();
        SystemUser expected = new SystemUser(userName, password, firstName, lastName, email, roles, createdOn);

        AddUserController controller = new AddUserController();

        SystemUser result = controller.addUser(userName, password, firstName, lastName, email, roles, createdOn);
        assertTrue("the added user does not have the same data as input", expected.sameAs(result));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureRoleTypeListIsNotNull() throws Exception {

        String userName = "john";
        String password = "johndoe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@email.com";
        List<RoleType> roles = null;

        SystemUser expected = new SystemUser(userName, password, firstName, lastName, email, roles);

        AddUserController controller = new AddUserController();

        SystemUser result = controller.addUser(userName, password, firstName, lastName, email, roles);
        assertEquals(expected, result);
    }
    /*
    @Test(expected=IllegalArgumentException.class)
    public void ensureRoleTypeListIsNotEmpty() throws Exception {

        String userName = "john";
        String password = "johndoe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@email.com";
        List<RoleType> roles = new ArrayList<>();

        User expected = new User(userName, password, firstName, lastName, email, roles);

        AddUserController controller = new AddUserController();

        User result = controller.addUser(userName, password, firstName, lastName, email, roles);
        assertEquals(expected, result);
    }
     */
}
