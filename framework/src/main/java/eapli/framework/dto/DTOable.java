/**
 *
 */
package eapli.framework.dto;

/**
 * @author pgsou_000
 *
 */
public interface DTOable<T> {

	/**
	 * returns a representation of the content of the object as a DTO.
	 *
	 * @return
	 */
	GenericDTO toDTO();
}
