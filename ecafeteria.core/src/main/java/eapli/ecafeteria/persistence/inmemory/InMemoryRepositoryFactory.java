package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

	private static UserRepository userRepository = null;
	private static DishTypeRepository dishTypeRepository = null;
        private static OrganicUnitRepository organicUnitRepository = null;

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
        
        @Override
	public OrganicUnitRepository organicUnits() {
		if (organicUnitRepository == null) {
			organicUnitRepository = new InMemoryOrganicUnitRepository();
		}
		return organicUnitRepository;
	}
}
