/**
 *
 */
package eapli.framework.dto;

/**
 * a class that can build an instance of a domain object based on a GenericDTO
 *
 * @author pgsou_000
 *
 */
public interface DtoParseable<T extends DTOable<T>> {

    /**
     * parses the dto and builds a new T based on the content of the DTO
     *
     * @param dto
     * @return
     */
    T valueOf(GenericDTO dto);
}
