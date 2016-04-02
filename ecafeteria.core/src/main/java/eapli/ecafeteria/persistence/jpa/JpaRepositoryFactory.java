package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.UserRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {
	@Override
	public UserRepository users() {
		return new JpaUserRepository();
	}

	@Override
	public JpaDishTypeRepository dishTypes() {
		return new JpaDishTypeRepository();
	}
        
	@Override
	public JpaOrganicUnitRepository organicUnits() {
		return new JpaOrganicUnitRepository();
	}        
}
