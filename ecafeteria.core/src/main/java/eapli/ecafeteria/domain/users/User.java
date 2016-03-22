package eapli.ecafeteria.domain.users;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eapli.framework.domain.AggregateRoot;
import eapli.framework.domain.Authorisable;
import eapli.framework.dto.DTOable;
import eapli.framework.dto.GenericDTO;
import eapli.util.DateTime;

public class User implements AggregateRoot<Username>, Authorisable<ActionRight>, DTOable<User> {

	private final Username	   username;
	private final Password	   password;
	private final Name		   name;
	private final EmailAddress email;
	private final List<Role>   roles;
	private final Calendar	   createdOn;

	public User(String username, String password, String firstName, String lastName, String email,
	        List<RoleType> roles) {
		if (roles == null) {
			throw new IllegalArgumentException("roles cannot be null");
		}
		createdOn = DateTime.now();
		this.username = new Username(username);
		this.password = new Password(password);
		name = new Name(firstName, lastName);
		this.email = new EmailAddress(email);
		this.roles = new ArrayList<Role>();
		for (final RoleType rt : roles) {
			this.roles.add(new Role(rt, createdOn));
		}
	}

	@Override
	public Username id() {
		return username;
	}

	/**
	 * Add role to user
	 *
	 * @param role
	 */
	public void addRole(Role role) {
		roles.add(role);
	}

	@Override
	public GenericDTO toDTO() {
		final GenericDTO ret = new GenericDTO("user");
		ret.put("username", username.toString());
		ret.put("password", password.toString());
		ret.put("name", name.toString());
		ret.put("email", email.toString());

		return ret;
	}

	/**
	 * Add role to user
	 *
	 * @param role
	 */
	public void removeRole(Role role) {
		roles.remove(role);
	}

	@Override
	public boolean isAuthorizedTo(ActionRight action) {

		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void passwordMatches(Password password) throws InvalidPasswordException {
		if (!this.password.equals(password)) {
			throw new InvalidPasswordException("Password does note match", this);
		}

	}
}
