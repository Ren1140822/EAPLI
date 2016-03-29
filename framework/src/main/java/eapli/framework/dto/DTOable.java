/**
 *
 */
package eapli.framework.dto;

/**
 * a domain object that can be transformed to a GenericDTO
 * 
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
