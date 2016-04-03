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
		boolean expected = true;

		String aMecanographicNumber = new String("abc");
		String anotherMecanographicNumber = new String("abc");

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", "emailA", roles);

		String anAccount= new String("acountA");

		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);
		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, anotherMecanographicNumber);
		expected = aCafeteriaUser.equals(anotherCafeteriaUser );

		assertTrue(expected);
	}

	@Test
	public void ensureCafeteriaUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
		boolean expected = false;

		String aMecanographicNumber = new String("abc");
		String anotherMecanographicNumber = new String("qwe");

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", "emailA", roles);

		String anAccount= new String("acountA");

		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);
		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, anotherMecanographicNumber);

		expected = aCafeteriaUser.equals(anotherCafeteriaUser);

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
		boolean expected = false;

		String aMecanographicNumber = new String("abc");

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", "emailA", roles);

		String anAccount= new String("acountA");

		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);

		ArrayList<RoleType> systemUserRoles = new ArrayList<>();
		systemUserRoles.add(RoleType.Admin);

		SystemUser systemUser = new SystemUser("userName", "password", "firsName", "lastName", "email", systemUserRoles);

		expected = aCafeteriaUser.equals(systemUser);

		assertFalse(expected);
	}

	@Test
	public void ensureCafeteriaUserIsTheSameAsItsInstance() throws Exception {
		boolean expected = true;

		String aMecanographicNumber = new String("abc");

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", "emailA", roles);

		String anAccount= new String("acountA");

		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);

		expected = aCafeteriaUser.sameAs(aCafeteriaUser);

		assertTrue(expected);
	}

	@Test
	public void ensureTwoCafeteriaUserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
		boolean expected = true;

		String aMecanographicNumber = new String("abc");
		String anotherMecanographicNumber = new String("qwe");

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", "emailA", roles);

		String anAccount= new String("acountA");

		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, anotherMecanographicNumber);

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

		String aMecanographicNumber = new String("abc");

		String anAccount = new String("newAccount");
		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(anotherSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUsersWithDifferentAccountsAreNotTheSame() throws Exception {
		boolean expected = false;

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", "email", roles);

		String aMecanographicNumber = new String("abc");

		String anAccount = new String ("accountA");
		String anotherAccount = new String("accountB");
		OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anotherAccount , anOrganicUnit, aMecanographicNumber);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUsersWithDifferentOrganicUnitsAreNotTheSame() throws Exception {
		boolean expected = false;

		ArrayList<RoleType> roles = new ArrayList<>();
		roles.add(RoleType.Admin);

		SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", "email", roles);

		String aMecanographicNumber = new String("abc");

		String anAccount = new String("accountA");
		OrganicUnit anOrganicUnit = new OrganicUnit("acronymA", "nameA", "descriptionA");
		OrganicUnit anotherOrganicUnit = new OrganicUnit("acronymB", "nameB", "descriptionB");

		CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount, anOrganicUnit, aMecanographicNumber);

		CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anAccount , anotherOrganicUnit, aMecanographicNumber);

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

}