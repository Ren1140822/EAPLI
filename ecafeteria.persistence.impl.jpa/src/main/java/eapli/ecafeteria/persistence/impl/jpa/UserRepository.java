package eapli.ecafeteria.persistence.impl.jpa;

import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

public class UserRepository extends JpaRepository<User, Username> {

	@Override
	protected String persistenceUnitName() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
