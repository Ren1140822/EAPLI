package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

	private static UserRepository userRepository = null;
	private static DishTypeRepository dishTypeRepository = null;

	@Override
	public UserRepository users() {
		if (userRepository == null) {
			userRepository = new InMemoryUserRepository();
		}
		return userRepository;
	}

	@Override
	public DishTypeRepository dishTypes() {
		if (dishTypeRepository == null) {
			dishTypeRepository = new InMemoryDishTypeRepository();
		}
		return dishTypeRepository;
	}
}
