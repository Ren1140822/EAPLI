package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nuno Bettencourt [NMB] on 24/03/16.
 */
public class AddUserControllerTest {

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

    @Test(expected=IllegalArgumentException.class)
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
}