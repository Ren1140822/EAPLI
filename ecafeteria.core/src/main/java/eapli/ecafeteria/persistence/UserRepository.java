/**
 *
 */
package eapli.ecafeteria.persistence;

import java.util.Iterator;
import java.util.List;

import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.framework.persistence.repositories.IterableRepository;

/**
 * @author pgsou_000
 *
 */
public class UserRepository implements IterableRepository<User, Username> {

	@Override
	public User save(User entity) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<User> all() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User findById(Username id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public long size() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Iterator<User> iterator() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Iterator<User> iterator(int pagesize) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
