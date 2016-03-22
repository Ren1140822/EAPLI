package eapli.ecafeteria.domain.users;

import java.util.Calendar;
import java.util.List;

import eapli.framework.domain.AggregateRoot;
import eapli.framework.domain.Authorisable;

public class User implements AggregateRoot<Username>, Authorisable<ActionRight> {

	private Username	 username;
	private Password	 password;
	private Name		 name;
	private EmailAddress email;
	private List<Role>	 roles;
	private Calendar	 createdOn;


	public User(Username username, Password password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Username id() {
		return username;
	}

	/**
	 * Add role to user
	 * @param role
     */
	public void addRole(Role role) {
		roles.add(role);
	}

	/**
	 * Add role to user
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
		if (!this.password.equals(password))
		{
			throw new InvalidPasswordException("Password does note match", this);
		}

	}
}
