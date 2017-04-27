package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUserBuilder;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class AccountCardTest {

    private CafeteriaUser aCafeteriaUser;
    private CafeteriaUser anotherCafeteriaUser;

    @Before
    public void setUp() {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);

        aCafeteriaUser = new CafeteriaUserBuilder()
                .withOrganicUnit(new OrganicUnit("dummy", "dummy", "dummy")).withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        anotherCafeteriaUser = new CafeteriaUserBuilder()
                .withOrganicUnit(new OrganicUnit("dummy", "dummy", "dummy")).withMecanographicNumber("anotherDummy")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureAccountCardHasCafeteriaUser() {
        new AccountCard(null);
    }

    @Test
    public void ensureAccountCardPassesForTheSameSystemUser() throws Exception {

        final AccountCard aAccountCard = new AccountCard(aCafeteriaUser);
        final AccountCard anotherAccountCard = new AccountCard(aCafeteriaUser);

        assertTrue(aAccountCard.equals(anotherAccountCard));
    }

    @Test
    public void ensureAccountCardFailsForDifferentSystemUser() throws Exception {

        final AccountCard aAccountCard = new AccountCard(aCafeteriaUser);
        final AccountCard anotherAccountCard = new AccountCard(anotherCafeteriaUser);

        assertFalse(aAccountCard.equals(anotherAccountCard));
    }

    @Test
    public void ensureAccountCardIsTheSameAsTheSameSystemUser() throws Exception {

        final AccountCard aAccountCard = new AccountCard(aCafeteriaUser);
        final AccountCard anotherAccountCard = new AccountCard(aCafeteriaUser);

        assertTrue(aAccountCard.sameAs(anotherAccountCard));
    }

    @Test
    public void ensureAccountCardIsNotTheSameAsTheSameSystemUser() throws Exception {

        final AccountCard aAccountCard = new AccountCard(aCafeteriaUser);
        final AccountCard anotherAccountCard = new AccountCard(anotherCafeteriaUser);

        assertFalse(aAccountCard.sameAs(anotherAccountCard));
    }

}