/**
 *
 */
package eapli.framework.domain;

import eapli.framework.application.GenericDTO;

/**
 * A factory of domain objects. When creation of an entire, internally
 * consistent aggregate, or a large value object, becomes complicated or reveals
 * too much of the internal structure, factories provide encapsulation.
 *
 * @author pgsou_000
 *
 */
public interface Factory<T extends DomainEntity<?>> {

	// most likely such method is not interesting as we will need to receive
	// specific parameters for the constructor
	T buildNew();

	// not all classes might be interested in using DTOs
	T fromDTO(GenericDTO dto);
}
