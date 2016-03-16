/**
 *
 */
package eapli.framework.application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A generic DTO that implements the Map interface. This class can be used when
 * you don't want to build your own custom DTO classes.
 *
 * @author pgsou_000
 *
 */
public class GenericDTO implements DTO, Map<String, Object> {

	Map<String, Object>	 data = new HashMap<String, Object>();

	private final String type;

	public GenericDTO(String type) {
		this.type = type;
	}

	/**
	 * Returns the name of the type contained in this DTO. Might be helpful for
	 * client code to parse the DTO.
	 * 
	 * @return
	 */
	public String type() {
		return type;
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return data.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return data.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return data.entrySet();
	}

	@Override
	public Object get(Object arg0) {
		return data.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return data.keySet();
	}

	@Override
	public Object put(String arg0, Object arg1) {
		return data.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> arg0) {
		data.putAll(arg0);
	}

	@Override
	public Object remove(Object arg0) {
		return data.remove(arg0);
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public Collection<Object> values() {
		return data.values();
	}
}
