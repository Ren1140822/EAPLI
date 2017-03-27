package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Material;
import eapli.framework.persistence.repositories.Repository;

/**
 * the repository for Materials.
 *
 */
public interface MaterialRepository extends Repository<Material, Long> {

    Material findByAcronym(String acronym);
}
