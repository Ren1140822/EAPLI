package eapli.ecafeteria.domain.users;

import java.util.Calendar;
import java.util.List;

import eapli.framework.domain.AggregateRoot;

public class User implements AggregateRoot<Username> {

	private Username	 username;
	private Password	 password;
	private Name		 name;
	private EmailAddress email;
	private List<Role>	 roles;
	private Calendar	 createdOn;

	public boolean isAuthorizedTo(ActionRight action) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Username id() {
		return username;
	}
}
