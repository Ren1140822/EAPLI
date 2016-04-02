/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.util.ArrayList;
import java.util.List;

import eapli.framework.domain.Factory;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author pgsou_000
 *
 */
public class UserFactory implements Factory<SystemUser> {

	private String				 username;
	private String				 password;
	private String				 firstName;
	private String				 lastName;
	private String				 email;
	private final List<RoleType> roles = new ArrayList<RoleType>();

	public UserFactory withFirstName(String name) {
		firstName = name;
		return this;
	}

	public UserFactory withLastName(String name) {
		lastName = name;
		return this;
	}

	public UserFactory withUsername(String username) {
		this.username = username;
		return this;
	}

	public UserFactory withPassword(String pass) {
		password = pass;
		return this;
	}

	public UserFactory withEmail(String email) {
		this.email = email;
		return this;
	}

	public UserFactory withRole(RoleType role) {
		roles.add(role);
		return this;
	}

	@Override
	public SystemUser build() {
		// since the factory knows that all the parts are needed it could throw
		// an exception. however, we will leave that to the constructor
		return new SystemUser(username, password, firstName, lastName, email, roles);
	}
}
