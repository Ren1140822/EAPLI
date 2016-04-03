package eapli.ecafeteria.domain;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class CafeteriaUserTest {

	@Test
	public void ensureCafeteriaUserEqualsPassesForTheSameMecanographicNumber() throws Exception {
		MecanographicNumber mecanographicNumber = new MecanographicNumber("abc");

		boolean expected = true;

		CafeteriaUser aCafeteriaUser = new CafeteriaUser();
		aCafeteriaUser.setMecanographicNumber(mecanographicNumber);

		CafeteriaUser anotherUser = new CafeteriaUser();
		anotherUser.setMecanographicNumber(mecanographicNumber);

		expected = aCafeteriaUser.equals(anotherUser);

		assertTrue(expected);
	}

	@Test
	public void ensureCafeteriaUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");
		MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("qwe");

		boolean expected = false;

		CafeteriaUser aCafeteriaUser = new CafeteriaUser();
		aCafeteriaUser.setMecanographicNumber(aMecanographicNumber);

		CafeteriaUser anotherUser = new CafeteriaUser();
		anotherUser.setMecanographicNumber(anotherMecanographicNumber);

		expected = aCafeteriaUser.equals(anotherUser);

		assertFalse(expected);
	}

	@Test
	public void ensureCafeteriaUserEqualsAreTheSameForTheSameInstance() throws Exception {
		boolean expected = true;

		CafeteriaUser aCafeteriaUser = new CafeteriaUser();

		expected = aCafeteriaUser.equals(aCafeteriaUser);

		assertTrue(expected);
	}

	@Test
	public void ensureCafeteriaUserEqualsFailsForDifferenteObjectTypes() throws Exception {
		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");

		boolean expected = false;

		CafeteriaUser aCafeteriaUser = new CafeteriaUser();
		aCafeteriaUser.setMecanographicNumber(aMecanographicNumber);

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser systemUser = new SystemUser("userName", "password", "firsName", "lastName", "email", roles);

		expected = aCafeteriaUser.equals(systemUser);

		assertFalse(expected);
	}

	@Test
	public void ensureCafeteriaUserIsTheSameAsItsInstance() throws Exception {
		boolean expected = true;

		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");
		CafeteriaUser aCafeteriaUser = new CafeteriaUser();
		aCafeteriaUser.setMecanographicNumber(aMecanographicNumber);

		expected = aCafeteriaUser.sameAs(aCafeteriaUser);

		assertTrue(expected);
	}

	@Test
	public void ensureTwoCafeteriaUserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
		boolean expected = false;

		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");
		MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("qwe");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser();
		aCafeteriaUser.setMecanographicNumber(aMecanographicNumber);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser();
		anotherCafeteriaUser.setMecanographicNumber(anotherMecanographicNumber);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUserWithDifferentMecanographicNumberAreNotTheSame() throws Exception {
		boolean expected = true;

		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");
		MecanographicNumber anotherMecanographicNumber = new MecanographicNumber("qwe");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser();
		aCafeteriaUser.setMecanographicNumber(aMecanographicNumber);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser();
		anotherCafeteriaUser.setMecanographicNumber(anotherMecanographicNumber);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUsersWithDifferentSystemUsersAreNotTheSame() throws Exception {
		boolean expected = false;

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", "emailA", roles);

		SystemUser anotherSystemUser = new SystemUser("userNameB", "passwordB", "firsNameB", "lastNameB", "emailB", roles);

		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");

		Account anAccount = new Account("newAccount");
		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber, Status.ACTIVE);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(anotherSystemUser, anAccount, anOrganicUnit, aMecanographicNumber, Status.ACTIVE);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUsersWithDifferentAccountsAreNotTheSame() throws Exception {
		boolean expected = false;

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", "email", roles);

		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");

		Account anAccount = new Account("accountA");
		Account anotherAccount = new Account("accountB");
		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber, Status.ACTIVE);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anotherAccount , anOrganicUnit, aMecanographicNumber, Status.ACTIVE);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUsersWithDifferentOrganicUnitsAreNotTheSame() throws Exception {
		boolean expected = false;

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", "email", roles);

		MecanographicNumber aMecanographicNumber = new MecanographicNumber("abc");

		Account anAccount = new Account("accountA");
		OrganicUnit anOrganicUnit = new OrganicUnit("acronymA", "nameA", "descriptionA");
		OrganicUnit anotherOrganicUnit = new OrganicUnit("acronymB", "nameB", "descriptionB");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber, Status.ACTIVE);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount , anotherOrganicUnit, aMecanographicNumber, Status.ACTIVE);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

}