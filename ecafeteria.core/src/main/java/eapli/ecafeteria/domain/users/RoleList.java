/**
 *
 */
package eapli.ecafeteria.domain.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author pgsou_000
 *
 */
public class RoleList implements List<Role>, Serializable {

	private final List<Role> data = new ArrayList<Role>();

	/**
	 *
	 */
	public RoleList() {
	}

	@Override
	public boolean add(Role arg0) {
		return data.add(arg0);
	}

	@Override
	public void add(int arg0, Role arg1) {
		data.add(arg0, arg1);
	}

	@Override
	public boolean addAll(Collection<? extends Role> arg0) {
		return data.addAll(arg0);
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends Role> arg1) {
		return data.addAll(arg0, arg1);
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public boolean contains(Object arg0) {

		return data.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {

		return data.containsAll(arg0);
	}

	@Override
	public Role get(int arg0) {
		return data.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return data.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public Iterator<Role> iterator() {
		return data.iterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return data.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<Role> listIterator() {
		return data.listIterator();
	}

	@Override
	public ListIterator<Role> listIterator(int arg0) {
		return data.listIterator(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		return data.remove(arg0);
	}

	@Override
	public Role remove(int arg0) {
		return data.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {

		return data.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return data.retainAll(arg0);
	}

	@Override
	public Role set(int arg0, Role arg1) {
		return data.set(arg0, arg1);
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public List<Role> subList(int arg0, int arg1) {
		return data.subList(arg0, arg1);
	}

	@Override
	public Object[] toArray() {
		return data.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return data.toArray(arg0);
	}

	public Collection<RoleType> roleTypes() {
		final List<RoleType> roleTypes = new ArrayList<RoleType>();

		//
		// taking advantage of java 8 iterators and lambda expressions to
		// replace the more conventional for each loop
		//
		// for (final Role role : data) {
		// roleTypes.add(role.type());
		// }
		//
		data.forEach(role -> roleTypes.add(role.type()));
		return roleTypes;
	}

}
