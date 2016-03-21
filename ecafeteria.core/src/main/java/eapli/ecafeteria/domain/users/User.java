package eapli.ecafeteria.domain.users;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eapli.framework.domain.AggregateRoot;
import eapli.framework.dto.DTOable;
import eapli.framework.dto.GenericDTO;
import eapli.util.DateTime;

public class User implements AggregateRoot<Username>, DTOable {

	private final Username	   username;
	private final Password	   password;
	private final Name		   name;
	private final EmailAddress email;
	private final List<Role>   roles;
	private final Calendar	   createdOn;

	public User(String username2, String password2, String firstName, String lastName, String email2,
	        List<RoleType> roles2) {
		createdOn = DateTime.now();
		username = new Username(username2);
		password = new Password(password2);
		name = new Name(firstName, lastName);
		email = new EmailAddress(email2);
		roles = new ArrayList<Role>();
		for (final RoleType rt : roles2) {
			roles.add(new Role(rt, createdOn));
		}
	}

	public boolean isAuthorizedTo(ActionRight action) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Username id() {
		return username;
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
}
