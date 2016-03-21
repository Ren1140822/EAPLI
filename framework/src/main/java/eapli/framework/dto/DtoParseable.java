/**
 *
 */
package eapli.framework.dto;

/**
 * @author pgsou_000
 *
 */
public interface DtoParseable<T extends DTOable<T>> {

	T valueOf(GenericDTO dto);
}
