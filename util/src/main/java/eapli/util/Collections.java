/**
 *
 */
package eapli.util;

/**
 * @author pgsou_000
 *
 */
public final class Collections {

    private Collections() {
    }

    public static int size(Iterable<?> col) {
	int i = 0;
	for (@SuppressWarnings("unused")
	final Object o : col) {
	    i++;
	}
	return i;
    }
}
