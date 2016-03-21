/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.util.ArrayList;
import java.util.List;

import eapli.framework.domain.Factory;

/**
 * @author pgsou_000
 *
 */
public class UserFactory implements Factory<User> {

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
	public User build() {
		return new User(username, password, firstName, lastName, email, roles);
	}
}
