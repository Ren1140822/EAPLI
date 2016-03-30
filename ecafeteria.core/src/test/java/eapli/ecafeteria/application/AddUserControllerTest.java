package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.users.AuthenticationService;
import eapli.ecafeteria.domain.users.InvalidPasswordException;
import eapli.ecafeteria.domain.users.InvalidUserException;
import eapli.ecafeteria.domain.users.Password;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.Session;
import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Nuno Bettencourt [NMB] on 24/03/16.
 */
public class AddUserControllerTest {

    @BeforeClass
    public static void setUp() throws InvalidUserException, InvalidPasswordException {
        AuthenticationService authz = new AuthenticationService();
        Session adminSession = authz.authenticate(new Username("admin"), new Password("admin"));
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

        User expected = new User(userName, password, firstName, lastName, email, roles);

        AddUserController controller = new AddUserController();

        User result = controller.addUser(userName, password, firstName, lastName, email, roles);
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureRoleTypeListIsNotNull() throws Exception {

        String userName = "john";
        String password = "johndoe";
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@email.com";
        List<RoleType> roles = null;

        User expected = new User(userName, password, firstName, lastName, email, roles);

        AddUserController controller = new AddUserController();

        User result = controller.addUser(userName, password, firstName, lastName, email, roles);
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
